import dk.sdu.smp4.commonplayer.services.ICameraProcessor;
import dk.sdu.smp4.commonplayerlight.services.*;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.player.PlayerPlugin;
import dk.sdu.smp4.player.PlayerControlSystem;

module Player {
    uses IPlayerLightPlugin;
    uses IPlayerLightProcessor;
    uses IQuestInteractable;
    uses IToggleableLight;
    uses dk.sdu.smp4.common.interactable.Services.InventorySPI;
    uses dk.sdu.smp4.common.events.services.IEventBus;
    uses ICameraProcessor;
    requires CommonEnemy;
    requires CommonPlayerLight;
    requires CommonInteractable;
    requires CommonEvents;
    requires CommonPlayer;
    requires Common;
    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with PlayerControlSystem;
}