package dk.sdu.smp4.common.events;

public class DoorCreationEvent {
    private final float x;
    private final float y;
    private final float width;
    private final float height;

    public DoorCreationEvent(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
