package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.IGraphicsProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraphicsPlugin implements IGraphicsProcessingService {

    private final Map<String, Polygon> polygonMap = new ConcurrentHashMap<>();

    @Override
    public void render(GameData gameData, World world, Pane gameWindow) {
        world.getEntities().forEach(entity -> {
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

        // Cleanup removed entities
        polygonMap.keySet().removeIf(id -> {
            if (world.getEntity(id) == null) {
                Polygon removed = polygonMap.remove(id);
                gameWindow.getChildren().remove(removed);
                return true;
            }
            return false;
        });
    }
}
