import dk.sdu.smp4.common.Services.GUI.IGUIManager;
import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.gui.services.GUIManagerProvider;

module CommonGUIElements {
    uses dk.sdu.smp4.common.events.services.IEventBus;
    uses dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
    uses dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;
    uses IEntityLoaderService;
    uses dk.sdu.smp4.map.services.IMapGenerator;
    exports dk.sdu.smp4.common.gui.elements;
    exports dk.sdu.smp4.common.gui.services;

    requires javafx.graphics;
    requires javafx.controls;
    requires CommonEvents;
    requires Common;
    requires CommonPlayer;
    requires CommonPlayerLight;
    requires CommonLightSource;
    requires CommonMap;

    provides IGUIManager with GUIManagerProvider;
}