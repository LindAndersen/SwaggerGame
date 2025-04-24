import dk.sdu.smp4.Sound.MusicSystem;
import dk.sdu.smp4.Sound.MusicSystemProvider;

module Sound {
    requires Common;
    requires java.desktop;

    provides dk.sdu.smp4.common.Services.IPostEntityProcessingService with MusicSystemProvider;
}
