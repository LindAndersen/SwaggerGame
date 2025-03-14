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
            // Set the position of the cone light (tip at player's position)
            lightEntity.setX(player.getX());
            lightEntity.setY(player.getY());

            // Get the player's rotation in radians
            double playerDirection = Math.toRadians(player.getRotation());

            // List to hold the coordinates for the cone
            List<Double> conePoints = new ArrayList<>();

            // Add the tip of the cone (this will be at the player's position)
            conePoints.add(0.0);  // X coordinate of the tip (relative to light entity)
            conePoints.add(0.0);  // Y coordinate of the tip (relative to light entity)

            // Cone parameters
            double angleWidth = 30; // Spread of the cone
            int numBasePoints = 40; // Number of points for the cone's base
            double baseRadius = player.getRadius() * 6; // Dynamic base size

            // Define the start and end angles for the base
            double startAngle = -Math.toRadians(angleWidth / 2); // Start of cone arc
            double endAngle = Math.toRadians(angleWidth / 2);    // End of cone arc

            // Calculate the base points (relative to the player's position)
            for (int i = 0; i <= numBasePoints; i++) {
                // Calculate base point angle
                double angle = startAngle + (endAngle - startAngle) * i / numBasePoints;

                // Calculate base points without rotation (relative to the player's position)
                double x = Math.cos(angle) * baseRadius;
                double y = Math.sin(angle) * baseRadius;

                // Apply the player's rotation to each point (rotate around the player's position)
                double rotatedX = x * Math.cos(playerDirection) - y * Math.sin(playerDirection);
                double rotatedY = x * Math.sin(playerDirection) + y * Math.cos(playerDirection);

                // Add rotated points to the list
                conePoints.add(rotatedX);
                conePoints.add(rotatedY);
            }

            // Convert List to array and set polygon coordinates
            double[] coordinates = new double[conePoints.size()];
            for (int i = 0; i < coordinates.length; i++) {
                coordinates[i] = conePoints.get(i);
            }

            // Set the cone's polygon coordinates
            lightEntity.setPolygonCoordinates(coordinates);
        }
    }
}
