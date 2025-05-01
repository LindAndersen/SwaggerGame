package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.GUI.PolygonColor;
import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.InventorySPI;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PlayerPlugin implements IEntityLoaderService {
    private SoftEntity player;
    private int mapCode = 4;


    @Override
    public void render(World world, int x, int y) {
        player = CreatePlayer(world.getTileSize(), x, y);
        world.addEntity(player);
        for(IPlayerLightPlugin lightPlugin : getEntityPlayerLights()) {
            lightPlugin.createPlayerLight(player, world);
        }

        for(InventorySPI inventorySPI : getInventorySPIs())
        {
            inventorySPI.addInventoryForEntity(player);
        }
    }

    private SoftEntity CreatePlayer(int tileSize, int x, int y) {

        SoftEntity player = new Player();
        player.setPolygonCoordinates(-8, -8,  // back top-left
                6, -8,  // near front top-right
                12, 0,  // tip (front middle point)
                6, 8,   // near front bottom-right
                -8, 8  );
        player.setX(x*tileSize+tileSize/2);
        player.setY(y*tileSize+tileSize/2);
        player.setRadius(8);
        player.setSolid(true);
        player.setPaint(PolygonColor.BLUE);
        return player;
    }

    private Collection<? extends IPlayerLightPlugin> getEntityPlayerLights() {
        return ServiceLoader.load(IPlayerLightPlugin.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends InventorySPI> getInventorySPIs() {
        return ServiceLoader.load(InventorySPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    @Override
    public int getMapCode() {
        return mapCode;
    }
}
