package dk.sdu.smp4.door;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

public class DoorControlSystem implements IQuestInteractable {

    @Override
    public void interact(Entity player, GameData gameData, World world) {
        for (Entity doorEntity : world.getEntities(Door.class)) {
            Door door = (Door) doorEntity;
            if (isDoorWithinReach(player, door) && gameData.getKeys().isPressed(GameKeys.INTERACT)) {

                if (player.getInventory().contains(door.getRequiredKey())) {
                    // Show unlock popup
                    gameData.setQuestPane("Unlocked", "Door unlocked with the "+door.getRequiredKey()+"!");

                    // Unlock door
                    player.getInventory().remove(door.getRequiredKey());
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

    private boolean isDoorWithinReach(Entity player, Entity door) {
        double distance = Math.sqrt(Math.pow(door.getX() - player.getX(), 2) + Math.pow(door.getY() - player.getY(), 2));
        return distance < 40;  // Adjust based on gameplay needs
    }
}
