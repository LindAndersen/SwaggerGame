package dk.sdu.smp4.common.gui.util;

import dk.sdu.smp4.common.Services.GUI.PolygonColor;
import javafx.scene.paint.Color;

public class ColorConverter {
    public static Color toJavaFXColor(PolygonColor polygonColor) {
        return switch (polygonColor) {
            case RED -> Color.RED;
            case BLACK -> Color.BLACK;
            case GOLD -> Color.GOLD;
            case DARKSLATEBLUE -> Color.DARKSLATEBLUE;
            default -> Color.GREEN;
        };
    }
}
