package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.events.data.PlayerPositionEvent;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;
import dk.sdu.smp4.commonplayerlight.services.IToggleableLight;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

enum Direction {
    NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST, IDLE
}

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        float acceleration = 0.3f;
        float maxSpeed = 2.0f;
        float friction = 0.1f;
        IEventBus eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        assert eventBus != null;

        for (Entity entity : world.getEntities(Player.class)) {
            Player player = (Player) entity;

            player.setRotation(Math.toDegrees(Math.atan2(GameKeys.mouseY - player.getY(), GameKeys.mouseX - player.getX())));

            player.setPreviousX(player.getX());
            player.setPreviousY(player.getY());

            player.setX(player.getX() + player.getVelocityX());
            player.setY(player.getY() + player.getVelocityY());

            player.setX(Math.min(Math.max(player.getX(), 0), gameData.getDisplayWidth()));
            player.setY(Math.min(Math.max(player.getY(), 0), gameData.getDisplayHeight()));

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setVelocityX(Math.max(player.getVelocityX() - acceleration, -maxSpeed));
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setVelocityX(Math.min(player.getVelocityX() + acceleration, maxSpeed));
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                player.setVelocityY(Math.max(player.getVelocityY() - acceleration, -maxSpeed));
            }
            if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                player.setVelocityY(Math.min(player.getVelocityY() + acceleration, maxSpeed));
            }

            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                for (IToggleableLight toggleableLight : getPlayerToggleableLights(world)) {
                    toggleableLight.toggle();
                }
            }

            if (!gameData.getKeys().isDown(GameKeys.LEFT) && !gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setVelocityX(approachZero(player.getVelocityX(), friction));
            }
            if (!gameData.getKeys().isDown(GameKeys.UP) && !gameData.getKeys().isDown(GameKeys.DOWN)) {
                player.setVelocityY(approachZero(player.getVelocityY(), friction));
            }

            Direction direction = getDirection(player.getVelocityX(), player.getVelocityY());
            double delta = gameData.getDelta();

            switch (direction) {
                case NORTH:
                    Player.moveNorthAnimation.update(delta);
                    player.setImage(Player.moveNorthAnimation.getCurrentFrame());
                    Player.setLastDirection(Direction.NORTH);
                    break;
                case NORTH_EAST:
                    Player.moveNorthEastAnimation.update(delta);
                    player.setImage(Player.moveNorthEastAnimation.getCurrentFrame());
                    Player.setLastDirection(Direction.NORTH_EAST);
                    break;
                case EAST:
                    Player.moveEastAnimation.update(delta);
                    player.setImage(Player.moveEastAnimation.getCurrentFrame());
                    Player.setLastDirection(Direction.EAST);
                    break;
                case SOUTH_EAST:
                    Player.moveSouthEastAnimation.update(delta);
                    player.setImage(Player.moveSouthEastAnimation.getCurrentFrame());
                    Player.setLastDirection(Direction.SOUTH_EAST);
                    break;
                case SOUTH:
                    Player.moveSouthAnimation.update(delta);
                    player.setImage(Player.moveSouthAnimation.getCurrentFrame());
                    Player.setLastDirection(Direction.SOUTH);
                    break;
                case SOUTH_WEST:
                    Player.moveSouthWestAnimation.update(delta);
                    player.setImage(Player.moveSouthWestAnimation.getCurrentFrame());
                    Player.setLastDirection(Direction.SOUTH_WEST);
                    break;
                case WEST:
                    Player.moveWestAnimation.update(delta);
                    player.setImage(Player.moveWestAnimation.getCurrentFrame());
                    Player.setLastDirection(Direction.WEST);
                    break;
                case NORTH_WEST:
                    Player.moveNorthWestAnimation.update(delta);
                    player.setImage(Player.moveNorthWestAnimation.getCurrentFrame());
                    Player.setLastDirection(Direction.NORTH_WEST);
                    break;
                case IDLE:
                    switch (Player.getLastDirection()) {
                        case NORTH:
                            Player.idleNorthAnimation.update(delta);
                            player.setImage(Player.idleNorthAnimation.getCurrentFrame());
                            break;
                        case NORTH_EAST:
                            Player.idleNorthEastAnimation.update(delta);
                            player.setImage(Player.idleNorthEastAnimation.getCurrentFrame());
                            break;
                        case EAST:
                            Player.idleEastAnimation.update(delta);
                            player.setImage(Player.idleEastAnimation.getCurrentFrame());
                            break;
                        case SOUTH_EAST:
                            Player.idleSouthEastAnimation.update(delta);
                            player.setImage(Player.idleSouthEastAnimation.getCurrentFrame());
                            break;
                        case SOUTH:
                            Player.idleSouthAnimation.update(delta);
                            player.setImage(Player.idleSouthAnimation.getCurrentFrame());
                            break;
                        case SOUTH_WEST:
                            Player.idleSouthWestAnimation.update(delta);
                            player.setImage(Player.idleSouthWestAnimation.getCurrentFrame());
                            break;
                        case WEST:
                            Player.idleWestAnimation.update(delta);
                            player.setImage(Player.idleWestAnimation.getCurrentFrame());
                            break;
                        case NORTH_WEST:
                            Player.idleNorthWestAnimation.update(delta);
                            player.setImage(Player.idleNorthWestAnimation.getCurrentFrame());
                            break;
                        default:
                            Player.idleSouthAnimation.update(delta);
                            player.setImage(Player.idleSouthAnimation.getCurrentFrame());
                            break;
                    }
                    break;
            }

            eventBus.post(new PlayerPositionEvent(player, player.getX(), player.getY()));

            for (IPlayerLightProcessor spi : getEntityPlayerLightProcessors()) {
                spi.processPlayerLight(player, gameData, world);
            }

            if (gameData.getKeys().isDown(GameKeys.INTERACT)) {
                for (IQuestInteractable interactable : getEntityQuestInteractables()) {
                    interactable.interact(player, gameData, world);
                }
            }

            if (gameData.getKeys().isDown(GameKeys.RELOAD)) {
                for (IToggleableLight toggleableLight : getPlayerToggleableLights(world)) {
                    toggleableLight.reload(player);
                }
            }
        }
    }

    private Direction getDirection(float velX, float velY) {
        if (velX == 0 && velY == 0) {
            return Direction.IDLE;
        }

        double angle = Math.toDegrees(Math.atan2(-velY, velX));
        if (angle < 0) {
            angle += 360;
        }

        if (angle >= 337.5 || angle < 22.5) return Direction.EAST;
        if (angle >= 22.5 && angle < 67.5) return Direction.NORTH_EAST;
        if (angle >= 67.5 && angle < 112.5) return Direction.NORTH;
        if (angle >= 112.5 && angle < 157.5) return Direction.NORTH_WEST;
        if (angle >= 157.5 && angle < 202.5) return Direction.WEST;
        if (angle >= 202.5 && angle < 247.5) return Direction.SOUTH_WEST;
        if (angle >= 247.5 && angle < 292.5) return Direction.SOUTH;
        if (angle >= 292.5 && angle < 337.5) return Direction.SOUTH_EAST;

        return Direction.IDLE;
    }

    private float approachZero(float value, float decrement) {
        if (value > 0) return Math.max(0, value - decrement);
        if (value < 0) return Math.min(0, value + decrement);
        return 0;
    }

    private Collection<? extends IToggleableLight> getPlayerToggleableLights(World world) {
        return world.getEntities(IToggleableLight.class).stream().map(IToggleableLight.class::cast).collect(toList());
    }

    private Collection<? extends IPlayerLightProcessor> getEntityPlayerLightProcessors() {
        return ServiceLoader.load(IPlayerLightProcessor.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IQuestInteractable> getEntityQuestInteractables() {
        return ServiceLoader.load(IQuestInteractable.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
