package dk.sdu.smp4.common.GUIelements;

import dk.sdu.smp4.common.events.*;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.*;

public class InventoryHUD extends HBox {

    private static final int SLOT_SIZE = 48;
    private static final int SLOT_COUNT = 8;

    private final Map<UUID, StackPane> slots = new HashMap<>();

    public InventoryHUD() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setStyle("-fx-padding: 10;");

        for (int i = 0; i < SLOT_COUNT; i++) {
            StackPane slot = createSlot();
            slots.put(null, slot);
            getChildren().add(slot);
        }

        EventBus.subscribe(InventoryUpdateEvent.class, event -> {
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

    public void setItemInSlot(int index, Image icon, int quantity) {
        if (index < 0 || index >= slots.size()) return;

        StackPane slot = slots.get(index);

        ImageView itemImage = (ImageView) slot.lookup("#itemImage");
        Text quantityText = (Text) slot.lookup("#quantityText");

        itemImage.setImage(icon);
        quantityText.setText(quantity > 1 ? String.valueOf(quantity) : "");
    }

}