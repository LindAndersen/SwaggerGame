import dk.sdu.smp4.collisionSystem.CollisionDetector;
import dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires javafx.graphics;
    provides IPostEntityProcessingService with CollisionDetector;
}