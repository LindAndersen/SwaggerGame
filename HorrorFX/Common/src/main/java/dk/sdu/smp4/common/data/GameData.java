package dk.sdu.smp4.common.data;

public class GameData {

    private int displayWidth = 1920;
    private int displayHeight = 1080;
    private final GameKeys keys = new GameKeys();
    private boolean isPaused = false;

    private float delta; // NEW FIELD

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
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
