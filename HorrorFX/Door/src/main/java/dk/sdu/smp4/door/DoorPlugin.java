package dk.sdu.smp4.door;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.HardEntity;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.events.DoorCreationEvent;
import dk.sdu.smp4.common.events.EventBus;

public class DoorPlugin implements IGamePluginService {

    private Door door, door2;

    @Override
    public void start(GameData gameData, World world) {
        door = createDoor(400, 300, 40,20, "bronze_key");
        door2 = createDoor(300, 100, 40,20, "golden_key");
        world.addEntity(door);
        world.addEntity(door2);
//        System.out.println("DoorPlugin started");
//        EventBus.subscribe(DoorCreationEvent.class, event -> {
//            System.out.println("DoorCreationEvent received: " + event);
//            HardEntity door = createDoor(event.getX(), event.getY(), event.getWidth(), event.getHeight());
//            world.addEntity(door);
//            System.out.println("Door created at (" + door.getX() + ", " + door.getY() + ") and added to world");
//        });
    }

    private Door createDoor(float x, float y, float width, float height, String requiredKey) {
        Door door = new Door();

        door.setPolygonCoordinates(
                -width / 2, -height / 2,   // Top left
                width / 2, -height / 2,    // Top right
                width / 2, height / 2,     // Bottom right
                -width / 2, height / 2     // Bottom left
        );
        door.setX(x);
        door.setY(y);
        door.setWidth(width);
        door.setHeight(height);
        door.setSolid(true);
        door.setPaint("brown");
        door.setType("brown_door");
        door.setRequiredKey(requiredKey);
        return door;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(door);
    }
}