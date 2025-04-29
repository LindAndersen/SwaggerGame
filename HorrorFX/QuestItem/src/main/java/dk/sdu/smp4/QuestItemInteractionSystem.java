package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.GUI.IGUIManager;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.events.data.GameOverEvent;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.commonquest.data.Utility;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class QuestItemInteractionSystem implements IQuestInteractable {
    QuestManager questManager = new QuestManager();

    @Override
    public void interact(Entity player, GameData gameData, World world) {

        for(Entity questItem : world.getEntities(QuestItem.class))
        {
            QuestItem _questItem = (QuestItem) questItem;

            if (Utility.isEntitiesWithinReach(player, questItem))
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

    private void displayQuestPopup(GameData gameData, QuestItem questItem) {
        IEventBus eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        IGUIManager guiManager = getGUIManagers().stream().findFirst().orElse(null);
        // Display a pop-up with quest details
        assert guiManager != null;
        guiManager.setQuestPane("Quest Description", questItem.getQuestDescription());
        // ONLY FOR DEMO, SHOULD 100% REMOVE THIS GARBAGE EVENT HANDLING XD
        if (questItem.getQuestName().equals("Victory!"))
        {
            assert eventBus != null;
            eventBus.post(new GameOverEvent());
        }

    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends IGUIManager> getGUIManagers() {
        return ServiceLoader.load(IGUIManager.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}


