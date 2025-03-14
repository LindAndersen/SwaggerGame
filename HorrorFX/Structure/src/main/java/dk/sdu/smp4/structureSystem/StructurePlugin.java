package dk.sdu.smp4.structureSystem;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class StructurePlugin implements IGamePluginService
{
    @Override
    public void start(GameData gameData, World world) {
        Entity structure = createStructure(gameData);
        world.addEntity(structure);
    }

    private Entity createStructure(GameData gameData){
        Entity structure = new Structure();
        Random rnd = new Random();
        int structureSize = 50;
        structure.setPolygonCoordinates(structureSize, -structureSize, -structureSize, -structureSize, -structureSize, structureSize, structureSize, structureSize);
        structure.setX(rnd.nextInt(500));
        structure.setY(rnd.nextInt(500));
        structure.setRadius(structureSize);
        structure.setRotation(90);
        return structure;
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
