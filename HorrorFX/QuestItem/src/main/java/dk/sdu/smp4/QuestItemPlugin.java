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
        world.addEntity(CreateQuest("name", "description", 1, 600, 400, 8, 100, 140));
        world.addEntity(CreateQuest("name2", "description2", 1, 400, 400, 8, 100, 140));
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(QuestNote);
    }

    private Entity CreateQuest(String questName, String questDescription, int questID, int questX, int questY, int questRadius, int questCompX, int questCompY) {

        SoftEntity quest = new QuestItem(questName, questDescription, questID, questX, questY, questRadius, questCompX, questCompY);
        quest.setPolygonCoordinates(-5,-5,5,0,-5,5, 10, 10);

        quest.setX(questX);
        quest.setY(questY);

        quest.setRadius(questRadius);
        quest.setType("guest");
        return quest;
    }
}
