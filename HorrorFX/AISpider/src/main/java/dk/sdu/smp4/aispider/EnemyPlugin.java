package dk.sdu.smp4.aispider;

import dk.sdu.smp4.common.Services.GUI.PolygonColor;
import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.World;

import java.util.Set;

public class EnemyPlugin implements IEntityLoaderService {
    private SoftEntity enemy;

    @Override
    public void render(World world, int x, int y, int mapCode) {
        world.addEntity(createEnemy(world.getTileSize(), x, y));
    }

    private SoftEntity createEnemy(int tileSize, int x, int y) {
        enemy = new AISpider();
        enemy.setPolygonCoordinates(-11, -11, -11, 11, 11, 11, 11, -11); // Simple square
        enemy.setX(x*tileSize+tileSize/2);
        enemy.setY(y*tileSize+tileSize/2);
        enemy.setRadius(8);
        enemy.setSolid(true);
        enemy.setPaint(PolygonColor.RED);
        return enemy;

    }

    @Override
    public Set<Integer> getMapCodes() {
        return Set.of(5);
    }

    @Override
    public void stop(World world) {
        world.removeEntity(enemy);
    }
}
