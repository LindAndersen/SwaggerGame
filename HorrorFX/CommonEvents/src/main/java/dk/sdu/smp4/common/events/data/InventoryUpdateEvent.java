package dk.sdu.smp4.common.events.data;

import dk.sdu.smp4.common.interactable.data.InventorySlotItems;
import javafx.scene.image.Image;

public class InventoryUpdateEvent {
    private int index;
    private Image icon;
    private int quantity;
    private InventorySlotItems type;

    public InventoryUpdateEvent(int index, Image icon, int quantity, InventorySlotItems type)
    {
        this.index = index;
        this.icon = icon;
        this.quantity = quantity;
        this.type = type;
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

    public InventorySlotItems getType() {
        return type;
    }
}
