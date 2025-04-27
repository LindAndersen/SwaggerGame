package dk.sdu.smp4.aispider;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.enemy.services.EnemyTargetsSPI;
import dk.sdu.smp4.common.events.EventBus;
import dk.sdu.smp4.common.events.PlayerHitEvent;
import javafx.scene.image.Image;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class Enemy extends SoftEntity {
    private Image moveLeft;
    private Image moveRight;
    private long lastHitTime = 0;

    public Enemy()
    {
        moveLeft = new Image(getClass().getResourceAsStream("/moveLeft.gif"), 80, 80, true, true);
        moveRight = new Image(getClass().getResourceAsStream("/moveRight.gif"), 80, 80, true, true);
        setImage(moveLeft);
    }

    public void setMoveLeft() {
        setImage(moveLeft);
    }

    public void setMoveRight() {
        setImage(moveRight);
    }

    @Override
    public void collide(World world, Entity entity) {
        // Define what happens when the enemy collides with another entity (e.g., damage player).
        if (entity instanceof EnemyTargetsSPI && !isInCooldown()) {
            System.out.println("Updated player hit bus");
            EventBus.post(new PlayerHitEvent(entity));
            setLastHitTime();
        }
    }

    public boolean isInCooldown() {
        return System.currentTimeMillis() - lastHitTime < 5000;
    }

    public void setLastHitTime() {
        this.lastHitTime = System.currentTimeMillis();
    }

    private Collection<? extends EnemyTargetsSPI> getEnemyTargetsSPI() {
        return ServiceLoader.load(EnemyTargetsSPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}

