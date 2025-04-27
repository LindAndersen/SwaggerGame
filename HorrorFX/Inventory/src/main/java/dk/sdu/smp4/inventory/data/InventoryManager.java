package dk.sdu.smp4.inventory.data;

import dk.sdu.smp4.common.data.Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryManager {
    private static InventoryManager instance = new InventoryManager();
    private Map<Entity, Inventory> inventories;

    private InventoryManager() {
        inventories = new ConcurrentHashMap<>();
    }

    public static InventoryManager getInstance() {
        return instance;
    }

    public Inventory getInventory(Entity entity) {
        return inventories.get(entity);
    }

    public void addInventory(Entity entity) {
        inventories.put(entity, new Inventory());
    }

    public void removeInventory(Entity entity) {
        inventories.remove(entity);
    }

    public boolean containsInventory(Entity entity) {
        return inventories.containsKey(entity);
    }
}
