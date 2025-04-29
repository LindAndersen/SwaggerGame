package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public class QuestItemPlugin implements IGamePluginService {

    private Entity QuestNote;
    @Override
    public void start(GameData gameData, World world) {
        //QuestItem childQuest2 = CreateQuest("child2", "poke my eyes", 500, 100, 8);
        QuestItem parentQuest = CreateQuest("Find the bronze key","Find the bronze key, to open the room in the middle",gameData.getDisplayWidth() /5, (gameData.getDisplayHeight()/5)+20, 8);
        QuestItem childQuest1 = CreateQuest("Escape!", "You found the golden key! Now you can escape the castle", 360, 400, 8);
        QuestItem childQuest2 = CreateQuest("Victory!", "You escaped the castle, congratulations!", 300, 50, 8);
        //QuestItem parentQuest2 = CreateQuest("Main Quest2tihi","Go save the world again",450, 600, 8);

        //Define Quest relationships.
        parentQuest.addChildQuest(childQuest1);
        //parentQuest2.addChildQuest(childQuest2);

        //Add Quests to the world
        world.addEntity(parentQuest);

        childQuest1.addChildQuest(childQuest2);
        //world.addEntity(parentQuest2);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(QuestNote);
    }

    private QuestItem CreateQuest(String questName, String questDescription, int questX, int questY, int questRadius) {

        QuestItem quest = new QuestItem(questName, questDescription, questX, questY, questRadius);
        quest.setPolygonCoordinates(-5,-5,5,0,-5,5, 10, 10);

        quest.setX(questX);
        quest.setY(questY);

        quest.setRadius(questRadius);
        return quest;
    }
}
