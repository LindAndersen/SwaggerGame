package dk.sdu.smp4.playercirclelight;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.data.CommonPlayerLight;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

public class PlayerCircleLightProcessor implements IPlayerLightProcessor {
    @Override
    public void processPlayerLight(Entity player, GameData gameData, World world) {

        for(Entity lightEntity : world.getEntities(CircleLight.class)) {
            lightEntity.setX(player.getX());
            lightEntity.setY(player.getY());
            lightEntity.setRadius(player.getRadius()*3);
            lightEntity.setRotation(player.getRotation());
        }
    }
}
