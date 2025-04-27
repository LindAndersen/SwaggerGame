package dk.sdu.smp4.common.gui;

import javafx.scene.control.Button;

public class GameButton extends Button {

    public GameButton(String text) {
        super(text);

        //size and style
        setPrefWidth(200);
        setPrefHeight(40);
        getStyleClass().add("game-button");

        setStyle("-fx-font-size: 18px; -fx-text-fill: Brown; -fx-background-color: beige;" +
                "-fx-border-color: Black; -fx-border-width: 2px; -fx-background-radius: 5;" +
                "-fx-border-radius: 5;");
    }
}
