import dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;

module Core {
    requires CommonLightSource;
    requires CommonPlayerLight;
    requires Common;
    requires CommonGUIElements;
    requires javafx.controls;
    opens dk.sdu.smp4.main to javafx.graphics;
    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
}