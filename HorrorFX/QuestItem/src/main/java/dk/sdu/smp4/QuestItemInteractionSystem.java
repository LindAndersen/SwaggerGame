package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

import javax.management.QueryEval;
import javax.swing.*;

public class QuestItemInteractionSystem implements IQuestInteractable {
    //imlement the other interface.... then put all this into that interfaces proces method... then it work :)
    @Override
    public void interact(Entity player, GameData gameData, World world) {

        for(Entity questItem : world.getEntities(QuestItem.class))
        {
            if (isQuestItemWithinReach(player, questItem))
            {
                System.out.println("Yoink");

                //swapQuestItemPosition((QuestItem) questItem);
                questItem.setX(50);
                questItem.setY(50);


                // 2. Display popup, when pressing E.
                displayQuestPopup((QuestItem) questItem);
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

    private void displayQuestPopup(QuestItem questItem) {
        // Display a pop-up with quest details


        // is quest started? (if statement check)
        // If not display quest description start
        JOptionPane.showMessageDialog(null, questItem.getQuestDescription());
        // set quest started to true

        // (if check is quest is already started) if started, check distance to player.
        //TODO check distance to player, if within reach done. (queststarted booleaen should be true)
        //remove entity when done.

    }

    private void swapQuestItemPosition(QuestItem questItem) {
        // Swap quest item position
        questItem.setX(questItem.getQuestCompX());
        questItem.setY(questItem.getQuestCompY());
    }
    private boolean isQuestComplete() {
        // Check if quest is complete
        return false;
    }

}


