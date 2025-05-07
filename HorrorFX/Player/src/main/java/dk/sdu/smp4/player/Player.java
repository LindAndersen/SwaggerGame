package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.enemy.services.EnemyTargetsSPI;
import dk.sdu.smp4.common.events.data.GameOverEvent;
import dk.sdu.smp4.common.events.data.PlayerHitEvent;
import dk.sdu.smp4.common.events.data.UpdateHUDLifeEvent;
import dk.sdu.smp4.common.events.services.IEventBus;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class Player extends SoftEntity implements EnemyTargetsSPI {

    // Walking animations
    public static Animation moveNorthAnimation;
    public static Animation moveNorthEastAnimation;
    public static Animation moveEastAnimation;
    public static Animation moveSouthEastAnimation;
    public static Animation moveSouthAnimation;
    public static Animation moveSouthWestAnimation;
    public static Animation moveWestAnimation;
    public static Animation moveNorthWestAnimation;

    // Idle animations
    public static Animation idleNorthAnimation;
    public static Animation idleNorthEastAnimation;
    public static Animation idleEastAnimation;
    public static Animation idleSouthEastAnimation;
    public static Animation idleSouthAnimation;
    public static Animation idleSouthWestAnimation;
    public static Animation idleWestAnimation;
    public static Animation idleNorthWestAnimation;

    // Track last direction moved
    private static Direction lastDirection = Direction.SOUTH;
    public static Direction getLastDirection() {
        return lastDirection;
    }
    public static void setLastDirection(Direction dir) {
        lastDirection = dir;
    }

    static {
        // Walking animations
        moveNorthAnimation = loadAnimation("Walking Frames/N", "north", 8, 0.1);
        moveNorthEastAnimation = loadAnimation("Walking Frames/NE", "northEast", 8, 0.1);
        moveEastAnimation = loadAnimation("Walking Frames/E", "east", 8, 0.1);
        moveSouthEastAnimation = loadAnimation("Walking Frames/SE", "southEast", 8, 0.1);
        moveSouthAnimation = loadAnimation("Walking Frames/S", "south", 8, 0.1);
        moveSouthWestAnimation = loadAnimation("Walking Frames/SW", "southWest", 8, 0.1);
        moveWestAnimation = loadAnimation("Walking Frames/W", "west", 8, 0.1);
        moveNorthWestAnimation = loadAnimation("Walking Frames/NW", "northWest", 8, 0.1);

        // Idle animations
        idleNorthAnimation = loadAnimation("Idle Frames/N", "north", 24, 0.05);
        idleNorthEastAnimation = loadAnimation("Idle Frames/NE", "northEast", 24, 0.05);
        idleEastAnimation = loadAnimation("Idle Frames/E", "east", 24, 0.05);
        idleSouthEastAnimation = loadAnimation("Idle Frames/SE", "southEast", 24, 0.05);
        idleSouthAnimation = loadAnimation("Idle Frames/S", "south", 24, 0.05);
        idleSouthWestAnimation = loadAnimation("Idle Frames/SW", "southWest", 24, 0.05);
        idleWestAnimation = loadAnimation("Idle Frames/W", "west", 24, 0.05);
        idleNorthWestAnimation = loadAnimation("Idle Frames/NW", "northWest", 24, 0.05);
    }

    private int lives = 3;
    private int maxLives = 3;
    private boolean isDead = false;
    private float velocityX = 0;
    private float velocityY = 0;
    private IEventBus eventBus;

    public Player() {
        setImage(moveEastAnimation.getCurrentFrame()); // Default idle facing right

        eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        assert eventBus != null;
        eventBus.subscribe(PlayerHitEvent.class, event -> {
            if (event.getPlayer() instanceof Player) {
                if (isDead()) return;

                loseLife();
                eventBus.post(new UpdateHUDLifeEvent(lives, maxLives));

                if (getLives() <= 0) {
                    setDead(true);
                    eventBus.post(new GameOverEvent());
                }
            }
        });
    }

    private static Animation loadAnimation(String folderName, String filePrefix, int frameCount, double frameDuration) {
        EntityImage[] frames = new EntityImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            String path = "/" + folderName + "/" + filePrefix + (i + 1) + ".png";
            frames[i] = new EntityImage(path, 50, 48, true, true, Player.class);
        }
        return new Animation(frames, frameDuration);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getMaxLives() {
        return maxLives;
    }

    public void setMaxLives(int maxLives) {
        this.maxLives = maxLives;
    }

    public void loseLife() {
        this.lives--;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
