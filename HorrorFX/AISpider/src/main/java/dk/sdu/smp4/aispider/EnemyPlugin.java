package dk.sdu.smp4.aispider;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public class EnemyPlugin implements IGamePluginService {
    private SoftEntity enemy;

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
        //System.out.println(" Enemy added to world with ID " + enemy.getID());
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }

    private SoftEntity createEnemy(GameData gameData) {
        enemy = new enemy();
        enemy.setPolygonCoordinates(-8, -8, 8, -8, 8, 8, -8, 8); // Simple square
        enemy.setX(Math.random() * gameData.getDisplayWidth());  // Spawn at a random location
        enemy.setY(Math.random() * gameData.getDisplayHeight());
        enemy.setRadius(8);
        enemy.setSolid(true);
        enemy.setPaint("red");
        enemy.setType("spider");
        return enemy;

    }

}
