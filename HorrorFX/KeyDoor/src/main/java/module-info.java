import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.keyanddoor.key.*;
import dk.sdu.smp4.keyanddoor.door.*;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.common.interactable.Services.InventorySPI;

module KeyDoor {
    uses InventorySPI;
    uses dk.sdu.smp4.common.Services.GUI.IGUIManager;
    uses dk.sdu.smp4.map.services.IMapGenerator;
    requires Common;
    requires CommonQuest;
    requires CommonInteractable;
    requires CommonMap;
    provides IQuestInteractable with DoorControlSystem, KeyControlSystem;
    provides IEntityLoaderService with DoorPlugin, KeyPlugin;
}