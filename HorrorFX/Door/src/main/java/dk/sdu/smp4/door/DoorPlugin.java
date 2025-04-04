package dk.sdu.smp4.door;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.HardEntity;
import dk.sdu.smp4.common.data.World;

import java.util.Random;

public class DoorPlugin implements IGamePluginService {

    private Random random = new Random();
    private HardEntity door;
    private int id;
    private boolean isOpen = false;



    @Override
    public void start(GameData gameData, World world) {
        door = createDoor(gameData);
        world.addEntity(door);
    }

    private HardEntity createDoor(GameData gameData){
        door = new Door();

        float randomX = random.nextFloat() * gameData.getDisplayWidth();
        float randomY = random.nextFloat() * gameData.getDisplayHeight();

        door.setPolygonCoordinates(-10, -20, 10, -20, 10, 20, -10, 20);
        door.setX(randomX);
        door.setY(randomY);
        door.setWidth(20);
        door.setHeight(40);
        door.setSolid(true);
        door.setPaint("brown");
        door.setType("brown_door");
        return door;
    }

    @Override
    public void stop(GameData gameData, World world) {


    }
}
