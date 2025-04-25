package dk.sdu.smp4.key;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

import java.util.Random;

public class KeyPlugin implements IGamePluginService {
    private Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        Entity goldenKey = createKey(gameData, 400, 400, "golden_key", "gold");
        Entity bronzeKey = createKey(gameData, 400, 600, "bronze_key", "blue");

        world.addEntity(goldenKey);
        world.addEntity(bronzeKey);
    }

    private Entity createKey(GameData gameData, int x, int y, String keyId, String color) {
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

        key.setX(x);
        key.setY(y);
        key.setPaint(color);
        return key;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities(Key.class).forEach(world::removeEntity);
    }
}
