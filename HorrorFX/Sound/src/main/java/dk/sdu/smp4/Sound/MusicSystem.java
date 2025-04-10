package dk.sdu.smp4.Sound;

import dk.sdu.smp4.aispider.Enemy;
import dk.sdu.smp4.common.Services.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.player.Player;

public class MusicSystem implements IPostEntityProcessingService {

    private static final String HIT_SOUND = "/background/man-scream.wav";
    private static final String PROXIMITY_SOUND = "/background/cropped_aggressive_scary.wav";
    private static final String DEFAULT_SOUND = "/background/chill_scary.wav";

    @Override
    public void process(GameData gameData, World world) {
        Entity player = world.getEntities(Player.class).stream().findFirst().orElse(null);
        if (player == null) return;

        for (Entity e : world.getEntities(Enemy.class)) {
            boolean overlap = Math.abs(e.getX() - player.getX()) < 20 && Math.abs(e.getY() - player.getY()) < 20;
            boolean close = Math.abs(e.getX() - player.getX()) < 200 && Math.abs(e.getY() - player.getY()) < 200;

            if (overlap) {
                SoundService.playSound(HIT_SOUND);
            } else if (close) {
                SoundService.playSound(PROXIMITY_SOUND);
                SoundService.stopSound(DEFAULT_SOUND);
            } else {
                SoundService.playSound(DEFAULT_SOUND);
                SoundService.stopSound(PROXIMITY_SOUND);
            }
        }
    }
}
