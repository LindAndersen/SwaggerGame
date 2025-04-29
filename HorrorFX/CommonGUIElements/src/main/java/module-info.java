import dk.sdu.smp4.common.Services.GUI.IGUIManager;
import dk.sdu.smp4.common.gui.services.GUIManagerProvider;

module CommonGUIElements {
    uses dk.sdu.smp4.common.events.services.IEventBus;
    uses dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
    uses dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
    uses dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;
    uses dk.sdu.smp4.common.Services.GameLoop.IStructurePluginService;
    exports dk.sdu.smp4.common.gui.elements;
    exports dk.sdu.smp4.common.gui.services;
    requires javafx.graphics;
    requires javafx.controls;
    requires CommonEvents;
    requires Common;
    requires CommonPlayer;
    requires CommonPlayerLight;
    requires CommonLightSource;

    provides IGUIManager with GUIManagerProvider;
}