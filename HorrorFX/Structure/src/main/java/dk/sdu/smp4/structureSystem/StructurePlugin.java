package dk.sdu.smp4.structureSystem;

import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.Services.GameLoop.IStructurePluginService;
import dk.sdu.smp4.common.data.*;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class StructurePlugin implements IStructurePluginService
{
    private Room room, room2;

    @Override
    public void start(GameData gameData, World world) {
//        for (int i = 0; i < 10; i++) {
//            structure = createStructure(gameData);
//            world.addEntity(structure);
//        }

        room = createRoom(gameData, 200, 300, 20, 400, 400);
        world.addEntity(room.getBottomWall());
        world.addEntity(room.getLeftWall());
        world.addEntity(room.getTopWallLeft());
        world.addEntity(room.getTopWallRight());
        world.addEntity(room.getRightWall());

        room2 = createRoom(gameData, 600, 600, 20, 300, 400);
        world.addEntity(room2.getBottomWall());
        world.addEntity(room2.getLeftWall());
        world.addEntity(room2.getTopWallLeft());
        world.addEntity(room2.getTopWallRight());
        world.addEntity(room2.getRightWall());
    }

    public Structure createStructure(GameData gameData){ //TODO: Probably not necessary to have random structures
        Structure structure = new Structure();

        Random rnd = new Random();
        int width = rnd.nextInt(50);
        int height = rnd.nextInt(50);
        double halfWidth = width / 2.0;
        double halfHeight = height / 2.0;

        structure.setPolygonCoordinates(
                halfWidth, -halfHeight,
                -halfWidth, -halfHeight,
                -halfWidth, halfHeight,
                halfWidth, halfHeight
        );
        structure.setX(rnd.nextInt(500));
        structure.setY(rnd.nextInt(500));
        structure.setWidth(width);
        structure.setHeight(height);
        structure.setSolid(true);
        return structure;
    }

    /**
     Create structure with specified height, width, x- and y-coordinates
     * @param gameData
     * @param height
     * @param width
     * @param x
     * @param y
     * @return
     */
    public Structure createStructure(GameData gameData, int height, int width, double x, double y){

        Structure structure = new Structure();

        double halfWidth = width / 2.0;
        double halfHeight = height / 2.0;
        structure.setPolygonCoordinates(
                halfWidth, -halfHeight,
                -halfWidth, -halfHeight,
                -halfWidth, halfHeight,
                halfWidth, halfHeight
        );
        structure.setX(x);
        structure.setY(y);
        structure.setWidth(width);
        structure.setHeight(height);
        structure.setSolid(true);
        return structure;
    }

    private Room createRoom(GameData gameData, int height, int width, int wallThickness, double x, double y){
        Room room = new Room();

        int horizontalWidth = width+wallThickness;
        int verticalHeight = height+wallThickness;
        int doorGapWidth = 40;

        room.setTopWallLeft(createStructure(gameData, wallThickness, (horizontalWidth - doorGapWidth) / 2,
                x - (horizontalWidth - doorGapWidth) / 4 - doorGapWidth / 2, y - height / 2));

        room.setTopWallRight(createStructure(gameData, wallThickness, (horizontalWidth - doorGapWidth) / 2,
                x + (horizontalWidth - doorGapWidth) / 4 + doorGapWidth / 2, y - height / 2));

        room.setBottomWall(createStructure(gameData, wallThickness, horizontalWidth, x, y+height/2));
        room.setLeftWall(createStructure(gameData, verticalHeight, wallThickness, x-width/2, y));
        room.setRightWall(createStructure(gameData, verticalHeight, wallThickness, x+width/2, y));

//        EventBus.post(new DoorCreationEvent((float) x, (float) y - height / 2, doorGapWidth, wallThickness));
//        System.out.println("DoorCreationEvent posted");


        return room;
    }



    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(room);
        world.removeEntity(room2);
    }
}
