package dk.sdu.smp4.common.events;

import dk.sdu.smp4.common.data.Entity;

public class PlayerHitEvent {
    private final Entity player;

    public PlayerHitEvent(Entity player) {
        this.player = player;
    }

    public Entity getPlayer() {
        return player;
    }
}
