import dk.sdu.smp4.inventory.services.IHasInventory;
import dk.sdu.smp4.commonplayerlight.services.*;
import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.player.Player;
import dk.sdu.smp4.player.PlayerPlugin;
import dk.sdu.smp4.player.PlayerControlSystem;

module Player {
    uses IPlayerLightPlugin;
    uses IPlayerLightProcessor;
    uses IQuestInteractable;
    uses IToggleableLight;
    requires CommonPlayerLight;
    requires CommonInteractable;
    requires javafx.graphics;
    requires Common;
    requires Inventory;
    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with PlayerControlSystem;
    provides IHasInventory with Player;
}