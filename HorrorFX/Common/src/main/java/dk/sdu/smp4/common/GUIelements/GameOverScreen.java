package dk.sdu.smp4.common.GUIelements;

import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.events.EventBus;
import dk.sdu.smp4.common.events.RestartGameEvent;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameOverScreen extends VBox {
    public GameOverScreen(GameData gameData){
        setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.85);");
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.getStyleClass().add("title-label");

        Button goAgainButton = new Button("Go Again");
        goAgainButton.getStyleClass().add("menu-button");
        goAgainButton.setOnAction(e -> {
            gameData.getTextLayer().getChildren().remove(this);
            EventBus.post(new RestartGameEvent());
        });

        Button quitButton = new Button("Quit");
        quitButton.getStyleClass().add("menu-button");
        quitButton.setOnAction(e -> System.exit(0));

        getChildren().addAll(gameOverLabel, goAgainButton, quitButton);
    }
}
