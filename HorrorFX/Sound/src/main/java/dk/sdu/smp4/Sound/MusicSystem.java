package dk.sdu.smp4.Sound;

import dk.sdu.smp4.common.Services.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.player.Player;
import dk.sdu.smp4.aispider.Enemy;

public class MusicSystem implements IPostEntityProcessingService {

    private boolean hasPlayedHitSound = false;

    @Override
    public void process(GameData gameData, World world) {
        Entity player = world.getEntities(Player.class).stream().findFirst().orElse(null);
        if (player == null) return;

        for (Entity e : world.getEntities(Enemy.class)) {
            boolean overlap = Math.abs(e.getX() - player.getX()) < 1 && Math.abs(e.getY() - player.getY()) < 1;
            if (overlap && !hasPlayedHitSound) {
                // ændre det til hvad der sker når enemy rammer player
                SoundService.playSound("hit.wav");
                hasPlayedHitSound = true;
            }
            if (!overlap) {
                hasPlayedHitSound = false;
            }
        }
    }
}
