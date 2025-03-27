package dk.sdu.smp4.lightPhysics.data;

import dk.sdu.smp4.common.Services.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.*;
import dk.sdu.smp4.common.lightsource.data.CommonLightSource;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class LightPhysicsEngine implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity light : world.getEntities(CommonLightSource.class))
        {
            for (Entity entity : world.getEntities(HardEntity.class))
            {
                if (light.getID().equals(entity.getID()))
                {
                    light.setPaint(Color.GREEN.deriveColor(1,1,1,0.3));
                    continue;
                }

                if(collides(light, entity))
                {
                    // Calculate and update light polygon coordinates based on intersections
                    double[] lightPolygonCoordinates = calculateLightPolygon(entity, (CommonLightSource) light);
                    if (lightPolygonCoordinates.length > 0) {
                        light.setPolygonCoordinates(lightPolygonCoordinates);
                        light.setPaint(Color.RED.deriveColor(1,1,1,0.3));
                    } else {
                        light.setPaint(Color.GREEN.deriveColor(1,1,1,0.3));
                    }
                } else {
                    light.setPaint(Color.GREEN.deriveColor(1,1,1,0.3));
                }
            }
        }
    }

    private Polygon getTranslatedPolygon(Entity entity){
        double[] coords = entity.getPolygonCoordinates();
        Polygon polygon = new Polygon();

        double rotationAngle = Math.toRadians(entity.getRotation());  // Convert rotation to radians
        double pivotX = entity.getX();  // Rotation pivot point (entity's position)
        double pivotY = entity.getY();

        for (int i = 0; i < coords.length; i+=2) {
            double rotatedX = coords[i] * Math.cos(rotationAngle) - coords[i+1] * Math.sin(rotationAngle);
            double rotatedY = coords[i+1] * Math.sin(rotationAngle) + coords[i+1] * Math.cos(rotationAngle);

            double x = rotatedX + pivotX;
            double y = rotatedY + pivotY;

            polygon.getPoints().addAll(x, y);  // Add the rotated and translated point to the polygon
        }

        return polygon;
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        Polygon poly1 = getTranslatedPolygon(entity1);
        Polygon poly2 = getTranslatedPolygon(entity2);
        Shape intersection = Shape.intersect(poly1, poly2);
        return intersection.getBoundsInLocal().getWidth() > 0 && intersection.getBoundsInLocal().getHeight() > 0;
    }

    /**
     * Calculates the light polygon coordinates by casting rays and detecting intersections with world entities.
     */
    private double[] calculateLightPolygon(Entity entity, CommonLightSource light) {
        List<Double> lightPoints = new ArrayList<>();
        double lightX = light.getLIGHT_SOURCE_X();
        double lightY = light.getLIGHT_SOURCE_Y();

        for (int i = 0; i < light.getNUM_RAYS(); i++) {
            double angle = Math.toRadians(i);
            double rayX = lightX + light.getRadius() * Math.cos(angle);
            double rayY = lightY + light.getRadius() * Math.sin(angle);

            double[] closestPoint = {rayX, rayY};


            double[] intersection = findRayPolygonIntersection(lightX, lightY, rayX, rayY, entity);
            if (intersection != null && distance(lightX, lightY, intersection[0], intersection[1])
                    < distance(lightX, lightY, closestPoint[0], closestPoint[1])) {
                closestPoint = intersection;  // Store the closest intersection point
            }

            lightPoints.add(closestPoint[0]);  // Add intersection X coordinate
            lightPoints.add(closestPoint[1]);  // Add intersection Y coordinate
        }

        // Convert List<Double> to double[] array
        double[] lightPolygonCoordinates = new double[lightPoints.size()];
        for (int i = 0; i < lightPoints.size(); i++) {
            lightPolygonCoordinates[i] = lightPoints.get(i);
        }

        return lightPolygonCoordinates;
    }

    /**
     * Finds the closest intersection point between a ray and an entity's polygon.
     */
    private double[] findRayPolygonIntersection(double x1, double y1, double x2, double y2, Entity entity) {
        double[] polygonCoords = entity.getPolygonCoordinates();
        double[] closestIntersection = null;
        double closestDistance = Double.MAX_VALUE;

        for (int i = 0; i < polygonCoords.length; i += 2) {
            double x3 = polygonCoords[i] + entity.getX();
            double y3 = polygonCoords[i + 1] + entity.getY();
            double x4 = polygonCoords[(i + 2) % polygonCoords.length] + entity.getX();
            double y4 = polygonCoords[(i + 3) % polygonCoords.length] + entity.getY();

            double[] intersection = getLineIntersection(x1, y1, x2, y2, x3, y3, x4, y4);
            if (intersection != null) {
                double dist = distance(x1, y1, intersection[0], intersection[1]);
                if (dist < closestDistance) {
                    closestIntersection = intersection;
                    closestDistance = dist;
                }
            }
        }
        return closestIntersection;
    }

    /**
     * Finds the intersection point between two lines (or returns null if they don't intersect).
     */
    private double[] getLineIntersection(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (denom == 0) return null;  // Parallel lines, no intersection

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denom;
        double u = ((x1 - x3) * (y1 - y2) - (y1 - y3) * (x1 - x2)) / denom;
        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            double intersectX = x1 + t * (x2 - x1);
            double intersectY = y1 + t * (y2 - y1);
            return new double[]{intersectX, intersectY};
        }
        return null;
    }

    /**
     * Calculates the distance between two points (x1, y1) and (x2, y2).
     */
    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
