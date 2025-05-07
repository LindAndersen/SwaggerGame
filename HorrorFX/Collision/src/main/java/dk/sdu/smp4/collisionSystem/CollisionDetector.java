package dk.sdu.smp4.collisionSystem;

import dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities(SoftEntity.class)){
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

    public boolean collides(Entity a, Entity b) {
        double[] aCoords = a.getPolygonCoordinates();
        double[] bCoords = b.getPolygonCoordinates();

        if (aCoords.length < 6 || bCoords.length < 6) return false; // need at least 3 points

        double ax = a.getX();
        double ay = a.getY();
        double bx = b.getX();
        double by = b.getY();

        return polygonsCollide(aCoords, ax, ay, bCoords, bx, by);
    }

    private boolean polygonsCollide(double[] poly1, double offsetX1, double offsetY1,
                                    double[] poly2, double offsetX2, double offsetY2) {
        return !hasSeparatingAxis(poly1, offsetX1, offsetY1, poly2, offsetX2, offsetY2) &&
                !hasSeparatingAxis(poly2, offsetX2, offsetY2, poly1, offsetX1, offsetY1);
    }

    private boolean hasSeparatingAxis(double[] polyA, double offsetX1, double offsetY1,
                                      double[] polyB, double offsetX2, double offsetY2) {
        int lenA = polyA.length;

        for (int i = 0; i < lenA; i += 2) {
            int next = (i + 2) % lenA;

            double x1 = polyA[i];
            double y1 = polyA[i + 1];
            double x2 = polyA[next];
            double y2 = polyA[next + 1];

            // Edge vector
            double edgeX = x2 - x1;
            double edgeY = y2 - y1;

            // Normal vector (perpendicular axis)
            double axisX = -edgeY;
            double axisY = edgeX;

            // Normalize axis
            double length = Math.sqrt(axisX * axisX + axisY * axisY);
            if (length == 0) continue;  // skip degenerate edges
            axisX /= length;
            axisY /= length;

            // Project both polygons onto axis
            double[] projectionA = projectPolygon(polyA, offsetX1, offsetY1, axisX, axisY);
            double[] projectionB = projectPolygon(polyB, offsetX2, offsetY2, axisX, axisY);

            // Check for gap
            if (projectionA[1] < projectionB[0] || projectionB[1] < projectionA[0]) {
                return true; // Separating axis found
            }
        }

        return false; // No separating axis found
    }

    private double[] projectPolygon(double[] poly, double offsetX, double offsetY,
                                    double axisX, double axisY) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < poly.length; i += 2) {
            double x = poly[i] + offsetX;
            double y = poly[i + 1] + offsetY;

            double projection = x * axisX + y * axisY;

            if (projection < min) min = projection;
            if (projection > max) max = projection;
        }

        return new double[]{min, max};
    }
}
