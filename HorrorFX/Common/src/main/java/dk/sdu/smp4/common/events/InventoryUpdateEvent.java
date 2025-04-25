package dk.sdu.smp4.common.events;

import javafx.scene.image.Image;

public class InventoryUpdateEvent {
    private int index;
    private Image icon;
    private int quantity;

    public InventoryUpdateEvent(int index, Image icon, int quantity) {
        this.index = index;
        this.icon = icon;
        this.quantity = quantity;
    }

    public int getIndex() {
        return index;
    }

    public Image getIcon() {
        return icon;
    }

    public int getQuantity() {
        return quantity;
    }
}
