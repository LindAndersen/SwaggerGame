package dk.sdu.smp4.common.gui.elements;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class GamePopup extends VBox {

    public GamePopup() {
        setMinSize(400, 300);
        setSpacing(20);
        setAlignment(Pos.CENTER);
        getStyleClass().add("game-popup");
    }
}
