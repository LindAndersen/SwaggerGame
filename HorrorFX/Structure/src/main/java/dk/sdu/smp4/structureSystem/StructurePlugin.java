package dk.sdu.smp4.structureSystem;

import dk.sdu.smp4.common.Services.GameLoop.IStructurePluginService;
import dk.sdu.smp4.common.data.*;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class StructurePlugin implements IStructurePluginService
{
    private final int mapCode = 1;
    private Room room, room2;

    @Override
    public void render(World world, int x, int y){
        world.addEntity(createStructure(world.getTileSize(), x, y));
    }

    /*public Structure createStructure(){ //TODO: Probably not necessary to have random structures
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
    }*/

    /**
     Create structure with specified height, width, x- and y-coordinates
     * @param tileSize
     * @param x
     * @param y
     * @return
     */
    public Structure createStructure(int tileSize, double x, double y){

        Structure structure = new Structure();

        double halfWidth = tileSize / 2.0;
        double halfHeight = tileSize / 2.0;
        structure.setPolygonCoordinates(
                halfWidth, -halfHeight,
                -halfWidth, -halfHeight,
                -halfWidth, halfHeight,
                halfWidth, halfHeight
        );
        structure.setX(x*tileSize);
        structure.setY(y*tileSize);
        structure.setSize(tileSize);
        structure.setSolid(true);
        return structure;
    }

    /*private Room createRoom(int height, int width, int wallThickness, double x, double y){
        Room room = new Room();

        int horizontalWidth = width+wallThickness;
        int verticalHeight = height+wallThickness;
        int doorGapWidth = 40;

        room.setTopWallLeft(createStructure(wallThickness, (horizontalWidth - doorGapWidth) / 2,
                x - (horizontalWidth - doorGapWidth) / 4 - doorGapWidth / 2, y - height / 2));

        room.setTopWallRight(createStructure(wallThickness, (horizontalWidth - doorGapWidth) / 2,
                x + (horizontalWidth - doorGapWidth) / 4 + doorGapWidth / 2, y - height / 2));

        room.setBottomWall(createStructure(wallThickness, horizontalWidth, x, y+height/2));
        room.setLeftWall(createStructure(verticalHeight, wallThickness, x-width/2, y));
        room.setRightWall(createStructure(verticalHeight, wallThickness, x+width/2, y));

//        EventBus.post(new DoorCreationEvent((float) x, (float) y - height / 2, doorGapWidth, wallThickness));
//        System.out.println("DoorCreationEvent posted");


        return room;
    }*/

    @Override
    public int getMapCode() {
        return mapCode;
    }
}
