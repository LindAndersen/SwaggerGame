package dk.sdu.smp4.key;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

public class KeyControlSystem implements IQuestInteractable {
    @Override
    public void interact(Entity player, GameData gameData, World world) {
        for (Entity key : world.getEntities(Key.class)) {
            if (isKeyWithinReach(player, key) && gameData.getKeys().isPressed(GameKeys.INTERACT)) {
                System.out.println("Key picked up: " + key.getProperty("keyId"));
                player.getInventory().add((String) key.getProperty("keyId"));
                world.removeEntity(key);
                displayKeyPickupMessage(key);
            }
        }
    }

    @Override
    public void consume(GameData gameData, World world) {
    }

    private boolean isKeyWithinReach(Entity player, Entity key) {
        double distance = Math.sqrt(Math.pow(key.getX() - player.getX(), 2) + Math.pow(key.getY() - player.getY(), 2));
        return distance < 40;
    }

    private void displayKeyPickupMessage(Entity key) {
        System.out.println("You have picked up a key: " + key.getProperty("keyId"));
    }
}