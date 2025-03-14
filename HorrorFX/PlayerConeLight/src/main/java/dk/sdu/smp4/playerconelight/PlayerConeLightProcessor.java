package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightProcessor;

import java.util.ArrayList;
import java.util.List;

public class PlayerConeLightProcessor implements IPlayerLightProcessor {
    @Override
    public void processPlayerLight(Entity player, GameData gameData, World world) {
        for (Entity lightEntity : world.getEntities(ConeLight.class)) {
            // Update position and rotation
            lightEntity.setX(player.getX());
            lightEntity.setY(player.getY());
            lightEntity.setRotation(player.getRotation());

            // Recalculate the cone polygon based on the new position & rotation
            List<Double> conePoints = new ArrayList<>();

            // Tip of the cone at (0,0) relative to the light entity
            conePoints.add(0.0);
            conePoints.add(0.0);

            double angleWidth = 30; // Spread of the cone
            int numBasePoints = 40; // Smoother base
            double playerDirection = Math.toRadians(player.getRotation()); // Convert to radians
            double baseRadius = player.getRadius() * 6; // Dynamic base size

            // Define base arc angles centered around player's direction
            double startAngle = playerDirection - Math.toRadians(angleWidth / 2);
            double endAngle = playerDirection + Math.toRadians(angleWidth / 2);

            for (int i = 0; i <= numBasePoints; i++) {
                double angle = startAngle + (endAngle - startAngle) * i / numBasePoints;

                // Points are now relative to (0,0) so they rotate correctly
                double x = Math.cos(angle) * baseRadius;
                double y = Math.sin(angle) * baseRadius;
                conePoints.add(x);
                conePoints.add(y);
            }

            // Convert List to array and set polygon coordinates
            double[] coordinates = new double[conePoints.size()];
            for (int i = 0; i < coordinates.length; i++) {
                coordinates[i] = conePoints.get(i);
            }

            lightEntity.setPolygonCoordinates(coordinates);
        }
    }
}
