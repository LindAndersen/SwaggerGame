package dk.sdu.smp4.playercirclelight;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;


public class PlayerCircleLightPlugin implements IPlayerLightPlugin {

    @Override
    public void createPlayerLight(SoftEntity player, World world) {
        CircleLight lightEntity = new CircleLight();

        lightEntity.setX(player.getX());
        lightEntity.setY(player.getY());
        lightEntity.setSolid(false);
        lightEntity.setRadius(player.getRadius());
        lightEntity.setRotation(player.getRotation());

        world.addEntity(lightEntity);
    }
}
