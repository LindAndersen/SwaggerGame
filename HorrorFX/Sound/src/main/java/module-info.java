import dk.sdu.smp4.Sound.MusicSystemProvider;
import dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;

module Sound {
    requires Common;
    requires java.desktop;
    requires CommonInteractable;
    provides IPostEntityProcessingService with MusicSystemProvider;
}
