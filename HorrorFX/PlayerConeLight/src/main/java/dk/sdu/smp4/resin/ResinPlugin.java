package dk.sdu.smp4.resin;

import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

import java.util.Random;
import java.util.Set;

public class ResinPlugin implements IEntityLoaderService {

    @Override
    public void render(World world, int x, int y, int mapCode) {
        world.addEntity(createResin(world.getTileSize(), x, y));
    }

    /*@Override
    public void start(GameData gameData, World world) {
        Random random = new Random();
        for (int i=0;i<12;i++)
        {
            int y = random.nextInt(50, gameData.getDisplayHeight()-50);
            int x = random.nextInt(50, gameData.getDisplayWidth()-50);

            world.addEntity(createResin(x, y));
        }
    }*/

    @Override
    public void stop(World world) {
        world.getEntities(Resin.class).forEach(world::removeEntity);
    }

    private Resin createResin(int tilesize, int x, int y)
    {
        Resin resin = new Resin();
        resin.setX(x*tilesize+tilesize/2);
        resin.setY(y*tilesize+tilesize/2);

        resin.setPolygonCoordinates(0,0);

        return resin;
    }

    @Override
    public Set<Integer> getMapCodes() {
        return Set.of(8);
    }
}
