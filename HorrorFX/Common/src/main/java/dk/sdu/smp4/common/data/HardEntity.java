package dk.sdu.smp4.common.data;

public class HardEntity extends Entity{
    double width;
    double height;

    @Override
    public void collide(World world, Entity entity) {
        SoftEntity hitter = (SoftEntity) entity;
        if (hitter.isSolid()) {

            // Check collision with sides (left/right)
            if (hitter.getPreviousX() - hitter.getRadius() <= this.getX() + this.getWidth() / 2 || hitter.getPreviousX() + hitter.getRadius() >= this.getX() - this.getWidth() / 2) {
                if (hitter.getPreviousY() - hitter.getRadius() <= this.getY() + this.getHeight() / 2 && hitter.getPreviousY() + hitter.getRadius() >= this.getY() - this.getHeight() / 2) {
                    hitter.setX(hitter.getPreviousX());
                }
            }

            // Check collision with top/bottom
            if (hitter.getPreviousY() - hitter.getRadius() <= this.getY() + this.getHeight() / 2 || hitter.getPreviousY() + hitter.getRadius() >= this.getY() - this.getHeight() / 2) {
                if (hitter.getPreviousX() - hitter.getRadius() <= this.getX() + this.getWidth() / 2 && hitter.getPreviousX() + hitter.getRadius() >= this.getX() - this.getWidth() / 2) {
                    hitter.setY(hitter.getPreviousY());
                }
            }
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
