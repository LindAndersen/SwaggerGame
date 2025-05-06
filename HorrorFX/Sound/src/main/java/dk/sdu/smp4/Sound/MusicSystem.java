package dk.sdu.smp4.Sound;

import dk.sdu.smp4.common.events.data.InventoryUpdateEvent;
import dk.sdu.smp4.common.events.data.PlayerHitEvent;
import dk.sdu.smp4.common.events.data.PlayerPositionEvent;
import dk.sdu.smp4.common.events.data.SpiderPositionEvent;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class MusicSystem {

    private static final String HIT_SOUND = "/background/man-scream.wav";
    private static final String PROXIMITY_SOUND = "/background/cropped_aggressive_scary.wav";
    private static final String DEFAULT_SOUND = "/background/chill_scary.wav";
    private static final String RELOAD_RESIN_SOUND = "/Player/reload_torch.wav";
    private static final String PICKUP_ITEM = "/Player/pickup_item.wav";
    private double playerX, playerY, spiderX, spiderY;
    private static final MusicSystem INSTANCE = new MusicSystem();

    public MusicSystem() {
        IEventBus eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        assert eventBus != null;
        eventBus.subscribe(PlayerPositionEvent.class, event -> {
            playerX = event.getX();
            playerY = event.getY();
        });

        eventBus.subscribe(SpiderPositionEvent.class, event -> {
            spiderX = event.getX();
            spiderY = event.getY();
        });
        eventBus.subscribe(PlayerHitEvent.class, event -> {
            SoundService.playSound(HIT_SOUND);
        });

        eventBus.subscribe(InventoryUpdateEvent.class, event -> {
            if (event.getType().equals(InventorySlotItems.RESIN) && !event.getWasPickedup())
            {
                SoundService.playSound(RELOAD_RESIN_SOUND);
            } else if(event.getWasPickedup())
            {
                SoundService.playSound(PICKUP_ITEM);
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

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
