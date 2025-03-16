package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public class PlayerPlugin implements IGamePluginService {
    private Entity player;
    @Override
    public void start(GameData gameData, World world) {
        player = CreatePlayer(gameData);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    private Entity CreatePlayer(GameData gameData) {

        Entity player = new Player();
        player.setPolygonCoordinates(-5,-5,10,0,-5,5);
        player.setX(gameData.getDisplayHeight() /2);
        player.setY(gameData.getDisplayWidth() /2);
        player.setRadius(8);
        return player;
    }
}
