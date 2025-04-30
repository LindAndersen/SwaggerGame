package dk.sdu.smp4.keyanddoor.door;

import dk.sdu.smp4.common.Services.GUI.PolygonColor;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.Services.GameLoop.IStructurePluginService;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

public class DoorPlugin implements IStructurePluginService {

    private final int mapCode = 2;
    private Door door, door2;

    @Override
    public void render(World world, int x, int y) {
        world.addEntity(createDoor(world.getTileSize(), x, y, InventorySlotItems.BRONZE_KEY));
    }


    /*public void start(GameData gameData, World world) {
        door = createDoor(400, 300, 40, 20, InventorySlotItems.BRONZE_KEY);
        door2 = createDoor(300, 100, 40, 20, InventorySlotItems.GOLDEN_KEY);
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
        */

    private Door createDoor(int tileSize, int x, int y, InventorySlotItems requiredKey) {
        Door door = new Door();

        door.setPolygonCoordinates(
                -tileSize / 2, -tileSize / 2,   // Top left
                tileSize / 2, -tileSize / 2,    // Top right
                tileSize / 2, tileSize / 2,     // Bottom right
                -tileSize / 2, tileSize / 2     // Bottom left
        );
        door.setX(x*tileSize);
        door.setY(y*tileSize);
        door.setSize(tileSize);
        door.setSolid(true);
        door.setPaint(PolygonColor.RED);
        door.setRequiredKey(requiredKey);
        return door;
    }

    @Override
    public int getMapCode() {
        return mapCode;
    }
}