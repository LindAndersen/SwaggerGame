module Graphics {
    requires Player;
    requires Common;
    requires javafx.graphics;

    provides dk.sdu.smp4.common.Services.IGraphicsProcessingService
            with dk.sdu.smp4.graphics.GraphicsPlugin;
    opens dk.sdu.smp4.graphics to javafx.graphics;
}
