import dk.sdu.smp4.common.interactable.Services.InventorySPI;
import dk.sdu.smp4.inventory.data.InventoryProvider;

module Inventory {
    exports dk.sdu.smp4.inventory.data;
    requires CommonInteractable;
    requires Common;
    provides InventorySPI with InventoryProvider;
}