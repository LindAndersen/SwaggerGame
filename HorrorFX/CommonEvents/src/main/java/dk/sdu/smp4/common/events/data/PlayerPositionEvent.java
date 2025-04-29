package dk.sdu.smp4.common.events.data;

import dk.sdu.smp4.common.data.Entity;

public class PlayerPositionEvent {
    private final double x, y;
    private final Entity player;

    public PlayerPositionEvent(Entity player, double x, double y) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public Entity getPlayer() { return player; }
}