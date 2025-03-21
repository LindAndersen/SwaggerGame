package dk.sdu.smp4.playercirclelight;

import dk.sdu.smp4.common.data.DynamicEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.data.CommonPlayerLight;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

public class PlayerCircleLightProcessor implements IPlayerLightProcessor {
    @Override
    public void processPlayerLight(DynamicEntity player, GameData gameData, World world) {

        for(Entity entity : world.getEntities(CircleLight.class)) {
            DynamicEntity lightEntity = (DynamicEntity) entity;
            lightEntity.setX(player.getX());
            lightEntity.setY(player.getY());
            lightEntity.setPreviousX(player.getPreviousX());
            lightEntity.setPreviousY(player.getPreviousY());
            lightEntity.setRadius(player.getRadius()*3);
            lightEntity.setRotation(player.getRotation());
        }
    }
}
