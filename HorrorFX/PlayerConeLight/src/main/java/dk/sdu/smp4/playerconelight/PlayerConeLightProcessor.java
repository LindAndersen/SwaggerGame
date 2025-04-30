package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.Services.GUI.IGUIManager;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.InventorySPI;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class PlayerConeLightProcessor implements IPlayerLightProcessor {
    @Override
    public void processPlayerLight(SoftEntity player, GameData gameData, World world) {

        for (Entity lightEntity : world.getEntities(ConeLight.class)) {
            ConeLight coneLight = (ConeLight) lightEntity;
            coneLight.setX(player.getX());
            coneLight.setY(player.getY());
            coneLight.setRotation(player.getRotation());
            coneLight.setShouldRotateAlternative(true);

            if (coneLight.isOn()) process(coneLight);
        }
    }

    private void process(ConeLight coneLight)
    {
        coneLight.tick();
        getGUIManagerSPI().stream().findFirst().ifPresent(guiManager -> guiManager.getFlashlightBar().update(coneLight.getPercentLifeTime()));
    }

    private Collection<? extends IGUIManager> getGUIManagerSPI() {
        return ServiceLoader.load(IGUIManager.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
