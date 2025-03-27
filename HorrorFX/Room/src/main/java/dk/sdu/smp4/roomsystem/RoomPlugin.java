package dk.sdu.smp4.roomsystem;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.structureSystem.StructurePlugin;

public class RoomPlugin implements IGamePluginService {
    private Room room;

    @Override
    public void start(GameData gameData, World world) {
        room = createRoom(gameData, 100, 200, 20, 200, 200);
        world.addEntity(room.getBottomWall());
        world.addEntity(room.getLeftWall());
        world.addEntity(room.getTopWall());
        world.addEntity(room.getRightWall());
    }

    private Room createRoom(GameData gameData, int height, int width, int wallThickness, double x, double y){
        Room room = new Room();

        int horizontalWidth = width+wallThickness;
        int verticalHeight = height+wallThickness;

        room.setTopWall(StructurePlugin.createStructure(gameData, wallThickness, horizontalWidth, x, y+height/2));
        room.setBottomWall(StructurePlugin.createStructure(gameData, wallThickness, horizontalWidth, x, y-height/2));
        room.setLeftWall(StructurePlugin.createStructure(gameData, verticalHeight, wallThickness, x-width/2, y));
        room.setRightWall(StructurePlugin.createStructure(gameData, verticalHeight, wallThickness, x+width/2, y));



        return room;
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
