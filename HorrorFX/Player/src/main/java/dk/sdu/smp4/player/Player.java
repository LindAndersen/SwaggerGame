package dk.sdu.smp4.player;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.World;

public class Player extends Entity {

    double currentX, currentY;

    @Override
    public void collide(World world, Entity entity) {
        if ((this.getX() == entity.getX()+entity.getRadius() || this.getX() == entity.getX()-entity.getRadius())
                && (this.getY() < entity.getY()+entity.getRadius() && this.getY() > entity.getY()-entity.getRadius())){ //If player collides with side
            this.setX(currentX);
        }
        if ((this.getY() == entity.getY()+entity.getRadius() || this.getY() == entity.getY()-entity.getRadius())
                && (this.getX() < entity.getX()+entity.getRadius() && this.getX() > entity.getX()-entity.getRadius())){ //If player collides with top/bottom
            this.setY(currentY);
        }
    }

    public void setCurrentCoords(double x, double y) {
        currentX = x;
        currentY = y;
    }
}
