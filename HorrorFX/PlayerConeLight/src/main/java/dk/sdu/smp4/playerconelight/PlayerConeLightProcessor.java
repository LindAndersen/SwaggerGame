package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

public class PlayerConeLightProcessor implements IPlayerLightProcessor {
    @Override
    public void processPlayerLight(SoftEntity player, GameData gameData, World world) {

        for (Entity lightEntity : world.getEntities(ConeLight.class)) {
            ConeLight coneLight = (ConeLight) lightEntity;
            coneLight.setX(player.getX());
            coneLight.setY(player.getY());
            coneLight.setRotation(player.getRotation());
            coneLight.setShouldRotateAlternative(true);

            //coneLight.tick();

            //System.out.println(coneLight.getFlashLightCurrentTime());
        }
    }
}
