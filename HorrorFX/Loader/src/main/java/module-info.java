import dk.sdu.loadersystem.MapGenerator;
import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.map.services.IMapGenerator;

module Loader {
    exports dk.sdu.loadersystem;
    uses IEntityLoaderService;
    requires Common;
    requires CommonMap;
    provides IMapGenerator with MapGenerator;
}