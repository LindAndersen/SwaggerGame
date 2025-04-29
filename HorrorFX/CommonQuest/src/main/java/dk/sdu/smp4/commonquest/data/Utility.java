package dk.sdu.smp4.commonquest.data;

import dk.sdu.smp4.common.data.Entity;

public class Utility {

    public static boolean isEntitiesWithinReach(Entity player, Entity door) {
        double distance = Math.sqrt(Math.pow(door.getX() - player.getX(), 2) + Math.pow(door.getY() - player.getY(), 2));
        return distance < 40;  // Adjust based on gameplay needs
    }
}
