package dk.sdu.smp4.collisionSystem;

import dk.sdu.smp4.common.Services.IPostEntityProcessor;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public class CollisionDetector implements IPostEntityProcessor {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()){
            for(Entity entity2 : world.getEntities()){
                if (entity1.getID().equals(entity2.getID())){
                    continue;
                }

                int collisionSide = getCollisionSide(entity1, entity2);
                if (collisionSide >= 0){
                    entity2.setBlockedDirection(3-collisionSide, true);
                    entity1.setBlockedDirection(collisionSide, true);
                } else {
                    for (int i = 0; i < 4; i++) {
                        entity2.setBlockedDirection(i, false);
                        entity1.setBlockedDirection(i, false);
                    }
                }
            }
        }
    }

    public int getCollisionSide(Entity e1, Entity e2) {
        float halfSize1 = e1.getRadius();
        float halfSize2 = e2.getRadius();

        double dx = e1.getX() - e2.getX();
        double dy = e1.getY() - e2.getY();

        float combinedHalfWidth = halfSize1 + halfSize2;
        float combinedHalfHeight = halfSize1 + halfSize2;

        if (Math.abs(dx) < combinedHalfWidth && Math.abs(dy) < combinedHalfHeight) {
            double overlapX = combinedHalfWidth - Math.abs(dx);
            double overlapY = combinedHalfHeight - Math.abs(dy);

            if (overlapX < overlapY) {
                if (dx > 0) {
                    return Entity.LEFT;
                } else {
                    return Entity.RIGHT;
                }
            } else {
                if (dy > 0) {
                    return Entity.UP;
                } else {
                    return Entity.DOWN;
                }
            }
        }

        return -1;
    }





}
