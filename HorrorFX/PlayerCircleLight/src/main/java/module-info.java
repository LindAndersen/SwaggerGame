import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;
import dk.sdu.smp4.playercirclelight.CircleLight;
import dk.sdu.smp4.playercirclelight.PlayerCircleLightPlugin;
import dk.sdu.smp4.playercirclelight.PlayerCircleLightProcessor;

module PlayerCircleLight {
    requires Common;
    requires CommonPlayerLight;
    requires javafx.graphics;
    requires CommonLightSource;
    provides IPlayerLightPlugin with PlayerCircleLightPlugin;
    provides IPlayerLightProcessor with PlayerCircleLightProcessor;
}