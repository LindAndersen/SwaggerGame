package dk.sdu.smp4.graphics;

import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class PlayerRenderer {

    private final Map<String, ImageView> spriteMap = new HashMap<>();
    private final Image idleLeft = new Image(getClass().getResourceAsStream("/textures/player/idle_left.png"));
    private final Image idleRight = new Image(getClass().getResourceAsStream("/textures/player/idle_right.png"));
    private final Image moveLeft = new Image(getClass().getResourceAsStream("/textures/player/move_left.gif"));
    private final Image moveRight = new Image(getClass().getResourceAsStream("/textures/player/move_right.gif"));

    public void render(Player player, GameData gameData, World world, Pane gameWindow) {
        ImageView sprite = spriteMap.get(player.getID());

        if (sprite == null) {
            sprite = new ImageView();
            spriteMap.put(player.getID(), sprite);
            gameWindow.getChildren().add(sprite);
        }

        double dx = player.getX() - player.getPreviousX();
        double dy = player.getY() - player.getPreviousY();
        boolean moving = Math.abs(dx) > 0.1 || Math.abs(dy) > 0.1;
        boolean facingRight = dx >= 0;

        Image targetImage = (moving
                ? (facingRight ? moveRight : moveLeft)
                : (facingRight ? idleRight : idleLeft));

        if (sprite.getImage() != targetImage) {
            sprite.setImage(targetImage);
        }

        sprite.setTranslateX(player.getX());
        sprite.setTranslateY(player.getY());
    }
}
