package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.data.DynamicEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

import java.util.ArrayList;
import java.util.List;

public class PlayerConeLightProcessor implements IPlayerLightProcessor {
    @Override
    public void processPlayerLight(DynamicEntity player, GameData gameData, World world) {
        for (Entity lightEntity : world.getEntities(ConeLight.class)) {
            lightEntity.setX(player.getX());
            lightEntity.setY(player.getY());
            lightEntity.setRotation(player.getRotation());
            lightEntity.setShouldRotateAlternative(true);
        }
    }
}
