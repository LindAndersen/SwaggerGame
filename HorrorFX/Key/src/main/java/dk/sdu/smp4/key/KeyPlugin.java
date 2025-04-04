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
        Entity goldenKey = createKey(gameData, "golden_key", "gold");
        Entity blueKey = createKey(gameData, "blue_key", "blue");
        Entity steelKey = createKey(gameData, "steel_key", "gray");

        world.addEntity(goldenKey);
        world.addEntity(blueKey);
        world.addEntity(steelKey);
    }

    private Entity createKey(GameData gameData, String keyId, String color) {
        Entity key = new Key();
        float randomX = random.nextFloat() * gameData.getDisplayWidth();
        float randomY = random.nextFloat() * gameData.getDisplayHeight();

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

        key.setX(randomX);
        key.setY(randomY);
        key.setPaint(color);
        key.setType("key");
        key.setProperties("keyId", keyId);
        return key;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities().removeIf(e -> "key".equals(e.getType()));
    }
}
