package dk.sdu.smp4.door;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

public class DoorControlSystem implements IQuestInteractable {
    private static final String REQUIRED_KEY = "golden_key";

    @Override
    public void interact(Entity player, GameData gameData, World world) {
        for (Entity door : world.getEntities(Door.class)) {
            if (isDoorWithinReach(player, door) && gameData.getKeys().isPressed(GameKeys.INTERACT)) {

                if (player.getInventory().contains(REQUIRED_KEY)) {
                    System.out.println("Door unlocked with the golden key!");
                    player.getInventory().remove(REQUIRED_KEY);
                    world.removeEntity(door);
                    System.out.println("The door has been removed!");
                } else {
                    System.out.println("The door is locked. You need a golden key!");
                }
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
