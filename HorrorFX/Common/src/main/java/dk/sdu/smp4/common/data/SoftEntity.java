package dk.sdu.smp4.common.data;

public class SoftEntity extends Entity {
    private double previousX, previousY;
    private double radius;

    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }


    public double getPreviousX() {
        return previousX;
    }

    public void setPreviousX(double previousX) {
        this.previousX = previousX;
    }

    public double getPreviousY() {
        return previousY;
    }

    public void setPreviousY(double previousY) {
        this.previousY = previousY;
    }

    @Override
    public void collide(World world, Entity entity) {
    }
}
