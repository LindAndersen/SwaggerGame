import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

module Player {
    //QuestItem should find player with serviceloader
    exports dk.sdu.smp4.player;
    uses IPlayerLightPlugin;
    uses IPlayerLightProcessor;
    uses IQuestInteractable;
    requires CommonPlayerLight;
    requires CommonInteractable;
    requires Common;
    provides dk.sdu.smp4.common.Services.IGamePluginService with dk.sdu.smp4.player.PlayerPlugin;
    provides dk.sdu.smp4.common.Services.IEntityProcessingService with dk.sdu.smp4.player.PlayerControlSystem;
}