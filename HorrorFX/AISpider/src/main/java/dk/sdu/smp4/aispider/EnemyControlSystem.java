package dk.sdu.smp4.aispider;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.events.EventBus;
import dk.sdu.smp4.common.events.PlayerPositionEvent;

public class EnemyControlSystem implements IEntityProcessingService {

    private double lastKnownPlayerX = -1;
    private double lastKnownPlayerY = -1;

    public EnemyControlSystem() {
        EventBus.subscribe(PlayerPositionEvent.class, event -> {
            lastKnownPlayerX = event.getX();
            lastKnownPlayerY = event.getY();
        });
    }
    @Override
    public void process(GameData gameData, World world) {
        if (lastKnownPlayerX == -1 || lastKnownPlayerY == -1) return;

        for (Entity entity : world.getEntities(enemy.class)) {
            enemy enemy = (enemy) entity;
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

        }
    }
}
