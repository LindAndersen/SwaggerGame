package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public class QuestItemPlugin implements IGamePluginService {

    private Entity QuestNote;
    @Override
    public void start(GameData gameData, World world) {
        QuestItem childQuest1 = CreateQuest("child1", "poke my ass", 400, 200, 8);
        QuestItem childQuest2 = CreateQuest("child2", "poke my eyes", 500, 100, 8);
        QuestItem parentQuest = CreateQuest("name","description",400, 600, 8);
        parentQuest.addChildQuest(childQuest1);
        parentQuest.addChildQuest(childQuest2);
        world.addEntity(parentQuest);
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
