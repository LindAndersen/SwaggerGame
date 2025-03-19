import dk.sdu.smp4.player.PlayerControlSystem;
import dk.sdu.smp4.player.PlayerPlugin;

module Player {
    exports dk.sdu.smp4.player;
    requires Common;
    provides dk.sdu.smp4.common.Services.IGamePluginService with PlayerPlugin;
    provides dk.sdu.smp4.common.Services.IEntityProcessingService with PlayerControlSystem;

}