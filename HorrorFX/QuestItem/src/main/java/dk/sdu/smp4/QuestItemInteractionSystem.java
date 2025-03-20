package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

public class QuestItemInteractionSystem implements IQuestInteractable {
    @Override
    public void interact(Entity player, GameData gameData, World world) {
        // 1. Check if player is within distance
        for(Entity questItem : world.getEntities(QuestItem.class))
        {
            if (isQuestItemWithinReach(player, questItem))
            {
                System.out.println("Yoink");
                world.removeEntity(questItem);

                // 3. Display popup, when pressing space.
            }
        }
    }

    @Override
    public void consume(GameData gameData, World world) {

    }

    private boolean isQuestItemWithinReach(Entity questItem, Entity player)
    {
        double distancePlayerToQuest = Math.sqrt(Math.pow(questItem.getX() - player.getX(), 2) + Math.pow(questItem.getY() - player.getY(), 2));

        // 2. Check if player presses Space

        return distancePlayerToQuest < 10;
    }
}


