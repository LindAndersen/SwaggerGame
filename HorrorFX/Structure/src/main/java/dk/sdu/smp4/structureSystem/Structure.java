package dk.sdu.smp4.structureSystem;

import dk.sdu.smp4.common.data.DynamicEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.StaticEntity;
import dk.sdu.smp4.common.data.World;

public class Structure extends StaticEntity {
    @Override
    public void collide(World world, Entity entity) {
        DynamicEntity player = (DynamicEntity) entity;
        if (player.isSolid()) {
            System.out.println();

            // Check collision with sides (left/right)
            if (player.getPreviousX() - player.getRadius() <= this.getX() + this.getWidth() / 2 || player.getPreviousX() + player.getRadius() >= this.getX() - this.getWidth() / 2) {
                if (player.getPreviousY() - player.getRadius() <= this.getY() + this.getHeight() / 2 && player.getPreviousY() + player.getRadius() >= this.getY() - this.getHeight() / 2) {
                    player.setX(player.getPreviousX());
                }
            }

            // Check collision with top/bottom
            if (player.getPreviousY() - player.getRadius() <= this.getY() + this.getHeight() / 2 || player.getPreviousY() + player.getRadius() >= this.getY() - this.getHeight() / 2) {
                if (player.getPreviousX() - player.getRadius() <= this.getX() + this.getWidth() / 2 && player.getPreviousX() + player.getRadius() >= this.getX() - this.getWidth() / 2) {
                    player.setY(player.getPreviousY());
                }
            }


//            // Check collision with sides (left/right)
//            if ((player.getPreviousX() - player.getRadius() <= this.getX() + this.getWidth()/2 || player.getPreviousX() + player.getRadius() >= this.getX() - this.getWidth()/2)
//                    && (player.getPreviousY() - player.getRadius() <= this.getY() + this.getHeight()/2 && player.getPreviousY() + player.getRadius() >= this.getY() - this.getHeight()/2)) {
//                player.setX(player.getPreviousX());
//            }
//
//            // Check collision with top/bottom
//            if ((player.getPreviousY() - player.getRadius() <= this.getY() + this.getHeight()/2 || player.getPreviousY() + player.getRadius() >= this.getY() - this.getHeight()/2)
//                    && (player.getPreviousX() - player.getRadius() <= this.getX() + this.getWidth()/2 && player.getPreviousX() + player.getRadius() >= this.getX() - this.getWidth()/2)) {
//                player.setY(player.getPreviousY());
//            }
//        }
        }
    }
}
