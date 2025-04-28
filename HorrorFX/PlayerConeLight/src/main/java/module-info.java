import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.playerconelight.*;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;
import dk.sdu.smp4.commonplayerlight.services.IToggleableLight;

module PlayerConeLight {
    uses dk.sdu.smp4.common.interactable.Services.InventorySPI;
    requires javafx.graphics;
    requires Common;
    requires CommonLightSource;
    requires CommonPlayerLight;
    requires CommonInteractable;
    requires CommonQuest;
    provides IPlayerLightPlugin with PlayerConeLightPlugin;
    provides IPlayerLightProcessor with PlayerConeLightProcessor;
    provides IToggleableLight with ConeLight;
    provides IGamePluginService with ResinPlugin;
    provides IQuestInteractable with ResinControlSystem;
}