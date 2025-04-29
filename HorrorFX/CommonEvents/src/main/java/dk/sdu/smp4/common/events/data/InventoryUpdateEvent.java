package dk.sdu.smp4.common.events.data;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

public class InventoryUpdateEvent {
    private int index;
    private EntityImage icon;
    private int quantity;
    private boolean wasPickedup;
    private InventorySlotItems type;

    public InventoryUpdateEvent(int index, EntityImage icon, int quantity, boolean wasPickedup, InventorySlotItems type)
    {
        this.index = index;
        this.icon = icon;
        this.quantity = quantity;
        this.type = type;
        this.wasPickedup = wasPickedup;
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

    public boolean getWasPickedup() {
        return wasPickedup;
    }

    public InventorySlotItems getType() {
        return type;
    }
}
