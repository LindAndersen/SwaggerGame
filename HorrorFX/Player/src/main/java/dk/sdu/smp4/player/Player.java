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
}