package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;

import java.util.ArrayList;
import java.util.List;

public class PlayerConeLightPlugin implements IPlayerLightPlugin {
    @Override
    public void createPlayerLight(Entity player, GameData gameData, World world) {
        Entity lightEntity = new ConeLight();


        lightEntity.setX(player.getX());
        lightEntity.setY(player.getY());
        lightEntity.setRadius(player.getRadius()*30);
        lightEntity.setRotation(player.getRotation());


        List<Double> conePoints = new ArrayList<>();
        conePoints.add(0.0);
        conePoints.add(0.0);

        // Add the tip of the cone (single point)
        double angleWidth = 30; // Spread of the cone
        int numBasePoints = 40; // Smoother base
        double playerDirection = Math.toRadians(player.getRotation()); // Convert to radians
        double baseRadius = player.getRadius() * 30; // Dynamic base size

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
        world.addEntity(lightEntity);
    }
}
