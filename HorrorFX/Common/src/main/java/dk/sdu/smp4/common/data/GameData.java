package dk.sdu.smp4.common.data;

public class GameData {

    private int displayWidth;
    private int displayHeight;
    private double heightToWidthRatio;
    private final GameKeys keys = new GameKeys();
    private boolean isPaused = false;

    private float delta; // NEW FIELD

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public void setHeightToWidthRatio(int height, int width) {
        this.heightToWidthRatio = (double) height / width;
    }

    public double getHeightToWidthRatio() {
        return heightToWidthRatio;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }
}
