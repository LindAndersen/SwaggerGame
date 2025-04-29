import dk.sdu.smp4.common.Services.GameLoop.IStructurePluginService;
import dk.sdu.smp4.structureSystem.StructurePlugin;

module Structure {
    exports dk.sdu.smp4.structureSystem;
    requires Common;
    provides IStructurePluginService with StructurePlugin;
}