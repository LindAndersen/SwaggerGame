package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;

public class PlayerConeLightPlugin implements IPlayerLightPlugin {
    @Override
    public void createPlayerLight(SoftEntity player, GameData gameData, World world) {
        ConeLight lightEntity = new ConeLight();

        lightEntity.setX(player.getX());
        lightEntity.setY(player.getY());
        lightEntity.setRadius(player.getRadius()*30);
        lightEntity.setSolid(false);
        lightEntity.setRotation(player.getRotation());

        world.addEntity(lightEntity);
    }
}
