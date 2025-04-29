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
    public final static EntityImage moveLeftImage = new EntityImage("/move_left.gif",50,48,true,true,Player.class);
    public final static EntityImage moveRightImage = new EntityImage("/move_right.gif", 50, 48, true,true,Player.class);
    public final static EntityImage idleRightImage = new EntityImage("/idle_right.png", 50, 48, true,true,Player.class);
    public final static EntityImage idleLeftImage = new EntityImage("/idle_left.png", 50, 48, true,true,Player.class);
    private int lives = 3;
    private int maxLives = 3;
    private boolean isDead = false;
    private float velocityX = 0;
    private float velocityY = 0;
    private IEventBus eventBus;

    public Player()
    {
        setImage(moveRightImage);
        eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        assert eventBus != null;
        eventBus.subscribe(PlayerHitEvent.class, event -> {
            if (event.getPlayer() instanceof Player ) {
                if (isDead()) return;

                loseLife();
                System.out.println("Player hit! Lives left: " + lives);
                eventBus.post(new UpdateHUDLifeEvent(lives, maxLives));

                if (getLives() <= 0) {
                    setDead(true); // blokerer flere hits
                    eventBus.post(new GameOverEvent());
                }
            }
        });
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