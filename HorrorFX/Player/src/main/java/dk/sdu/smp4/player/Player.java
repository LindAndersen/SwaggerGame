package dk.sdu.smp4.player;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.events.EventBus;
import dk.sdu.smp4.common.events.GameOverEvent;
import dk.sdu.smp4.common.events.PlayerHitEvent;
import dk.sdu.smp4.common.events.UpdateHUDLifeEvent;
import javafx.scene.image.Image;

public class Player extends SoftEntity {
    private final Image moveLeftImage = new Image(getClass().getResourceAsStream("/move_left.gif"));
    private final Image moveRightImage = new Image(getClass().getResourceAsStream("/move_right.gif"));
    private int lives = 3;
    private int maxLives = 3;
    private boolean isDead = false;

    public Player()
    {
        this.setImage(moveRightImage);

        EventBus.subscribe(PlayerHitEvent.class, event -> {
            if (event.getPlayer() instanceof Player ) {
                if (isDead()) return;

                loseLife();
                System.out.println("Player hit! Lives left: " + lives);
                EventBus.post(new UpdateHUDLifeEvent(lives, maxLives));

                if (getLives() <= 0) {
                    setDead(true); // blokerer flere hits
                    EventBus.post(new GameOverEvent());
                }
            }
        });
    }

    public Image getMoveLeftImage() {
        return moveLeftImage;
    }

    public Image getMoveRightImage() {
        return moveRightImage;
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
}