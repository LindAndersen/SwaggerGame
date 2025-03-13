module Core {
    requires javafx.graphics;
    requires Common;

    opens dk.sdu.smp4.main to javafx.graphics;
    uses dk.sdu.smp4.common.Services.IGamePluginService;
    uses dk.sdu.smp4.common.Services.IEntityProcessingService;
}