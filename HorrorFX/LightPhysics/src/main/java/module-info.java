import dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;
import dk.sdu.smp4.lightPhysics.data.LightPhysicsEngine;

module LightPhysics {
    requires Common;
    requires CommonLightSource;
    requires javafx.graphics;
    exports dk.sdu.smp4.lightPhysics.data;
    provides IPostEntityProcessingService with LightPhysicsEngine;
}