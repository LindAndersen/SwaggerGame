package dk.sdu.smp4.aispider;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.enemy.data.CommonEnemy;
import dk.sdu.smp4.common.enemy.services.EnemyTargetsSPI;
import dk.sdu.smp4.common.events.data.PlayerHitEvent;
import dk.sdu.smp4.common.events.services.IEventBus;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class AISpider extends CommonEnemy {
    private EntityImage moveLeft;
    private EntityImage moveRight;
    private long lastHitTime = 0;

    public AISpider()
    {
        moveLeft = new EntityImage("/moveLeft.gif", 80, 80, true, true, getClass());
        moveRight = new EntityImage("/moveRight.gif", 80, 80, true, true, getClass());
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
        IEventBus eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        if(eventBus == null){return;}

        if (entity instanceof EnemyTargetsSPI && !isInCooldown()) {
            eventBus.post(new PlayerHitEvent(entity));
            setLastHitTime();
        }
    }

    public boolean isInCooldown() {
        return System.currentTimeMillis() - lastHitTime < 5000;
    }

    public void setLastHitTime() {
        this.lastHitTime = System.currentTimeMillis();
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}

