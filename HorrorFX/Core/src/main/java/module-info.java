module Core {
    requires javafx.graphics;
    requires Common;
    requires javafx.controls;

    opens dk.sdu.smp4.main to javafx.graphics;
    uses dk.sdu.smp4.common.Services.IGamePluginService;
    uses dk.sdu.smp4.common.Services.IEntityProcessingService;
    uses dk.sdu.smp4.common.Services.IPostEntityProcessingService;
}