package dk.sdu.smp4.inventory.data;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.events.data.InventoryUpdateEvent;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class Inventory {
    private Map<InventorySlotItems, InventorySlot> inventory;
    private static final int SIZE = 8;
    private IEventBus eventBus;

    public Inventory() {
        eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        inventory = new HashMap<>();
    }

    public void add(InventorySlotItems name, Entity entity)
    {
        if (inventory.containsKey(name))
        {
            InventorySlot slot = inventory.get(name);
            slot.amount++;
            eventBus.post(new InventoryUpdateEvent(slot.index, entity.getImage(), slot.amount, name));
        }else if (inventory.size() < SIZE +1)
        {
            InventorySlot slot = new InventorySlot(entity, 1);
            inventory.put(name, slot);
            eventBus.post(new InventoryUpdateEvent(slot.index, entity.getImage(), slot.amount, name));
        } else
        {
            System.out.println("Too many things in inventory!");
        }
    }

    public void remove(InventorySlotItems name, int amount)
    {
        if(inventory.containsKey(name)){
            InventorySlot slot = inventory.get(name);
            slot.remove(amount);
            if (slot.amount == 0)
            {
                eventBus.post(new InventoryUpdateEvent(slot.index, null, 0, name));
            } else
            {
                eventBus.post(new InventoryUpdateEvent(slot.index, null, slot.amount, name));
            }
        }
    }

    public boolean contains(InventorySlotItems name)
    {
        return inventory.containsKey(name);
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    static class InventorySlot
    {
        static int globalIndex = 0;
        Entity entity = null;
        int amount = 0;
        int index = 0;

        public InventorySlot(Entity entity, int amount)
        {
            this.entity = entity;
            this.amount = amount;
            index = globalIndex;
            globalIndex++;
        }

        void remove(int amount)
        {
            this.amount -= amount;
            if (amount == 0)
            {
                entity = null;
            }
            globalIndex--;
        }
    }
}
