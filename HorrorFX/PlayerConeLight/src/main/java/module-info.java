import dk.sdu.smp4.playerconelight.*;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;
import dk.sdu.smp4.commonplayerlight.services.IToggleableLight;

module PlayerConeLight {
    requires javafx.graphics;
    requires Common;
    requires CommonLightSource;
    requires CommonPlayerLight;
    provides IPlayerLightPlugin with PlayerConeLightPlugin;
    provides IPlayerLightProcessor with PlayerConeLightProcessor;
    provides IToggleableLight with ConeLight;
}