package dk.sdu.smp4.Sound;

import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

public class MusicSystem {

    private static final String HIT_SOUND = "/background/man-scream.wav";
    private static final String PROXIMITY_SOUND = "/background/cropped_aggressive_scary.wav";
    private static final String DEFAULT_SOUND = "/background/chill_scary.wav";
    private static final String RELOAD_SOUND = "/Player/reload_torch.wav";
    private double playerX, playerY, spiderX, spiderY;
    private static final MusicSystem INSTANCE = new MusicSystem();

    public MusicSystem() {
        EventBus.subscribe(PlayerPositionEvent.class, event -> {
            playerX = event.getX();
            playerY = event.getY();
        });

        EventBus.subscribe(SpiderPositionEvent.class, event -> {
            spiderX = event.getX();
            spiderY = event.getY();
        });
        EventBus.subscribe(PlayerHitEvent.class, event -> {
            SoundService.playSound(HIT_SOUND);
        });

        EventBus.subscribe(InventoryUpdateEvent.class, event -> {
            if (event.getType().equals(InventorySlotItems.RESIN))
            {
                SoundService.playSound(RELOAD_SOUND);
            }
        });
    }

    public static MusicSystem getInstance(){
        return INSTANCE;
    }

    public void play() {

        if (playerX == 0 & playerY == 0 & spiderX == 0 & spiderY == 0){
            return;
        }

        boolean close = Math.abs(spiderX - playerX) < 200 && Math.abs(spiderY - playerY) < 200;

        if (close) {
            SoundService.playSound(PROXIMITY_SOUND);
            SoundService.stopSound(DEFAULT_SOUND);
        } else {
            SoundService.playSound(DEFAULT_SOUND);
            SoundService.stopSound(PROXIMITY_SOUND);
        }
    }
}
