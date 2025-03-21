import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

module Door {
    requires CommonInteractable;
    requires Common;
    provides IQuestInteractable with dk.sdu.smp4.door.DoorControlSystem;
    provides IGamePluginService with dk.sdu.smp4.door.DoorPlugin;
}