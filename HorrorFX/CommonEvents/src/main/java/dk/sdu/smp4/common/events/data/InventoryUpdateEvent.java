package dk.sdu.smp4.common.events.data;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

public class InventoryUpdateEvent {
    private int index;
    private EntityImage icon;
    private int quantity;
    private InventorySlotItems type;

    public InventoryUpdateEvent(int index, EntityImage icon, int quantity, InventorySlotItems type)
    {
        this.index = index;
        this.icon = icon;
        this.quantity = quantity;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public EntityImage getIcon() {
        return icon;
    }

    public int getQuantity() {
        return quantity;
    }

    public InventorySlotItems getType() {
        return type;
    }
}
