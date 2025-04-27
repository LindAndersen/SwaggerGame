package dk.sdu.smp4.inventory.data;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.interactable.Services.InventorySPI;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

import java.util.Map;
import java.util.Optional;

public class InventoryProvider implements InventorySPI {

    @Override
    public void addInventoryForEntity(Entity entity) {
        InventoryManager.getInstance().addInventory(entity);
    }

    @Override
    public boolean containsInventoryForEntity(Entity entity) {
        return InventoryManager.getInstance().containsInventory(entity);
    }

    @Override
    public void add(Entity receiver, Entity inventoryItem, InventorySlotItems itemType) {
        InventoryManager inventoryManager = InventoryManager.getInstance();
        Optional.ofNullable(inventoryManager.getInventory(receiver)).ifPresent(inventory -> inventory.add(itemType, inventoryItem));
    }

    @Override
    public void remove(Entity receiver, InventorySlotItems itemType) {
        InventoryManager inventoryManager = InventoryManager.getInstance();
        Optional.ofNullable(inventoryManager.getInventory(receiver)).ifPresent(inventory -> inventory.remove(itemType));
    }

    @Override
    public boolean contains(Entity receiver, InventorySlotItems itemType) {
        return InventoryManager.getInstance().getInventory(receiver).contains(itemType);
    }
}
