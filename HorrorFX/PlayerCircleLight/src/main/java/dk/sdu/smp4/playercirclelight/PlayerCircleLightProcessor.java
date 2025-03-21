package dk.sdu.smp4.playercirclelight;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

public class PlayerCircleLightProcessor implements IPlayerLightProcessor {
    @Override
    public void processPlayerLight(SoftEntity player, GameData gameData, World world) {

        for(Entity entity : world.getEntities(CircleLight.class)) {
            SoftEntity lightEntity = (SoftEntity) entity;
            lightEntity.setX(player.getX());
            lightEntity.setY(player.getY());
            lightEntity.setPreviousX(player.getPreviousX());
            lightEntity.setPreviousY(player.getPreviousY());
            lightEntity.setRadius(player.getRadius()*3);
            lightEntity.setRotation(player.getRotation());
        }
    }
}
