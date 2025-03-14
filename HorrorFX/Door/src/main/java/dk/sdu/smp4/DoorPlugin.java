package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Data.CommonInteractable;

import java.util.Random;

public class DoorPlugin implements IGamePluginService {

    private Random random = new Random();
    private Entity door;



    @Override
    public void start(GameData gameData, World world) {
        door = createDoor(gameData);
        world.addEntity(door);
    }

    private Entity createDoor(GameData gameData){
        Entity door = new Door();

        float randomX = random.nextFloat() * gameData.getDisplayWidth();
        float randomY = random.nextFloat() * gameData.getDisplayHeight();

        door.setPolygonCoordinates(-10, -20, 10, -20, 10, 20, -10, 20);
        door.setX(randomX);
        door.setY(randomY);
        door.setRadius(8);
        return door;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(door);
    }
}
