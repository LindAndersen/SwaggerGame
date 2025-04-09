import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;
import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.player.PlayerPlugin;
import dk.sdu.smp4.player.PlayerControlSystem;

module Player {
    //QuestItem should find player with serviceloader
    exports dk.sdu.smp4.player;
    uses IPlayerLightPlugin;
    uses IPlayerLightProcessor;
    uses IQuestInteractable;
    requires CommonPlayerLight;
    requires CommonInteractable;
    requires javafx.graphics;
    requires Common;
    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with PlayerControlSystem;
}