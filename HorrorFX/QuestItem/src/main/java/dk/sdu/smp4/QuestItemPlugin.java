package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public class QuestItemPlugin implements IGamePluginService {

    private Entity QuestNote;
    @Override
    public void start(GameData gameData, World world) {
        QuestNote = CreateQuest(gameData);
        world.addEntity(QuestNote);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(QuestNote);
    }

    private Entity CreateQuest(GameData gameData) {

        Entity QuestNote = new QuestItem();
        QuestNote.setPolygonCoordinates(-5,-5,10,0,-5,5, 10, 10);

        QuestNote.setX(20);
        QuestNote.setY(gameData.getDisplayWidth() -20);

        QuestNote.setRadius(8);

        QuestNote.setType("guest_note");

        return QuestNote;
    }
}
