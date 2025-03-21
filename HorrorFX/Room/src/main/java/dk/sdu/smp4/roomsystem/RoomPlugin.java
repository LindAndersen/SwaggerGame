package dk.sdu.smp4.roomsystem;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.StaticEntity;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.structureSystem.Structure;

import java.util.Random;

public class RoomPlugin implements IGamePluginService {
    private StaticEntity room;

    @Override
    public void start(GameData gameData, World world) {

    }

    private StaticEntity createRoom(GameData gameData){
        Room room = new Room();
        Random rnd = new Random();
        int structureSize = rnd.nextInt(50);
        room.setPolygonCoordinates(structureSize, -structureSize, -structureSize, -structureSize, -structureSize, structureSize, structureSize, structureSize);
        room.setX(rnd.nextInt(500));
        room.setY(rnd.nextInt(500));
        room.setHeight(structureSize);
        room.setWidth(structureSize);
        room.setRotation(90);
        return room;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(room);
    }
}
