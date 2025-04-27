package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

import java.util.Random;

public class ResinPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Random random = new Random();
        for (int i=0;i<8;i++)
        {
            int y = random.nextInt(50, gameData.getDisplayHeight()-50);
            int x = random.nextInt(50, gameData.getDisplayWidth()-50);

            world.addEntity(createResin(x, y));
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity resin : world.getEntities(Resin.class))
        {
            world.removeEntity(resin);
        }
    }

    private Resin createResin(int x, int y)
    {
        Resin resin = new Resin();
        resin.setX(x);
        resin.setY(y);

        resin.setPolygonCoordinates(0,0);

        return resin;
    }
}
