package dk.sdu.smp4.common.interactable.Services;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

public interface InventorySPI {
    void addInventoryForEntity(Entity entity);
    boolean containsInventoryForEntity(Entity entity);
    void add(Entity receiver, Entity inventoryItem, InventorySlotItems itemType);
    void remove(Entity receiver, InventorySlotItems itemType, int amount);
    boolean contains(Entity receiver, InventorySlotItems itemType);
}
