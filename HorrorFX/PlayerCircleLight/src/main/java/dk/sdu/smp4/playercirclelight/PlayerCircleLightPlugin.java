package dk.sdu.smp4.playercirclelight;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayerlight.data.CommonPlayerLight;
import dk.sdu.smp4.commonplayerlight.services.IPlayerLightPlugin;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PlayerCircleLightPlugin implements IPlayerLightPlugin {

    @Override
    public void createPlayerLight(Entity player, GameData gameData, World world) {
        Entity lightEntity = new CommonPlayerLight();

        lightEntity.setPaint(Color.GREEN);
        lightEntity.setX(player.getX());
        lightEntity.setY(player.getY());
        lightEntity.setRadius(player.getRadius()*3);
        lightEntity.setRotation(player.getRotation());

        int numPoints = 36; // More points = smoother circle
        double radius = lightEntity.getRadius();
        double[] circlePoints = new double[numPoints * 2]; // Each point has x and y

        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints; // Evenly spaced angles
            circlePoints[i * 2] = Math.cos(angle) * radius; // X coordinate
            circlePoints[i * 2 + 1] = Math.sin(angle) * radius; // Y coordinate
        }

        lightEntity.setPolygonCoordinates(circlePoints);

        world.addEntity(lightEntity);
    }
}
