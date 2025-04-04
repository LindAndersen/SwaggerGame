package dk.sdu.smp4.graphics;

import dk.sdu.smp4.common.Services.IGraphicsProcessingService;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.player.Player;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraphicsPlugin implements IGraphicsProcessingService {

    private final PlayerRenderer playerRenderer = new PlayerRenderer();
    private final Map<String, Polygon> polygonMap = new ConcurrentHashMap<>();

    @Override
    public void render(GameData gameData, World world, Pane gameWindow) {

        // First: handle Player rendering separately
        world.getEntities().stream()
                .filter(entity -> entity instanceof Player)
                .map(entity -> (Player) entity)
                .forEach(player -> playerRenderer.render(player, gameData, world, gameWindow));

        // Then: render all non-Player entities as polygons
        world.getEntities().forEach(entity -> {
            if (entity instanceof Player) return; // Already handled

            Polygon poly = polygonMap.get(entity.getID());
            if (poly == null) {
                poly = new Polygon(entity.getPolygonCoordinates());
                polygonMap.put(entity.getID(), poly);
                gameWindow.getChildren().add(poly);
            }

            poly.setTranslateX(entity.getX());
            poly.setTranslateY(entity.getY());
            poly.getTransforms().clear();

            if (entity.isShouldRotateAlternative()) {
                poly.getTransforms().add(new Rotate(entity.getRotation(), 0, 0));
            } else {
                poly.setRotate(entity.getRotation());
            }

            poly.setFill(entity.getPaint());
        });

        // Remove deleted polygon entities
        polygonMap.keySet().removeIf(id -> {
            if (world.getEntity(id) == null || world.getEntity(id) instanceof Player) {
                Polygon removed = polygonMap.remove(id);
                gameWindow.getChildren().remove(removed);
                return true;
            }
            return false;
        });
    }

}
