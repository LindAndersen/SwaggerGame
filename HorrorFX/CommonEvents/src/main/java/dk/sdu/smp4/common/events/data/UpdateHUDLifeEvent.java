package dk.sdu.smp4.common.events.data;

public class UpdateHUDLifeEvent {
    private final int lives;


    private final int maxLives;

    public UpdateHUDLifeEvent(int lives, int maxLives) {
        this.lives = lives;
        this.maxLives = maxLives;
    }

    public int getMaxLives() {
        return maxLives;
    }

    public int getLives() {
        return lives;
    }
}
