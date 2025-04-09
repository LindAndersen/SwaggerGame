package dk.sdu.smp4.common.events;

public class UpdateHUDLifeEvent {
    private final int lives;

    public UpdateHUDLifeEvent(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }
}
