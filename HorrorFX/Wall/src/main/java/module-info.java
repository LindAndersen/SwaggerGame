import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.wallSystem.WallPlugin;

module Wall {
    exports dk.sdu.smp4.wallSystem;
    requires Common;
    provides IEntityLoaderService with WallPlugin;
}