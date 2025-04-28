package dk.sdu.smp4.keyanddoor.door;

import dk.sdu.smp4.common.Services.GUI.IGUIManager;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.common.interactable.Services.InventorySPI;
import dk.sdu.smp4.commonquest.data.Utility;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class DoorControlSystem implements IQuestInteractable {

    @Override
    public void interact(Entity player, GameData gameData, World world) {
        InventorySPI inventorySPI = getInventorySPIs().stream().findFirst().orElse(null);
        if (inventorySPI == null) {return;}

        IGUIManager guiManager = getGUIManagers().stream().findFirst().orElse(null);
        if (guiManager == null) {return;}

        for (Entity doorEntity : world.getEntities(Door.class)) {
            Door door = (Door) doorEntity;
            if (Utility.isEntitiesWithinReach(player, door)) {

                if (inventorySPI.containsInventoryForEntity(player) && inventorySPI.contains(player, door.getRequiredKey())) {
                    // Show unlock popup
                    guiManager.setQuestPane("Unlocked", "Door unlocked with the " + door.getRequiredKey() + "!");

                    // Unlock door
                    inventorySPI.remove(player, door.getRequiredKey(), 1);
                    world.removeEntity(door);
                } else {
                    // Show locked popup
                    guiManager.setQuestPane("Locked", "The door is locked. You need a " + door.getRequiredKey() + "!");
                }

                break;
            }
        }
    }


    @Override
    public void consume(GameData gameData, World world) {

    }

    private Collection<? extends InventorySPI> getInventorySPIs() {
        return ServiceLoader.load(InventorySPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends IGUIManager> getGUIManagers() {
        return ServiceLoader.load(IGUIManager.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
