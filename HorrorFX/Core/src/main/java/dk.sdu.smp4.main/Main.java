package dk.sdu.smp4.main;

import dk.sdu.smp4.common.gui.services.GameManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) {
        GameManager gameManager = new GameManager();
        gameManager.init(stage);
    }
}
