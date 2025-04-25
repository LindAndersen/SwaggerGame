import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

module Door {
    requires Common;
    requires CommonInteractable;
    requires Inventory;
    provides IQuestInteractable with dk.sdu.smp4.door.DoorControlSystem;
    provides IGamePluginService with dk.sdu.smp4.door.DoorPlugin;
}