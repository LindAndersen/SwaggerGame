package dk.sdu.smp4.player;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.World;
import javafx.scene.image.Image;

public class Player extends SoftEntity {
    private final Image moveLeftImage = new Image(getClass().getResourceAsStream("/move_left.gif"));
    private final Image moveRightImage = new Image(getClass().getResourceAsStream("/move_right.gif"));

    public Player()
    {
        this.setImage(moveRightImage);
    }

    public Image getMoveLeftImage() {
        return moveLeftImage;
    }

    public Image getMoveRightImage() {
        return moveRightImage;
    }

    @Override
    public void collide(World world, Entity entity) {
    }
    private int lives = 2;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void loseLife() {
        this.lives--;
    }

    private boolean isDead = false;

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }
}