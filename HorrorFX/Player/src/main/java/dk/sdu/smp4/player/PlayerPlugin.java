package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerPlugin implements IGamePluginService {
    private Entity player;
    @Override
    public void start(GameData gameData, World world) {
        player = CreatePlayer(gameData);
        world.addEntity(player);

        for(IPlayerLightPlugin lightPlugin : getEntityPlayerLights()) {
            lightPlugin.createPlayerLight(player, gameData, world);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    private Entity CreatePlayer(GameData gameData) {

        Entity player = new Player();
        player.setPolygonCoordinates(-8, -8, 8, -8, 8, 8, -8, 8);
        player.setX(gameData.getDisplayHeight() /2);
        player.setY(gameData.getDisplayWidth() /2);
        player.setRadius(8);
        player.setPaint("blue");
        return player;
    }

    private Collection<? extends IPlayerLightPlugin> getEntityPlayerLights() {
        return ServiceLoader.load(IPlayerLightPlugin.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
