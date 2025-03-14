package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;

public class PlayerControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                if (player.getRotation() != 180){
                    player.setRotation(180);
                }
                if (!player.getBlockedDirection(1)){
                    player.setX(player.getX()-1);
                }
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                if (player.getRotation() != 0) {
                    player.setRotation(0);
                }
                if (!player.getBlockedDirection(2)){
                    player.setX(player.getX()+1);
                }
            }
            if (gameData.getKeys().isDown(GameKeys.UP)){
                if (player.getRotation() != 270){
                    player.setRotation(270);
                }
                if (!player.getBlockedDirection(0)){
                    player.setY(player.getY()-1);
                }
            }
            if (gameData.getKeys().isDown(GameKeys.DOWN)){
                if (player.getRotation() != 90){
                    player.setRotation(90);
                }
                if (!player.getBlockedDirection(3)){
                    player.setY(player.getY()+1);
                }
            }
            //if(gameData.getKeys().isDown(GameKeys.SPACE)) {

            //}

            if (player.getX() < 0) {
                player.setX(1);
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth()-1);
            }

            if (player.getY() < 0) {
                player.setY(1);
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight()-1);
            }


        }
    }
}
