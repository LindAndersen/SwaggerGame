package dk.sdu.smp4.structureSystem;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.*;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class StructurePlugin implements IGamePluginService
{
    private StaticEntity structure;

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 1; i++) {
            structure = createStructure(gameData, 200, 100, 200, 400);
            world.addEntity(structure);
        }
    }

    private StaticEntity createStructure(GameData gameData){
        Structure structure = new Structure();
        Random rnd = new Random();
        int structureWidth = rnd.nextInt(50);
        int structureHeight = rnd.nextInt(50);
        structure.setPolygonCoordinates(
                structureWidth, -structureHeight,
                -structureWidth, -structureHeight,
                -structureWidth, structureHeight,
                structureWidth, structureHeight
        );
        structure.setX(rnd.nextInt(500));
        structure.setY(rnd.nextInt(500));
        structure.setWidth(structureWidth);
        structure.setHeight(structureHeight);
        structure.setSolid(true);
        structure.setRotation(90);
        return structure;
    }

    private StaticEntity createStructure(GameData gameData, int height, int width, double x, double y){
        StaticEntity structure = new Structure();

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


    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(structure);
    }
}
