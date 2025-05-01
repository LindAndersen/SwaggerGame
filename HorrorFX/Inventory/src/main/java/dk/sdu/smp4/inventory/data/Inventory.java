package dk.sdu.smp4.inventory.data;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.events.data.InventoryUpdateEvent;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory {
    private Map<InventorySlotItems, InventorySlot> inventory;
    private static final int SIZE = 8;
    private IEventBus eventBus;

    private Queue<Integer> freeSlots;

    private int nextIndex = 0;

    public Inventory() {
        eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        inventory = new HashMap<>();
        freeSlots = new LinkedList<>();
    }

    public void add(InventorySlotItems name, Entity entity) {
        if (inventory.containsKey(name)) {
            InventorySlot slot = inventory.get(name);
            slot.amount++;
            eventBus.post(new InventoryUpdateEvent(slot.index, entity.getImage(), slot.amount, true, name));
        } else if (inventory.size() < SIZE) {
            int index;
            if (!freeSlots.isEmpty()) {
                index = freeSlots.poll(); // reuse a freed slot
            } else {
                index = nextIndex++;
            }

            InventorySlot slot = new InventorySlot(entity, 1, index);
            inventory.put(name, slot);
            eventBus.post(new InventoryUpdateEvent(slot.index, entity.getImage(), slot.amount, true, name));
        } else {
            System.out.println("Too many things in inventory!");
        }
    }

    public void remove(InventorySlotItems name, int amount) {
        if (inventory.containsKey(name)) {
            InventorySlot slot = inventory.get(name);
            slot.amount -= amount;

            if (slot.amount <= 0) {
                inventory.remove(name);
                freeSlots.add(slot.index);
                eventBus.post(new InventoryUpdateEvent(slot.index, null, 0, false, name));
            } else {
                eventBus.post(new InventoryUpdateEvent(slot.index, slot.entity.getImage(), slot.amount, false, name));
            }
        }
    }

    public boolean contains(InventorySlotItems name) {
        return inventory.containsKey(name);
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    static class InventorySlot {
        Entity entity;
        int amount;
        int index;

        public InventorySlot(Entity entity, int amount, int index) {
            this.entity = entity;
            this.amount = amount;
            this.index = index;
        }
    }
}
