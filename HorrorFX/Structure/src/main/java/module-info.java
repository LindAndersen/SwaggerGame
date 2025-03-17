import dk.sdu.smp4.structureSystem.WallPlugin;
import dk.sdu.smp4.common.Services.IGamePluginService;

module Structure {
    requires Common;
    provides IGamePluginService with WallPlugin;
}