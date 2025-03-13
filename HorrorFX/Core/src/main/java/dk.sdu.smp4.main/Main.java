package dk.sdu.smp4.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private final Pane gameWindow = new Pane();

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        System.out.println("Hello Bitch");

        Scene scene = new Scene(gameWindow);

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }
}