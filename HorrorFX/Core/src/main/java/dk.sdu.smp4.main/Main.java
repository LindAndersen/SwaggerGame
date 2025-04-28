package dk.sdu.smp4.main;

import dk.sdu.smp4.common.data.*;
import dk.sdu.smp4.common.gui.services.GUIManager;
import dk.sdu.smp4.common.gui.services.Renderer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private final World world = new World();
    private final GameData gameData = new GameData();

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) {
        GUIManager guiManager = new GUIManager(gameData, stage);
        Renderer renderer = new Renderer(world, gameData, guiManager);
        renderer.start();
    }
}
