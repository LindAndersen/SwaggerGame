package dk.sdu.smp4.common.GUIelements;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public abstract class GamePopup extends VBox {

    public GamePopup() {
        setMinSize(400, 300);
        setSpacing(20);
        setAlignment(Pos.CENTER);
        getStyleClass().add("game-popup");

        // Optional fallback inline styling
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.8);" +
                "-fx-border-color: white;" +
                "-fx-border-width: 2;" +
                "-fx-padding: 20;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;");
    }
}
