import dk.sdu.smp4.commonplayerlight.services.*;
import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.player.PlayerPlugin;
import dk.sdu.smp4.player.PlayerControlSystem;

module Player {
    uses IPlayerLightPlugin;
    uses IPlayerLightProcessor;
    uses IQuestInteractable;
    uses IToggleableLight;
    uses dk.sdu.smp4.common.interactable.Services.InventorySPI;
    requires Common;
    requires CommonEnemy;
    requires CommonPlayerLight;
    requires CommonInteractable;
    requires javafx.graphics;
    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with PlayerControlSystem;
}