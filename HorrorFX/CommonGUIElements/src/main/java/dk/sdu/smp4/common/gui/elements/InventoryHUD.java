package dk.sdu.smp4.common.gui.elements;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import dk.sdu.smp4.common.Services.GUI.IInventoryHUD;
import dk.sdu.smp4.common.events.data.InventoryUpdateEvent;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.common.gui.util.EntityImageConverter;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.*;
import java.util.stream.Collectors;

public class InventoryHUD extends HBox implements IInventoryHUD {

    private static final int SLOT_SIZE = 48;
    private static final int SLOT_COUNT = 8;

    private final List<StackPane> slots = new ArrayList<>();
    private IEventBus eventBus;

    public InventoryHUD() {
        eventBus = getEventBusSPI().stream().findFirst().orElse(null);

        setSpacing(10);
        setAlignment(Pos.CENTER);
        setStyle("-fx-padding: 10;");

        for (int i = 0; i < SLOT_COUNT; i++) {
            StackPane slot = createSlot();
            slots.add(slot);
            getChildren().add(slot);
        }

        eventBus.subscribe(InventoryUpdateEvent.class, event -> {
            setItemInSlot(event.getIndex(), event.getIcon(), event.getQuantity());
        });
    }

    private StackPane createSlot() {
        StackPane slot = new StackPane();
        slot.setPrefSize(SLOT_SIZE, SLOT_SIZE);

        Rectangle background = new Rectangle(SLOT_SIZE, SLOT_SIZE);
        background.setFill(Color.rgb(30, 30, 30));
        background.setStroke(Color.WHITE);
        background.setArcWidth(6);
        background.setArcHeight(6);

        ImageView itemImage = new ImageView();
        itemImage.setFitWidth(SLOT_SIZE - 8);
        itemImage.setFitHeight(SLOT_SIZE - 8);
        itemImage.setPreserveRatio(true);
        itemImage.setId("itemImage");

        Text quantity = new Text("");
        quantity.setFill(Color.WHITE);
        quantity.setId("quantityText");
        StackPane.setAlignment(quantity, Pos.BOTTOM_RIGHT);

        slot.getChildren().addAll(background, itemImage, quantity);
        return slot;
    }

    public void setItemInSlot(int index, EntityImage icon, int quantity) {
        if (index < 0 || index >= slots.size()) return;

        StackPane slot = slots.get(index);

        ImageView itemImage = (ImageView) slot.lookup("#itemImage");
        Text quantityText = (Text) slot.lookup("#quantityText");

        if (icon == null)
        {
            itemImage.setImage(null);
        }else
        {
            itemImage.setImage(EntityImageConverter.convertEntityImage(icon, icon.getResourceClass()));
        }
        quantityText.setText(quantity > 1 ? String.valueOf(quantity) : "");
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    @Override
    public void updateInventoryDisplay() {

    }
}