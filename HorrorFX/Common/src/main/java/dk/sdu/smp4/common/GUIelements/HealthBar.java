package dk.sdu.smp4.common.GUIelements;

import dk.sdu.smp4.common.events.EventBus;
import dk.sdu.smp4.common.events.UpdateHUDLifeEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HealthBar extends HBox {
    private final int defaultLives = 3;

    public HealthBar() {
        super(5);
        setPadding(new Insets(10));
        setAlignment(Pos.TOP_LEFT);
        updateLifeHUD(defaultLives, defaultLives);
        EventBus.subscribe(UpdateHUDLifeEvent.class, event -> {
            updateLifeHUD(event.getLives(), defaultLives);
        });
    }

    private void updateLifeHUD(int lives, int maxLives) {
        getChildren().clear();
        for (int i = 0; i < maxLives; i++) {
            Image heartImage = new Image(getClass().getResourceAsStream(
                    i < lives ? "/images/heart_full.png" : "/images/heart_empty.png"
            ), 32, 32, true, true);
            ImageView heartView = new ImageView(heartImage);
            getChildren().add(heartView);
        }
    }
}
