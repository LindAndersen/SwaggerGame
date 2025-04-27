package dk.sdu.smp4.keyanddoor.door;

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

        for (Entity doorEntity : world.getEntities(Door.class)) {
            Door door = (Door) doorEntity;
            if (Utility.isEntitiesWithinReach(player, door)) {

                if (inventorySPI.containsInventoryForEntity(player) && inventorySPI.contains(player, door.getRequiredKey())) {
                    // Show unlock popup
                    gameData.setQuestPane("Unlocked", "Door unlocked with the "+door.getRequiredKey()+"!");

                    // Unlock door
                    inventorySPI.remove(player, door.getRequiredKey());
                    world.removeEntity(door);
                } else {
                    // Show locked popup
                    gameData.setQuestPane("Locked", "The door is locked. You need a "+door.getRequiredKey()+"!");
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
}
