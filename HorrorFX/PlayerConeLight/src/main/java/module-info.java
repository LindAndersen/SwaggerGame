import dk.sdu.smp4.playerconelight.PlayerConeLightPlugin;
import dk.sdu.smp4.playerconelight.PlayerConeLightProcessor;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

module PlayerConeLight {
    requires javafx.graphics;
    requires Common;
    requires CommonLightSource;
    requires CommonPlayerLight;
    provides IPlayerLightPlugin with PlayerConeLightPlugin;
    provides IPlayerLightProcessor with PlayerConeLightProcessor;
}