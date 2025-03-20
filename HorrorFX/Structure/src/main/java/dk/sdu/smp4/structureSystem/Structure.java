package dk.sdu.smp4.structureSystem;

import dk.sdu.smp4.common.data.DynamicEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.StaticEntity;
import dk.sdu.smp4.common.data.World;

public class Structure extends StaticEntity {
    @Override
    public void collide(World world, Entity entity) {
        DynamicEntity player = (DynamicEntity) entity;
        // Check collision with sides (left/right)
        if ((player.getPreviousX() - player.getRadius() <= this.getX() + this.getRadius() || player.getPreviousX() + player.getRadius() >= this.getX() - this.getRadius())
                && (player.getPreviousY() - player.getRadius() <= this.getY() + this.getRadius() && player.getPreviousY() + player.getRadius() >= this.getY() - this.getRadius())) {
            player.setX(player.getPreviousX());
        }

        // Check collision with top/bottom
        if ((player.getPreviousY() - player.getRadius() <= this.getY() + this.getRadius() || player.getPreviousY() + player.getRadius() >= this.getY() - this.getRadius())
                && (player.getPreviousX() - player.getRadius() <= this.getX() + this.getRadius() && player.getPreviousX() + player.getRadius() >= this.getX() - this.getRadius())) {
            player.setY(player.getPreviousY());
        }
    }
}
