import dk.sdu.smp4.keyanddoor.key.*;
import dk.sdu.smp4.keyanddoor.door.*;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.common.interactable.Services.InventorySPI;

module KeyDoor {
    uses InventorySPI;
    requires Common;
    requires CommonQuest;
    requires CommonInteractable;
    requires javafx.graphics;
    provides IQuestInteractable with DoorControlSystem, KeyControlSystem;
    provides IGamePluginService with DoorPlugin, KeyPlugin;
}