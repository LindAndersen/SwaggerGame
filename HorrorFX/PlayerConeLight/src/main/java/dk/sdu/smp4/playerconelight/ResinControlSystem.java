package dk.sdu.smp4.playerconelight;

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

public class ResinControlSystem implements IQuestInteractable {
    @Override
    public void interact(Entity player, GameData gameData, World world) {
        InventorySPI inventorySPI = getInventorySPIs().stream().findFirst().orElse(null);
        if (inventorySPI == null) {return;}

        if (!inventorySPI.containsInventoryForEntity(player))
        {
            return;
        }

        for (Entity resinEntity : world.getEntities(Resin.class)) {
            Resin resin = (Resin) resinEntity;
            InventorySlotItems resinId = resin.getInventoryIdentifier();

            if (Utility.isEntitiesWithinReach(player, resin)) {
                inventorySPI.add(player, resin, resinId);
                world.removeEntity(resin);
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
