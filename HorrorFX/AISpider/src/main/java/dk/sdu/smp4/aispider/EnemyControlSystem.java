package dk.sdu.smp4.aispider;

import dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.events.data.PlayerPositionEvent;
import dk.sdu.smp4.common.events.data.SpiderPositionEvent;
import dk.sdu.smp4.common.events.services.IEventBus;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class EnemyControlSystem implements IEntityProcessingService {

    private double lastKnownPlayerX = -1;
    private double lastKnownPlayerY = -1;
    IEventBus eventBus;

    public EnemyControlSystem() {
        eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        assert eventBus != null;
        eventBus.subscribe(PlayerPositionEvent.class, event -> {
            lastKnownPlayerX = event.getX();
            lastKnownPlayerY = event.getY();
        });
    }

    @Override
    public void process(GameData gameData, World world) {
        if (lastKnownPlayerX == -1 || lastKnownPlayerY == -1) return;

        for (Entity entity : world.getEntities(Enemy.class)) {
            Enemy enemy = (Enemy) entity;
            if (enemy.isInCooldown()) continue;
            enemy.setPreviousX(enemy.getX());
            enemy.setPreviousY(enemy.getY());
            double dx = lastKnownPlayerX - enemy.getX();
            double dy = lastKnownPlayerY - enemy.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > 1) {
                double speed = 0.8;
                enemy.setRotation(Math.toDegrees(Math.atan2(dy, dx)));
                enemy.setX(enemy.getX() + (dx / distance) * speed);
                enemy.setY(enemy.getY() + (dy / distance) * speed);
            }

            handleImage(enemy, dx);
            eventBus.post(new SpiderPositionEvent(entity, entity.getX(), entity.getY()));
        }
    }

    private void handleImage(Enemy enemy, double dx)
    {
        if(dx < 0){
            enemy.setMoveLeft();
        } else {
            enemy.setMoveRight();
        }
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
