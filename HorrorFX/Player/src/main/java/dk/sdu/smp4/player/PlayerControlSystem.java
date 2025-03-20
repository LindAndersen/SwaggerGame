package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)) {
            Player player = (Player) entity;
            player.setPreviousX(player.getX());
            player.setPreviousY(player.getY());

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                if (player.getRotation() != 180){
                    player.setRotation(180);
                }
                player.setX(player.getX()-1);
                player.setPreviousX(player.getX()+1.00001);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                if (player.getRotation() != 0) {
                    player.setRotation(0);
                }
                player.setX(player.getX()+1);
                player.setPreviousX(player.getX()-1.00001);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)){
                if (player.getRotation() != 270){
                    player.setRotation(270);
                }
                player.setY(player.getY()-1);
                player.setPreviousY(player.getY()+1.00001);
            }
            if (gameData.getKeys().isDown(GameKeys.DOWN)){
                if (player.getRotation() != 90){
                    player.setRotation(90);
                }
                player.setY(player.getY()+1);
                player.setPreviousY(player.getY()-1.00001);
            }
            if(gameData.getKeys().isDown(GameKeys.SPACE)) {
                System.out.println("Toggle flashlight");
            }

            if(gameData.getKeys().isDown(GameKeys.E)) {
                for(IQuestInteractable interactable : getEntityQuestInteractables())
                {
                    interactable.interact(player, gameData, world);
                }
            }

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


            for (IPlayerLightProcessor spi : getEntityPlayerLights())
            {
                spi.processPlayerLight(player, gameData, world);
            }
        }
    }

    private Collection<? extends IPlayerLightProcessor> getEntityPlayerLights() {
        return ServiceLoader.load(IPlayerLightProcessor.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IQuestInteractable> getEntityQuestInteractables() {
        return ServiceLoader.load(IQuestInteractable.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
