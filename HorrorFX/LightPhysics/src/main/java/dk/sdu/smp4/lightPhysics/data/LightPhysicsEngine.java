package dk.sdu.smp4.lightPhysics.data;

import dk.sdu.smp4.common.Services.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.*;
import dk.sdu.smp4.common.lightsource.data.CommonLightSource;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.*;

public class LightPhysicsEngine implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(CommonLightSource.class)) {
            CommonLightSource light = (CommonLightSource) entity;

            // Gather obstacles
            List<Polygon> obstacles = new ArrayList<>();
            for (Entity obstacle : world.getEntities(HardEntity.class)) {
                if (!obstacle.getID().equals(light.getID())) {
                    obstacles.add(getTranslatedPolygon(obstacle));
                }
            }

            // Calculate light polygon
            double[] polygonCoords = calculateLightPolygon(light, obstacles);

            // Translate coordinates to local space (around light's position)
            for (int i = 0; i < polygonCoords.length; i += 2) {
                polygonCoords[i] -= light.getX();
                polygonCoords[i + 1] -= light.getY();
            }

            // Apply to light entity
            light.setPolygonCoordinates(polygonCoords);
            //light.setPaint(Color.rgb(255, 255, 200, 0.25));
        }
    }

    private Polygon getTranslatedPolygon(Entity entity) {
        double[] coords = entity.getPolygonCoordinates();
        Polygon polygon = new Polygon();
        double angle = Math.toRadians(entity.getRotation());

        if (entity.isShouldRotateAlternative()) {
            double pivotX = entity.getX();
            double pivotY = entity.getY();

            for (int i = 0; i < coords.length; i += 2) {
                double x = coords[i] - pivotX;
                double y = coords[i + 1] - pivotY;

                double rotatedX = x * Math.cos(angle) - y * Math.sin(angle);
                double rotatedY = x * Math.sin(angle) + y * Math.cos(angle);

                polygon.getPoints().addAll(rotatedX + pivotX, rotatedY + pivotY);
            }
        } else {
            double centerX = 0;
            double centerY = 0;
            for (int i = 0; i < coords.length; i += 2) {
                centerX += coords[i];
                centerY += coords[i + 1];
            }
            centerX /= (coords.length / 2);
            centerY /= (coords.length / 2);

            for (int i = 0; i < coords.length; i += 2) {
                double dx = coords[i] - centerX;
                double dy = coords[i + 1] - centerY;

                double rotatedX = dx * Math.cos(angle) - dy * Math.sin(angle);
                double rotatedY = dx * Math.sin(angle) + dy * Math.cos(angle);

                polygon.getPoints().addAll(rotatedX + centerX + entity.getX(), rotatedY + centerY + entity.getY());
            }
        }

        return polygon;
    }

    private double[] calculateLightPolygon(CommonLightSource light, List<Polygon> obstacles) {
        List<PointWithAngle> points = new ArrayList<>();
        double originX = light.getX();
        double originY = light.getY();
        int numRays = light.getNumRays();
        double radius = light.getRadius() * light.getRadiusFactor();

        double rotationRad = Math.toRadians(light.getRotation());
        double spreadRad = Math.toRadians(light.getAngleWidth());
        double startAngle = rotationRad - spreadRad / 2;
        double endAngle = rotationRad + spreadRad / 2;

        for (int i = 0; i < numRays; i++) {
            double angle = startAngle + i * (endAngle - startAngle) / (numRays - 1);
            angle = (angle + 2 * Math.PI) % (2 * Math.PI); // Normalize

            double endX = originX + radius * Math.cos(angle);
            double endY = originY + radius * Math.sin(angle);

            double[] closestPoint = {endX, endY};
            double minDist = distance(originX, originY, endX, endY);

            for (Polygon obstacle : obstacles) {
                double[] intersection = findRayPolygonIntersection(originX, originY, endX, endY, obstacle);
                if (intersection != null) {
                    double dist = distance(originX, originY, intersection[0], intersection[1]);
                    if (dist < minDist) {
                        closestPoint = intersection;
                        minDist = dist;
                    }
                }
            }

            points.add(new PointWithAngle(closestPoint[0], closestPoint[1], angle));
        }

        // Sort by angle to form valid polygon shape
        points.sort(Comparator.comparingDouble(p -> p.angle));
        double[] coords = new double[points.size() * 2];
        for (int i = 0; i < points.size(); i++) {
            coords[i * 2] = points.get(i).x;
            coords[i * 2 + 1] = points.get(i).y;
        }

        return coords;
    }

    private double[] findRayPolygonIntersection(double x1, double y1, double x2, double y2, Polygon polygon) {
        List<Double> points = polygon.getPoints();
        double[] closestIntersection = null;
        double minDist = Double.MAX_VALUE;

        for (int i = 0; i < points.size(); i += 2) {
            double x3 = points.get(i);
            double y3 = points.get(i + 1);
            double x4 = points.get((i + 2) % points.size());
            double y4 = points.get((i + 3) % points.size());

            double[] intersection = getLineIntersection(x1, y1, x2, y2, x3, y3, x4, y4);
            if (intersection != null) {
                double dist = distance(x1, y1, intersection[0], intersection[1]);
                if (dist < minDist) {
                    closestIntersection = intersection;
                    minDist = dist;
                }
            }
        }

        return closestIntersection;
    }

    private double[] getLineIntersection(double x1, double y1, double x2, double y2,
                                         double x3, double y3, double x4, double y4) {
        double denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (denom == 0) return null;

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denom;
        double u = ((x1 - x3) * (y1 - y2) - (y1 - y3) * (x1 - x2)) / denom;

        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            return new double[]{
                    x1 + t * (x2 - x1),
                    y1 + t * (y2 - y1)
            };
        }

        return null;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }

    private static class PointWithAngle {
        double x, y, angle;
        PointWithAngle(double x, double y, double angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }
    }
}
