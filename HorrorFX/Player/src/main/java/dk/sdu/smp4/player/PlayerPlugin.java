package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerPlugin implements IGamePluginService {
    private SoftEntity player;
    @Override
    public void start(GameData gameData, World world) {
        player = CreatePlayer(gameData);
        world.addEntity(player);
        System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");

        for(IPlayerLightPlugin lightPlugin : getEntityPlayerLights()) {
            lightPlugin.createPlayerLight(player, gameData, world);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    private SoftEntity CreatePlayer(GameData gameData) {

        SoftEntity player = new Player();
        player.setPolygonCoordinates(-8, -8,  // back top-left
                6, -8,  // near front top-right
                12, 0,  // tip (front middle point)
                6, 8,   // near front bottom-right
                -8, 8  );
        player.setX(gameData.getDisplayHeight() /2);
        player.setY(gameData.getDisplayWidth() /2);
        player.setRadius(8);
        player.setSolid(true);
        player.setPaint("blue");
        player.setType("player");
        return player;
    }

    private Collection<? extends IPlayerLightPlugin> getEntityPlayerLights() {
        return ServiceLoader.load(IPlayerLightPlugin.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
