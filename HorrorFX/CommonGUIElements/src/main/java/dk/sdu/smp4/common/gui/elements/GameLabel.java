package dk.sdu.smp4.common.gui.elements;

import javafx.scene.control.Label;

public class GameLabel extends Label {

    public enum Type {
        TITLE,
        DESCRIPTION,
        DEFAULT
    }

    public GameLabel(String text) {
        this(text, Type.DEFAULT);
    }

    public GameLabel(String text, Type type) {
        super(text);
        getStyleClass().add("game-label");

        switch (type) {
            case TITLE -> getStyleClass().add("game-label-title");
            case DESCRIPTION -> getStyleClass().add("game-label-description");
        }
    }
}