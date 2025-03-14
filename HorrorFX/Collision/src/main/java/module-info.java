import dk.sdu.smp4.collisionSystem.CollisionDetector;
import dk.sdu.smp4.common.Services.IPostEntityProcessor;

module Collision {
    requires Common;
    provides IPostEntityProcessor with CollisionDetector;
}