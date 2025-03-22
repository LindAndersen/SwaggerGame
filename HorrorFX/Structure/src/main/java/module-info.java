import dk.sdu.smp4.structureSystem.StructurePlugin;
import dk.sdu.smp4.common.Services.IGamePluginService;

module Structure {
    exports dk.sdu.smp4.structureSystem;
    requires Common;
    provides IGamePluginService with StructurePlugin;
}