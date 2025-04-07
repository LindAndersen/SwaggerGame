package dk.sdu.smp4.main;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.Services.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.*;
import dk.sdu.smp4.common.lightsource.data.CommonLightSource;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Main extends Application {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Map<Entity, ImageView> images = new ConcurrentHashMap<>();
    private final StackPane gameWindow = gameData.getRoot();
    private StackPane startPane = new StackPane();
    private final Image noiseImage = generateNoiseImage(gameData.getDisplayWidth(), gameData.getDisplayHeight());
    private final Canvas lightMaskCanvas = new Canvas(gameData.getDisplayWidth(), gameData.getDisplayHeight());

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) {
        Scene startScene = new Scene(startPane, gameData.getDisplayWidth(), gameData.getDisplayHeight());
        startScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        window.setScene(startScene);
        setStartPane(window);
        Font.loadFont(getClass().getResource("/fonts/was.ttf").toExternalForm(), 10);

        window.setTitle("HorrorFX");
        window.show();
    }

    private void setStartPane(Stage stage) {
        startPane.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        startPane.getStyleClass().add("start-pane");

        Label title = new Label("HorrorFX");
        title.setPadding(new Insets(50, 0, 0, 0));
        title.getStyleClass().add("title-label");

        Button startButton = new Button("Start");
        startButton.getStyleClass().add("menu-button");
        startButton.setOnMouseClicked((MouseEvent event) -> {
            stage.setScene(createGameScene(stage));
        });


        Button quitButton = new Button("Quit");
        quitButton.getStyleClass().add("menu-button");
        quitButton.setOnMouseClicked((MouseEvent event) -> {
            stage.close();
        });


        Image image = new Image(getClass().getResource("/images/dungeon.gif").toExternalForm());
        ImageView background = new ImageView(image);
        background.setPreserveRatio(false);
        background.setSmooth(true);
        background.setCache(true);
        background.fitWidthProperty().bind(startPane.widthProperty());
        background.fitHeightProperty().bind(startPane.heightProperty());


        VBox buttons = new VBox(20);
        buttons.setMaxWidth(225);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(startButton, quitButton);

        BorderPane layout = new BorderPane();
        layout.setTop(title);
        layout.setCenter(buttons);
        BorderPane.setAlignment(title, Pos.TOP_CENTER);

        startPane.getChildren().addAll(background, layout);
    }

    private Scene createGameScene(Stage stage){
        Scene gameScene = new Scene(gameWindow);
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        Scene scene = new Scene(gameWindow, gameData.getDisplayWidth(), gameData.getDisplayHeight(), Color.TRANSPARENT);
        gameWindow.prefWidthProperty().bind(scene.widthProperty());
        gameWindow.prefHeightProperty().bind(scene.heightProperty());

        scene.setOnMouseMoved(event -> {
            gameData.getKeys().setMouseMoved(true);
            GameKeys.setMousePosition(event.getSceneX(), event.getSceneY());
        });

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.ESCAPE){
                gameData.setPausedBox();
            } else {
                setKey(event.getCode(), true);
            }
        });
        scene.setOnKeyReleased(event -> setKey(event.getCode(), false));

        getPluginServices().forEach(plugin -> plugin.start(gameData, world));

        render();
        return gameScene;
    }




    private void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameData.isPaused()){
                    update();
                    draw();
                    gameData.getKeys().update();
                }
            }
        }.start();
    }

    private void update() {
        getEntityProcessingServices().forEach(service -> service.process(gameData, world));
        getPostEntityProcessor().forEach(service -> service.process(gameData, world));
    }

    private void drawLightingMask() {
        GraphicsContext gcLight = lightMaskCanvas.getGraphicsContext2D();
        gcLight.setGlobalBlendMode(BlendMode.SRC_OVER);
        gcLight.clearRect(0, 0, lightMaskCanvas.getWidth(), lightMaskCanvas.getHeight());

        // Draw noise background
        gcLight.drawImage(noiseImage, 0, 0, lightMaskCanvas.getWidth(), lightMaskCanvas.getHeight());

        // Opacity value here alongside base value in generateNoiseImage controls contrast in between light and non-light areas
        gcLight.setFill(Color.color(0, 0, 0, 0.85));
        gcLight.fillRect(0, 0, lightMaskCanvas.getWidth(), lightMaskCanvas.getHeight());

        // Draw light cutouts
        gcLight.setFill(Color.color(1, 1, 1, 1));
        for (Entity entity : world.getEntities(CommonLightSource.class)) {
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
                gcLight.closePath();
                gcLight.fill();
            }
        }

        lightMaskCanvas.setBlendMode(BlendMode.MULTIPLY);
        gameData.getLightLayer().getChildren().setAll(lightMaskCanvas);

    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if (!world.getEntities().contains(polygonEntity)) {
                gameData.getPolygonLayer().getChildren().remove(polygons.get(polygonEntity));
                polygons.remove(polygonEntity);

                ImageView removedImage = images.remove(polygonEntity);
                if (removedImage != null) {
                    gameData.getPolygonLayer().getChildren().remove(removedImage);
                }
            }
        }

        for (Entity entity : world.getEntities()) {
            if (entity instanceof CommonLightSource) continue;

            Polygon polygon = polygons.computeIfAbsent(entity, e -> {
                Polygon newPoly = new Polygon(e.getPolygonCoordinates());
                gameData.getPolygonLayer().getChildren().add(newPoly);
                return newPoly;
            });

            handlePolygonCoordsPreDrawing(polygon, entity);
            drawLightingMask();

            Image image = entity.getImage();
            if (image != null) {
                ImageView imageView = images.get(entity);
                if (imageView == null) {
                    imageView = new ImageView();
                    images.put(entity, imageView);
                    gameData.getPolygonLayer().getChildren().add(imageView);
                } else if (imageView.getImage() != image) {
                    imageView.setImage(image);
                }

                imageView.setTranslateX(entity.getX() - image.getWidth()/2);
                imageView.setTranslateY(entity.getY()- image.getHeight()/2);
            } else {
                ImageView imageView = images.remove(entity);
                if(imageView != null){
                    gameData.getPolygonLayer().getChildren().remove(imageView);
                }
            }
        }
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
        polygon.setFill(entity.getPaint());
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

    private void setKey(KeyCode code, boolean pressed) {
        if (code == KeyCode.A) gameData.getKeys().setKey(GameKeys.LEFT, pressed);
        if (code == KeyCode.D) gameData.getKeys().setKey(GameKeys.RIGHT, pressed);
        if (code == KeyCode.W) gameData.getKeys().setKey(GameKeys.UP, pressed);
        if (code == KeyCode.SPACE) gameData.getKeys().setKey(GameKeys.SPACE, pressed);
        if (code == KeyCode.S) gameData.getKeys().setKey(GameKeys.DOWN, pressed);
        if (code == KeyCode.E) gameData.getKeys().setKey(GameKeys.INTERACT, pressed);
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessor() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
