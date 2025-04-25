package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.events.*;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {
        float acceleration = 0.1f;
        float maxSpeed = 2.0f;
        float friction = 0.2f; //how quickly player stops

        for (Entity entity : world.getEntities(Player.class)) {
            Player player = (Player) entity;

            //update mouse position
            player.setRotation(Math.toDegrees(Math.atan2(GameKeys.mouseY - player.getY(), GameKeys.mouseX - player.getX())));

            // Adjust velocity
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setVelocityX(Math.max(player.getVelocityX() - acceleration, -maxSpeed));
                if (player.getImage() != null && !player.getImage().equals(player.getMoveLeftImage())) {
                    player.setImage(player.getMoveLeftImage());
                }
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setVelocityX(Math.min(player.getVelocityX() + acceleration, maxSpeed));
                if (player.getImage() != null && !player.getImage().equals(player.getMoveRightImage())) {
                    player.setImage(player.getMoveRightImage());
                }
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                player.setVelocityY(Math.max(player.getVelocityY() - acceleration, -maxSpeed));
            }
            if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                player.setVelocityY(Math.min(player.getVelocityY() + acceleration, maxSpeed));
            }

            // Apply friction
            if (!gameData.getKeys().isDown(GameKeys.LEFT) && !gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setVelocityX(approachZero(player.getVelocityX(), friction));
            }
            if (!gameData.getKeys().isDown(GameKeys.UP) && !gameData.getKeys().isDown(GameKeys.DOWN)) {
                player.setVelocityY(approachZero(player.getVelocityY(), friction));
            }

            // Update position
            player.setPreviousX(player.getX());
            player.setPreviousY(player.getY());
            player.setX(player.getX() + player.getVelocityX());
            player.setY(player.getY() + player.getVelocityY());

            // Out of bounds checks
            player.setX(Math.min(Math.max(player.getX(), 0), gameData.getDisplayWidth()));
            player.setY(Math.min(Math.max(player.getY(), 0), gameData.getDisplayHeight()));

            EventBus.post(new PlayerPositionEvent(player, player.getX(), player.getY()));

            for (IPlayerLightProcessor spi : getEntityPlayerLights()) {
                spi.processPlayerLight(player, gameData, world);
            }

            if(gameData.getKeys().isDown(GameKeys.INTERACT)) {
                for(IQuestInteractable interactable : getEntityQuestInteractables()) {
                    interactable.interact(player, gameData, world);
                }
            }

            if(gameData.getKeys().isDown(GameKeys.SPACE)) {
                System.out.println("Toggle flashlight");
            }
        }
    }
    //Prevent instant stop
    private float approachZero(float value, float decrement) {
        if (value > 0) {
            return Math.max(0, value - decrement);
        } else if (value < 0) {
            return Math.min(0, value + decrement);
        }
        return 0;
    }

    private Collection<? extends IPlayerLightProcessor> getEntityPlayerLights() {
        return ServiceLoader.load(IPlayerLightProcessor.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IQuestInteractable> getEntityQuestInteractables() {
        return ServiceLoader.load(IQuestInteractable.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
