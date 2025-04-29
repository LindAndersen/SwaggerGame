package dk.sdu.smp4.keyanddoor.door;

import dk.sdu.smp4.common.Services.GUI.PolygonColor;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

public class DoorPlugin implements IGamePluginService {

    private Door door, door2;

    @Override
    public void start(GameData gameData, World world) {
        door = createDoor(400, 300, 40,20, InventorySlotItems.BRONZE_KEY);
        door2 = createDoor(300, 100, 40,20, InventorySlotItems.GOLDEN_KEY);
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

    private Door createDoor(float x, float y, float width, float height, InventorySlotItems requiredKey) {
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
        door.setPaint(PolygonColor.RED);
        door.setRequiredKey(requiredKey);
        return door;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(door);
    }
}