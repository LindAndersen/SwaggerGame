package dk.sdu.smp4.key;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

import java.util.Random;

public class KeyPlugin implements IGamePluginService {
    private Random random = new Random();
    private Entity key;
    private int id;


    @Override
    public void start(GameData gameData, World world) {
        key = createDoor(gameData);
        world.addEntity(key);
    }

    private Entity createDoor(GameData gameData){
        key = new Key();

        float randomX = random.nextFloat() * gameData.getDisplayWidth();
        float randomY = random.nextFloat() * gameData.getDisplayHeight();

        key.setPolygonCoordinates(
                -5, -20,  // Top left
                5, -20,   // Top right
                5, -15,   // Right notch
                10, -15,  // Right notch
                10, -10,  // Right notch
                5, -10,   // Right notch
                5, 0,     // Right side
                10, 0,    // Right side
                10, 5,    // Right side
                5, 5,     // Right side
                5, 10,    // Bottom right
                -5, 10,   // Bottom left
                -5, 5,    // Left side
                -10, 5,   // Left side
                -10, 0,   // Left side
                -5, 0,    // Left side
                -5, -10,  // Left notch
                -10, -10, // Left notch
                -10, -15, // Left notch
                -5, -15   // Left notch
                );
        key.setX(randomX);
        key.setY(randomY);
        return key;
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
