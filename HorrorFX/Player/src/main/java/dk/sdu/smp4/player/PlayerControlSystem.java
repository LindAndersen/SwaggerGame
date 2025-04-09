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
        for (Entity entity : world.getEntities(Player.class)) {
            Player player = (Player) entity;
            player.setPreviousX(player.getX());
            player.setPreviousY(player.getY());

            if (gameData.getKeys().isMouseMoved()){
                player.setRotation(Math.toDegrees(Math.atan2(GameKeys.mouseY- player.getY(), GameKeys.mouseX - player.getX())));
            }
            gameData.getKeys().setMouseMoved(false);

            //LEFT
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setX(player.getX()-1);
                player.setPreviousX(player.getX()+1.00001);
                if (player.getImage() != null && !player.getImage().equals(player.getMoveLeftImage())) {
                    player.setImage(player.getMoveLeftImage());
                }
            }

            //RIGHT
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setX(player.getX()+1);
                player.setPreviousX(player.getX()-1.00001);
                if (player.getImage() != null && !player.getImage().equals(player.getMoveRightImage())) {
                    player.setImage(player.getMoveRightImage());
                }
            }

            //UP
            if (gameData.getKeys().isDown(GameKeys.UP)){
                player.setY(player.getY()-1);
                player.setPreviousY(player.getY()+1.00001);
            }

            //DOWN
            if (gameData.getKeys().isDown(GameKeys.DOWN)){
                player.setY(player.getY()+1);
                player.setPreviousY(player.getY()-1.00001);
            }

            if(gameData.getKeys().isDown(GameKeys.SPACE)) {
                System.out.println("Toggle flashlight");
            }

            if(gameData.getKeys().isDown(GameKeys.INTERACT)) {
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
                player.setY(gameData.getDisplayHeight() - 1);
            }

            EventBus.post(new PlayerPositionEvent(player, player.getX(), player.getY()));

            for (IPlayerLightProcessor spi : getEntityPlayerLights())
            {
                spi.processPlayerLight(player, gameData, world);
            }

            EventBus.subscribe(PlayerHitEvent.class, event -> {
                if (event.getPlayer() instanceof Player ) {
                    if (player.isDead()) return;

                    player.loseLife();
                    System.out.println("Player hit! Lives left: " + player.getLives());
                    EventBus.post(new UpdateHUDLifeEvent(player.getLives()));

                    if (player.getLives() <= 0) {
                        player.setDead(true); // blokerer flere hits
                        EventBus.post(new GameOverEvent());
                    }
                }
            });
        }
    }

    private Collection<? extends IPlayerLightProcessor> getEntityPlayerLights() {
        return ServiceLoader.load(IPlayerLightProcessor.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IQuestInteractable> getEntityQuestInteractables() {
        return ServiceLoader.load(IQuestInteractable.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
