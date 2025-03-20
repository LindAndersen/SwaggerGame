import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;
import dk.sdu.smp4.player.PlayerControlSystem;
import dk.sdu.smp4.player.PlayerPlugin;

module Player {
    //QuestItem should find player with serviceloader
    exports dk.sdu.smp4.player;
    uses IPlayerLightPlugin;
    uses IPlayerLightProcessor;
    requires Common;
    requires CommonPlayerLight;
    provides dk.sdu.smp4.common.Services.IGamePluginService with PlayerPlugin;
    provides dk.sdu.smp4.common.Services.IEntityProcessingService with PlayerControlSystem;

}