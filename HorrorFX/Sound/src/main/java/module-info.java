import dk.sdu.smp4.Sound.BackgroundMusicSystem;
import dk.sdu.smp4.Sound.MusicSystem;

module Sound {
    requires Common;
    requires Player;
    requires AISpider;
    requires java.desktop;

    provides dk.sdu.smp4.common.Services.IPostEntityProcessingService with MusicSystem;
    provides dk.sdu.smp4.common.Services.IGamePluginService with BackgroundMusicSystem;

}
