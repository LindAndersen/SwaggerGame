package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;
import javafx.geometry.Point2D;

public class PlayerConeLightProcessor implements IPlayerLightProcessor {
    @Override
    public void processPlayerLight(SoftEntity player, GameData gameData, World world) {
        Point2D mouseLocal = gameData.getPane().sceneToLocal(GameKeys.getMouseX(), GameKeys.getMouseY());

        double dx = mouseLocal.getX() - player.getX();
        double dy = mouseLocal.getX() - player.getY();
        double angle = Math.toDegrees(Math.atan2(dy, dx));

        for (Entity lightEntity : world.getEntities(ConeLight.class)) {
            lightEntity.setX(player.getX());
            lightEntity.setY(player.getY());
            lightEntity.setRotation(angle); // Use angle, not player rotation
            player.setRotation(angle);
            lightEntity.setShouldRotateAlternative(true);
        }
    }
}
