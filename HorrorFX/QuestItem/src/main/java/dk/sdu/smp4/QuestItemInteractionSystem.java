package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

import javax.swing.*;

public class QuestItemInteractionSystem implements IQuestInteractable {
    @Override
    public void interact(Entity player, GameData gameData, World world) {

        for(Entity questItem : world.getEntities(QuestItem.class))
        {
            if (isQuestItemWithinReach(player, questItem))
            {
                System.out.println("Yoink");
                world.removeEntity(questItem);

                // 2. Display popup, when pressing E.
                displayQuestPopup();
            }
        }
    }

    @Override
    public void consume(GameData gameData, World world) {

    }

    private boolean isQuestItemWithinReach(Entity questItem, Entity player)
    {   // 1. Check if player is within distance
        double distancePlayerToQuest = Math.sqrt(Math.pow(questItem.getX() - player.getX(), 2) + Math.pow(questItem.getY() - player.getY(), 2));

        return distancePlayerToQuest < 10;
    }

    private void displayQuestPopup() {
        // Display a pop-up with quest details
        JOptionPane.showMessageDialog(null, "Quest: Retrieve the ancient scroll from the haunted forest.");
    }
}


