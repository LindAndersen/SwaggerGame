package dk.sdu.smp4.common.data;

public class GameKeys {

    private static boolean[] keys;
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 7;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int SPACE = 4;
    public static final int INTERACT = 5;
    public static final int RELOAD = 6;
    public static double mouseY;
    public static double mouseX;
    public static boolean mouseMoved;

    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    public static void setMousePosition(double x, double y){
        mouseX = x;
        mouseY = y;
    }

    public void setMouseMoved(boolean b){
        mouseMoved = b;
    }

    public boolean isMouseMoved(){
        return mouseMoved;
    }
    public void setKey(int k, boolean b) {
        keys[k] = b;
    }

    public boolean isDown(int k) {
        return keys[k];
    }

    public boolean isPressed(int k) {
        return keys[k] && !pkeys[k];
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static double getMouseX() {
        return mouseX;
    }
}
