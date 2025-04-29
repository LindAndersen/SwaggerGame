import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.structureSystem.StructurePlugin;

module Structure {
    exports dk.sdu.smp4.structureSystem;
    requires Common;
    provides IGamePluginService with StructurePlugin;
}