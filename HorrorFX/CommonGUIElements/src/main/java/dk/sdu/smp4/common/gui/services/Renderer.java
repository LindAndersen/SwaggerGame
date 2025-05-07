package dk.sdu.smp4.common.gui.services;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
import dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.*;
import dk.sdu.smp4.common.gui.util.ColorConverter;
import dk.sdu.smp4.common.gui.util.EntityImageConverter;
import dk.sdu.smp4.common.lightsource.data.CommonLightSource;
import dk.sdu.smp4.commonplayerlight.services.IToggleableLight;
import dk.sdu.smp4.map.services.IMapGenerator;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Renderer {
    private final World world;
    private final GameData gameData;
    private final GUIManager guiManager;
    private Image noiseImage;
    private Canvas lightMaskCanvas;
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Map<Entity, ImageView> images = new ConcurrentHashMap<>();
    private final Map<EntityImage, Image> imageCache = new HashMap<>();
    private long lastTime = System.nanoTime();

    public Renderer(World world, GameData gameData, GUIManager guiManager) {
        this.world = world;
        this.gameData = gameData;
        this.guiManager = guiManager;
    }

    public void start()
    {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                float delta = (now - lastTime) / 1_000_000_000.0f;
                lastTime = now;
                gameData.setDelta(delta);

                if (!gameData.isPaused()) {
                    update();
                    draw();
                    gameData.getKeys().update();
                }
            }
        };


        render(timer);
    }

    private void render(AnimationTimer timer) {
        getMapGeneratorServices().stream().findFirst().ifPresent(generator -> generator.generate(world));
        noiseImage = generateNoiseImage(world.getMapWidth(), world.getMapHeight());
        lightMaskCanvas = new Canvas(world.getMapWidth(), world.getMapHeight());
        timer.start();
    }

    private void update() {
        getEntityProcessingServices().forEach(service -> service.process(gameData, world));
        getPostEntityProcessor().forEach(service -> service.process(gameData, world));
    }

    private boolean isEntityLit(Entity entity) {
        double entityX = entity.getX();
        double entityY = entity.getY();

        for (Entity lightEntity : world.getEntities(CommonLightSource.class)) {
            if (lightEntity instanceof IToggleableLight iToggleableLight && !iToggleableLight.isOn())
            {
                continue;
            }
            Polygon lightPoly = new Polygon(lightEntity.getPolygonCoordinates());
            handlePolygonCoordsPreDrawing(lightPoly, lightEntity);

            double localX = entityX - lightPoly.getTranslateX();
            double localY = entityY - lightPoly.getTranslateY();

//            Circle debugDot = new Circle(3, Color.RED);
//            debugDot.setTranslateX(entity.getX());
//            debugDot.setTranslateY(entity.getY());
//            gameData.getPolygonLayer().getChildren().add(debugDot);


            // Check 5 points: center + four offsets
            if (lightPoly.contains(localX, localY) ||
                    lightPoly.contains(localX + 2, localY) ||
                    lightPoly.contains(localX - 2, localY) ||
                    lightPoly.contains(localX, localY + 2) ||
                    lightPoly.contains(localX, localY - 2)) {
                return true;
            }
        }

        return false;
    }


    private void drawLightingMask() {
        GraphicsContext gcLight = lightMaskCanvas.getGraphicsContext2D();
        gcLight.setGlobalBlendMode(BlendMode.SRC_OVER);
        gcLight.clearRect(0, 0, lightMaskCanvas.getWidth(), lightMaskCanvas.getHeight());

        gcLight.drawImage(noiseImage, 0, 0, lightMaskCanvas.getWidth(), lightMaskCanvas.getHeight());

        gcLight.setFill(Color.color(1, 1, 1, 1));
        for (Entity entity : world.getEntities(CommonLightSource.class)) {
            if (entity instanceof IToggleableLight iToggleableLight && !(iToggleableLight.isOn()))
            {
                continue;
            }

            double[] coords = entity.getPolygonCoordinates();
            Polygon poly = new Polygon(coords);
            handlePolygonCoordsPreDrawing(poly, entity);
            double[] points = poly.getPoints().stream().mapToDouble(Double::doubleValue).toArray();

            if (points.length >= 4) {
                gcLight.beginPath();
                gcLight.moveTo(points[0] + poly.getTranslateX(), points[1] + poly.getTranslateY());
                for (int i = 2; i < points.length; i += 2) {
                    gcLight.lineTo(points[i] + poly.getTranslateX(), points[i + 1] + poly.getTranslateY());
                }

                // Explicitly close the path by connecting back to the first point
                gcLight.lineTo(points[0] + poly.getTranslateX(), points[1] + poly.getTranslateY());

                gcLight.closePath();
                gcLight.fill();
            }
        }

        lightMaskCanvas.setBlendMode(BlendMode.MULTIPLY);
        guiManager.getLightLayer().getChildren().setAll(lightMaskCanvas);
    }

    private void updatePolygonAndImageMap()
    {
        Collection<Entity> worldEntities = world.getEntities();

        for (Entity polygonEntity : polygons.keySet()) {
            if (!worldEntities.contains(polygonEntity)) {
                guiManager.getPolygonLayer().getChildren().remove(polygons.get(polygonEntity));
                polygons.remove(polygonEntity);

                ImageView removedImage = images.remove(polygonEntity);
                if (removedImage != null) {
                    guiManager.getPolygonLayer().getChildren().remove(removedImage);
                }
            }
        }
    }

    private void drawEntities()
    {
        for (Entity entity : world.getEntities()) {
            boolean isVisible;
            if (entity instanceof CommonLightSource) continue;
            isVisible = !(entity instanceof SoftEntity) || isEntityLit(entity);

            Polygon polygon = polygons.computeIfAbsent(entity, e -> {
                Polygon newPoly = new Polygon(e.getPolygonCoordinates());
                guiManager.getPolygonLayer().getChildren().add(newPoly);
                return newPoly;
            });

            handlePolygonCoordsPreDrawing(polygon, entity);
            //DEBUGGING
            polygon.setVisible(isVisible);
            //polygon.setVisible(true);

            EntityImage entityImage = entity.getImage();

            if (entityImage != null) {
                ImageView imageView = images.get(entity);
                if (imageView == null) {
                    imageView = new ImageView();
                    images.put(entity, imageView);
                    guiManager.getPolygonLayer().getChildren().add(imageView);
                }

                Image fxImage = imageCache.computeIfAbsent(entityImage, e -> EntityImageConverter.convertEntityImage(e, e.getResourceClass()));

                if (imageView.getImage() == null || !imageView.getImage().equals(fxImage)) {
                    imageView.setImage(fxImage);
                }

                imageView.setTranslateX(entity.getX() - fxImage.getWidth() / 2);
                imageView.setTranslateY(entity.getY() - fxImage.getHeight() / 2);
                //DEBUGGING
                imageView.setVisible(isVisible);
                //imageView.setVisible(false);
            }

        }
    }

    private void draw() {
        updatePolygonAndImageMap();
        drawLightingMask();
        drawEntities();
    }

    private void handlePolygonCoordsPreDrawing(Polygon polygon, Entity entity) {
        polygon.getTransforms().clear();

        polygon.setTranslateX(entity.getX());
        polygon.setTranslateY(entity.getY());

        if (entity.isShouldRotateAlternative()) {
            //polygon.getTransforms().add(new Rotate(entity.getRotation(), 0, 0));
        } else {
            polygon.setRotate(entity.getRotation());
        }
        if(entity instanceof SoftEntity)
        {
            //DEBUGGING
            polygon.setOpacity(0);
            //polygon.setOpacity(1);
        }

        polygon.setFill(ColorConverter.toJavaFXColor(entity.getPaint()));

    }

    private Image generateNoiseImage(int width, int height) {
        WritableImage image = new WritableImage(width, height);
        PixelWriter pw = image.getPixelWriter();
        Random rand = new Random();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double base = 0.2 + rand.nextDouble() * 0.3;
                pw.setColor(x, y, Color.color(base, base * 0.8, base * 0.6, 1.0));
            }
        }

        return image;
    }

    private Collection<? extends IMapGenerator> getMapGeneratorServices(){
        return ServiceLoader.load(IMapGenerator.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessor() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
