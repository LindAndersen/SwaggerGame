package dk.sdu.smp4.common.events;

import dk.sdu.smp4.common.data.Entity;

public class SpiderPositionEvent {
    private final double x, y;
    private final Entity spider;

    public SpiderPositionEvent(Entity spider, double x, double y) {
        this.x = x;
        this.y = y;
        this.spider = spider;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public Entity getPlayer() { return spider; }
}