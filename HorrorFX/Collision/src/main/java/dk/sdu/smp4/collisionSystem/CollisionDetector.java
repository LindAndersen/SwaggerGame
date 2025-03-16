package dk.sdu.smp4.collisionSystem;

import dk.sdu.smp4.common.Services.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()){
            for(Entity entity2 : world.getEntities()){
                if (entity1.getID().equals(entity2.getID())){
                    continue;
                }

                if (this.collides(entity1, entity2)) {
                    entity1.collide(world, entity2);
                    entity2.collide(world, entity1);
                }
            }
        }
    }

    private Polygon getTranslatedPolygon(Entity entity){
        double[] coords = entity.getPolygonCoordinates();
        Polygon polygon = new Polygon();

        for (int i = 0; i < coords.length; i+=2) {
            double x = coords[i] + entity.getX();
            double y = coords[i+1] + entity.getY();
            polygon.getPoints().addAll(x, y);
        }

        return polygon;
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        Polygon poly1 = getTranslatedPolygon(entity1);
        Polygon poly2 = getTranslatedPolygon(entity2);
        Shape intersection = Shape.intersect(poly1, poly2);
        return intersection.getBoundsInLocal().getWidth() > 0 && intersection.getBoundsInLocal().getHeight() > 0;
    }





}
