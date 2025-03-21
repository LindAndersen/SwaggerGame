package dk.sdu.smp4.aispider;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.player.Player;

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        Entity player = world.getEntities(Player.class).stream().findFirst().orElse(null);
        if (player == null) return; // No player found

        for (Entity entity : world.getEntities(enemy.class)) {
            enemy enemy = (enemy) entity;

           // System.out.println("EnemyControlSystem: Moving enemy towards player. Enemy at (" + enemy.getX() + ", " + enemy.getY() + ")");

            // Calculate movement direction toward player
            double dx = player.getX() - enemy.getX();
            double dy = player.getY() - enemy.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > 1) { // Avoid jittering when close
                double speed = 0.8; // Adjust speed as needed
                enemy.setX(enemy.getX() + (dx / distance) * speed);
                enemy.setY(enemy.getY() + (dy / distance) * speed);
            }
        }
    }
}
