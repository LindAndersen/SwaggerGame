import dk.sdu.smp4.Sound.MusicSystemProvider;
import dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;

module Sound {
    uses dk.sdu.smp4.common.events.services.IEventBus;
    requires Common;
    requires java.desktop;
    requires CommonInteractable;
    requires CommonEvents;
    provides IPostEntityProcessingService with MusicSystemProvider;
}
