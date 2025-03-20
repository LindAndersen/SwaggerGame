package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.player.Player;

public class QuestItemInteractionSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        double questXcord = 0;
        double questYcord = 0;
        double playerXcord = 0;
        double playerYcord = 0;
        double distancePlayerToQuest = 0;
        Entity questNote = null;

        for(Entity questItem : world.getEntities(QuestItem.class)){
            questNote = questItem;
            questXcord = questItem.getX();
            questYcord = questItem.getY();
        }

        // 1. Check if player is within distance
        for (Entity player: world.getEntities(Player.class)){
            playerXcord = player.getX();
            playerYcord = player.getY();

        }

        distancePlayerToQuest = Math.sqrt(Math.pow(questXcord - playerXcord, 2) + Math.pow(questYcord - playerYcord, 2));

        // 2. Check if player presses Space

        if(distancePlayerToQuest < 10 && gameData.getKeys().isDown(GameKeys.SPACE)){
            System.out.println("Yoink");
            world.removeEntity(questNote);

        // 3. Display popup, when pressing space.
        }





    }

}


