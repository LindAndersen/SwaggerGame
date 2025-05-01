package dk.sdu.smp4.keyanddoor.key;

import dk.sdu.smp4.common.Services.GUI.PolygonColor;
import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.World;

import java.util.Random;

public class KeyPlugin implements IEntityLoaderService {
    private Random random = new Random();
    private int mapCode = 6;

    @Override
    public void render(World world, int x, int y) {
        world.addEntity(createKey(world.getTileSize(), x, y, "bronze_key", PolygonColor.BLUE));
    }

    /*public void start(GameData gameData, World world) {
        Entity goldenKey = createKey(gameData, 400, 400, "golden_key", PolygonColor.GOLD);
        Entity bronzeKey = createKey(gameData, 400, 600, "bronze_key", PolygonColor.BLUE);

        world.addEntity(goldenKey);
        world.addEntity(bronzeKey);
    }*/

    private Entity createKey(int tileSize, int x, int y, String keyId, PolygonColor color) {
        Key key = new Key(keyId);
//        float randomX = random.nextFloat(0.1F,0.7F) * gameData.getDisplayWidth();
//        float value1 = random.nextFloat() * (0.3f - 0.2f) + 0.2f;
//        float value2 = random.nextFloat() * (0.8f - 0.7f) + 0.7f;
//
//        float result = random.nextBoolean() ? value1 : value2;
//        float randomY = result * gameData.getDisplayHeight();

        key.setPolygonCoordinates(
                // Teeth (blocky bit)
                5, -33,
                7, 33,
                5, -30,
                10, -30,
                10, -26,
                6, -26,
                6, -24,
                10, -24,
                10, -20,
                6, -20,
                6, -10,
                10, -10,
                10, -3,
                2, -3,
                2, -10,
                4, -10,
                4, -33
        );

        key.setX(x*tileSize+tileSize/2);
        key.setY(y*tileSize+tileSize/2);
        key.setPaint(color);
        return key;
    }

    /*
    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities(Key.class).forEach(world::removeEntity);
    }*/

    @Override
    public int getMapCode() {
        return mapCode;
    }
}
