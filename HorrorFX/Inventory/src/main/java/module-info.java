import dk.sdu.smp4.common.interactable.Services.InventorySPI;
import dk.sdu.smp4.inventory.data.InventoryProvider;

module Inventory {
    uses dk.sdu.smp4.common.events.services.IEventBus;
    exports dk.sdu.smp4.inventory.data;
    requires CommonInteractable;
    requires Common;
    requires CommonEvents;
    provides InventorySPI with InventoryProvider;
}