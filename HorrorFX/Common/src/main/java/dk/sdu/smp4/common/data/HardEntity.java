package dk.sdu.smp4.common.data;

public class HardEntity extends Entity{
    private int size;

    @Override
    public void collide(World world, Entity entity) {
        SoftEntity hitter = (SoftEntity) entity;
        if (hitter.isSolid()) {

            // Check collision with sides (left/right)
            if (hitter.getPreviousX() - hitter.getRadius() <= this.getX() + this.getSize() / 2 || hitter.getPreviousX() + hitter.getRadius() >= this.getX() - this.getSize() / 2) {
                if (hitter.getPreviousY() - hitter.getRadius() <= this.getY() + this.getSize() / 2 && hitter.getPreviousY() + hitter.getRadius() >= this.getY() - this.getSize() / 2) {
                    hitter.setX(hitter.getPreviousX());
                }
            }

            // Check collision with top/bottom
            if (hitter.getPreviousY() - hitter.getRadius() <= this.getY() + this.getSize() / 2 || hitter.getPreviousY() + hitter.getRadius() >= this.getY() - this.getSize() / 2) {
                if (hitter.getPreviousX() - hitter.getRadius() <= this.getX() + this.getSize() / 2 && hitter.getPreviousX() + hitter.getRadius() >= this.getX() - this.getSize() / 2) {
                    hitter.setY(hitter.getPreviousY());
                }
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
