package dk.sdu.smp4.key;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.inventory.services.IHasInventory;

public class KeyControlSystem implements IQuestInteractable {
    @Override
    public void interact(Entity player, GameData gameData, World world) {
        if (!(player instanceof IHasInventory iHasInventory))
        {
            return;
        }
        for (Entity keyIn : world.getEntities(Key.class)) {
            Key key = (Key) keyIn;
            String keyId = key.getInventoryIdentifier();

            if (isKeyWithinReach(player, key) && gameData.getKeys().isPressed(GameKeys.INTERACT)) {
                iHasInventory.getInventory().add(keyId, key);
                world.removeEntity(key);

                // Show popup
                String displayKeyName = prettifyKeyName(keyId);
                gameData.setQuestPane("Key Acquired", "You picked up a " + displayKeyName + ".");

                break;
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

    private String prettifyKeyName(String keyId) {
        if (keyId == null) return "key";
        return keyId.replace("_", " ").replace("key", "Key").trim();
    }
}