package dk.sdu.smp4.keyanddoor.key;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.common.interactable.Services.InventorySPI;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;
import dk.sdu.smp4.commonquest.data.Utility;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class KeyControlSystem implements IQuestInteractable {
    @Override
    public void interact(Entity player, GameData gameData, World world) {
        InventorySPI inventorySPI = getInventorySPIs().stream().findFirst().orElse(null);
        if (inventorySPI == null) {return;}

        if (!inventorySPI.containsInventoryForEntity(player))
        {
            return;
        }

        for (Entity keyIn : world.getEntities(Key.class)) {
            Key key = (Key) keyIn;
            InventorySlotItems keyId = key.getInventoryIdentifier();

            if (Utility.isEntitiesWithinReach(player, key)) {
                inventorySPI.add(player, key, keyId);
                world.removeEntity(key);

                // Show popup
                String displayKeyName = prettifyKeyName(String.valueOf(keyId));
                gameData.setQuestPane("Key Acquired", "You picked up a " + displayKeyName + ".");

                break;
            }
        }
    }


    @Override
    public void consume(GameData gameData, World world) {
    }

    private String prettifyKeyName(String keyId) {
        if (keyId == null) return "key";
        return keyId.replace("_", " ").replace("key", "Key").trim();
    }

    private Collection<? extends InventorySPI> getInventorySPIs() {
        return ServiceLoader.load(InventorySPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}