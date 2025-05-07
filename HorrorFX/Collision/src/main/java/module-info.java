import dk.sdu.smp4.collisionSystem.CollisionDetector;
import dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;

module Collision {
    requires Common;
    provides IPostEntityProcessingService with CollisionDetector;
}