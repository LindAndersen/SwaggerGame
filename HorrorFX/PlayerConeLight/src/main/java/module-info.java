import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.playerconelight.*;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;
import dk.sdu.smp4.commonplayerlight.services.IToggleableLight;
import dk.sdu.smp4.resin.ResinControlSystem;
import dk.sdu.smp4.resin.ResinPlugin;

module PlayerConeLight {
    uses dk.sdu.smp4.common.interactable.Services.InventorySPI;
    uses dk.sdu.smp4.common.Services.GUI.IGUIManager;
    requires Common;
    requires CommonLightSource;
    requires CommonPlayerLight;
    requires CommonInteractable;
    requires CommonQuest;
    provides IPlayerLightPlugin with PlayerConeLightPlugin;
    provides IPlayerLightProcessor with PlayerConeLightProcessor;
    provides IToggleableLight with ConeLight;
    provides IEntityLoaderService with ResinPlugin;
    provides IQuestInteractable with ResinControlSystem;
}