package dk.sdu.smp4.common.data;

public class HardEntity extends Entity{
    int size;

    @Override
    public void collide(World world, Entity entity) {
        if (!(entity instanceof SoftEntity hitter) || !hitter.isSolid()) return;

        double halfSize = this.getSize() / 2;
        double r = hitter.getRadius();

        //AABB overlap check, axis-aligned bounding boxes
        boolean collidesX = hitter.getX() + r >= this.getX() - halfSize &&
                hitter.getX() - r <= this.getX() + halfSize;
        boolean collidesY = hitter.getY() + r >= this.getY() - halfSize &&
                hitter.getY() - r <= this.getY() + halfSize;

        // Check if fully overlapping: bounce back completely
        if (collidesX && collidesY) {
            boolean prevInsideX = hitter.getPreviousX() + r >= this.getX() - halfSize &&
                    hitter.getPreviousX() - r <= this.getX() + halfSize;
            boolean prevInsideY = hitter.getPreviousY() + r >= this.getY() - halfSize &&
                    hitter.getPreviousY() - r <= this.getY() + halfSize;

            // Resolve axis independently to allow wallriding
            if (!prevInsideX) {
                hitter.setX(hitter.getPreviousX());
            }
            if (!prevInsideY) {
                hitter.setY(hitter.getPreviousY());
            }

            // If previous position was fully valid, but current is fully blocked, snap fully back
            if (prevInsideX && prevInsideY) {
                hitter.setX(hitter.getPreviousX());
                hitter.setY(hitter.getPreviousY());
            }
        }
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
