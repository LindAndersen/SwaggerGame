package dk.sdu.smp4;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.events.EventBus;
import dk.sdu.smp4.common.events.GameOverEvent;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

public class QuestItemInteractionSystem implements IQuestInteractable {
    QuestManager questManager = new QuestManager();

    @Override
    public void interact(Entity player, GameData gameData, World world) {

        for(Entity questItem : world.getEntities(QuestItem.class))
        {
            QuestItem _questItem = (QuestItem) questItem;

            if (isQuestItemWithinReach(player, questItem))
            {
                if(!questManager.isSubQuest(_questItem) && !questManager.isActiveQuest(_questItem))
                {
                    questManager.addQuest(_questItem);
                    world.removeEntity(questItem);
                    // 2. Display popup, when pressing E.
                    displayQuestPopup(gameData, _questItem);

                    // Add subquests to the world
                    for (QuestItem subQuest : _questItem.getChildren()) {
                        world.addEntity(subQuest);
                    }
                }
            }

        }
    }

    @Override
    public void consume(GameData gameData, World world) {

    }

    private boolean isQuestItemWithinReach(Entity questItem, Entity player)
    {   // Check if player is within distance
        double distancePlayerToQuest = Math.sqrt(Math.pow(questItem.getX() - player.getX(), 2) + Math.pow(questItem.getY() - player.getY(), 2));

        return distancePlayerToQuest < 10;
    }

    private void displayQuestPopup(GameData gameData, QuestItem questItem) {
        // Display a pop-up with quest details
        gameData.setQuestPane("Quest Description", questItem.getQuestDescription());
        // ONLY FOR DEMO, SHOULD 100% REMOVE THIS GARBAGE EVENT HANDLING XD
        if (questItem.getQuestName().equals("Victory!"))
        {
            EventBus.post(new GameOverEvent());
        }

    }

}


