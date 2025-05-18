package dk.sdu.smp4.wallSystem;

import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.data.*;

import java.util.Set;

/**
 * Hello world!
 *
 */
public class WallPlugin implements IEntityLoaderService
{
    @Override
    public void render(World world, int x, int y, int mapCode){
        world.addEntity(createWall(world.getTileSize(), x, y));
    }

    /**
     Create wall based on tile size and x- and y-coordinates
     * @param tileSize
     * @param x
     * @param y
     * @return
     */

    public Wall createWall(int tileSize, double x, double y){

        Wall wall = new Wall();

        double halfSize = tileSize / 2.0;
        wall.setPolygonCoordinates(
                halfSize, -halfSize,
                -halfSize, -halfSize,
                -halfSize, halfSize,
                halfSize, halfSize
        );
        wall.setX(x*tileSize+tileSize/2);
        wall.setY(y*tileSize+tileSize/2);
        wall.setSize(tileSize);
        wall.setSolid(true);
        return wall;
    }

    @Override
    public Set<Integer> getMapCodes() {
        return Set.of(1);
    }

    @Override
    public void stop(World world) {
        world.getEntities(Wall.class).forEach(world::removeEntity);
    }
}
