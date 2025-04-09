import dk.sdu.smp4.structureSystem.StructurePlugin;
import dk.sdu.smp4.common.Services.IGamePluginService;

module Structure {
    exports dk.sdu.smp4.structureSystem;
    requires Common;
    requires javafx.graphics;
    provides IGamePluginService with StructurePlugin;
}