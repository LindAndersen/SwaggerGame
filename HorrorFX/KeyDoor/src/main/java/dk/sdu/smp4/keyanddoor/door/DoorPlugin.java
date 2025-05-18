package dk.sdu.smp4.keyanddoor.door;

import dk.sdu.smp4.common.Services.GUI.PolygonColor;
import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

import javax.security.auth.login.CredentialException;
import java.util.Set;

public class DoorPlugin implements IEntityLoaderService {

    @Override
    public void render(World world, int x, int y, int mapCode) {
        switch (mapCode){
            case 2:
                world.addEntity(createDoor(world.getTileSize(), x, y, InventorySlotItems.BRONZE_KEY, PolygonColor.RED));
                break;
            case 3:
                world.addEntity(createDoor(world.getTileSize(), x, y, InventorySlotItems.GOLDEN_KEY, PolygonColor.GOLD));
                break;
            default:
                break;
        }
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

    private Door createDoor(int tileSize, int x, int y, InventorySlotItems requiredKey, PolygonColor color) {
        Door door = new Door();

        double halfSize = tileSize / 2.0;

        door.setPolygonCoordinates(
                halfSize, -halfSize,
                -halfSize, -halfSize,
                -halfSize, halfSize,
                halfSize, halfSize
        );
        door.setX(x*tileSize+tileSize/2);
        door.setY(y*tileSize+tileSize/2);
        door.setSize(tileSize);
        door.setSolid(true);
        door.setPaint(color);
        door.setRequiredKey(requiredKey);
        return door;
    }

    @Override
    public void stop(World world) {
        world.getEntities(Door.class).forEach(world::removeEntity);
    }

    @Override
    public Set<Integer> getMapCodes() {
        return Set.of(2, 3);
    }
}