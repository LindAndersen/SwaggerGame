package dk.sdu.smp4.main;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.Services.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class Main extends Application {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());


        Scene scene = new Scene(gameWindow);
        scene.setOnMouseMoved((MouseEvent event) -> {
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            GameKeys.setMousePosition(mouseX, mouseY);
        });
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
            if (event.getCode().equals(KeyCode.S)) {
                gameData.getKeys().setKey(GameKeys.DOWN, true);
            }
            if (event.getCode().equals(KeyCode.E)) {
                gameData.getKeys().setKey(GameKeys.INTERACT, true);
            }

        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
            if (event.getCode().equals(KeyCode.S)) {
                gameData.getKeys().setKey(GameKeys.DOWN, false);
            }
            if (event.getCode().equals(KeyCode.E)) {
                gameData.getKeys().setKey(GameKeys.INTERACT, false);
            }

        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        render();
        window.setScene(scene);
        window.setTitle("HorrorFX");
        window.show();
    }

    private void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessor : getPostEntityProcessor()){ //Should probably be refactored
            postEntityProcessor.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }

            polygon.getTransforms().clear();
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            if (entity.isShouldRotateAlternative())
            {
                polygon.getTransforms().add(new Rotate(entity.getRotation(), 0, 0));
            } else
            {
                polygon.setRotate(entity.getRotation());
            }
            polygon.setFill(entity.getPaint());
        }

    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessor(){
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}