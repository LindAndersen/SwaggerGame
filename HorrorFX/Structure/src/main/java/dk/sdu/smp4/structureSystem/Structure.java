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
        if ((player.getX() == this.getX() + this.getRadius() || player.getX() == this.getX() - this.getRadius())
                && (player.getY() < this.getY() + this.getRadius() && player.getY() > this.getY() - this.getRadius())) {
            player.setX(player.getPreviousX()); // Reset X to prevent movement into structure side
        }

        // Check collision with top/bottom
        if ((player.getY() == this.getY() + this.getRadius() || player.getY() == this.getY() - this.getRadius())
                && (player.getX() < this.getX() + this.getRadius() && player.getX() > this.getX() - this.getRadius())) {
            player.setY(player.getPreviousY()); // Reset Y to prevent movement into structure top/bottom
        }
    }




}
