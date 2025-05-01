package dk.sdu.smp4.common.gui.elements;

import dk.sdu.smp4.common.Services.GUI.IFlashlightBar;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FlashlightBar extends StackPane implements IFlashlightBar {

    private final Rectangle background;
    private final Rectangle fill;

    private static final double WIDTH = 100;
    private static final double HEIGHT = 12;

    public FlashlightBar() {
        background = new Rectangle(WIDTH, HEIGHT, Color.DARKGRAY);
        background.setArcWidth(8);
        background.setArcHeight(8);

        fill = new Rectangle(WIDTH, HEIGHT, Color.LIMEGREEN);
        fill.setArcWidth(8);
        fill.setArcHeight(8);

        // Anchor fill to the left
        StackPane.setAlignment(fill, Pos.CENTER_LEFT);
        fill.setTranslateX(0);

        getChildren().addAll(background, fill);
    }


    public void update(double percentage) {
        percentage = Math.max(0, Math.min(1.0, percentage));
        fill.setWidth(WIDTH * percentage);

        if (percentage > 0.5) {
            fill.setFill(Color.LIMEGREEN);
        } else if (percentage > 0.25) {
            fill.setFill(Color.ORANGE);
        } else {
            fill.setFill(Color.RED);
        }
    }
}
