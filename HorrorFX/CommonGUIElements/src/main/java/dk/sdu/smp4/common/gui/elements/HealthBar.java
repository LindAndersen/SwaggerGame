package dk.sdu.smp4.common.gui.elements;

import dk.sdu.smp4.common.Services.GUI.IHealthBar;
import dk.sdu.smp4.common.events.data.UpdateHUDLifeEvent;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.commonplayer.CommonPlayer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Collection;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class HealthBar extends HBox implements IHealthBar {
    private final int defaultLives = 3;
    private IEventBus eventBus;

    public HealthBar() {
        super(5);
        setPadding(new Insets(10));
        setAlignment(Pos.TOP_LEFT);
        setHealth(defaultLives, defaultLives);

        eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        assert eventBus != null;
        eventBus.subscribe(UpdateHUDLifeEvent.class, event -> {
            setHealth(event.getLives(), event.getMaxLives());
        });
    }

    @Override
    public void setHealth(int lives, int maxLives) {
        getChildren().clear();
        for (int i = 0; i < maxLives; i++) {
            Image heartImage = new Image(Objects.requireNonNull(CommonPlayer.class.getResourceAsStream(
                    i < lives ? "/heart_full.png" : "/heart_empty.png"
            )), 32, 32, true, true);
            ImageView heartView = new ImageView(heartImage);
            getChildren().add(heartView);
        }
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
