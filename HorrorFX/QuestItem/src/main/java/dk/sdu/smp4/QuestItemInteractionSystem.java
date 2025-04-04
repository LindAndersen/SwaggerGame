package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import javax.management.QueryEval;
import javax.swing.*;

public class QuestItemInteractionSystem implements IQuestInteractable {
    //implement the other interface.... then put all this into that interfaces proces method... then it work :)
    @Override
    public void interact(Entity player, GameData gameData, World world) {

        for(Entity questItem : world.getEntities(QuestItem.class))
        {
            if (isQuestItemWithinReach(player, questItem))
            {
                System.out.println("Yoink");

//                swapQuestItemPosition((QuestItem) questItem);
                world.removeEntity(questItem);

                // 2. Display popup, when pressing E.
                displayQuestPopup(gameData, (QuestItem) questItem);
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

    private void displayQuestPopup(GameData gameData, QuestItem questItem) {
        // Display a pop-up with quest details

        gameData.setQuestPane("Quest Description", questItem.getQuestDescription());

//        Platform.runLater(() -> {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Quest Description");
//            alert.setHeaderText(null);
//            alert.setContentText(questItem.getQuestDescription());
//            alert.showAndWait();
//        });
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


