package dk.sdu.smp4.keyanddoor.door;

import dk.sdu.smp4.common.data.HardEntity;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;


public class Door extends HardEntity {
    private InventorySlotItems requiredKey;

    public void setRequiredKey(InventorySlotItems requiredKey) {
        this.requiredKey = requiredKey;
    }

    public InventorySlotItems getRequiredKey() {
        return requiredKey;
    }
}
