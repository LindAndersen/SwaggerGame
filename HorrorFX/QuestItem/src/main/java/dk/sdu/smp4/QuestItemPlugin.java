package dk.sdu.smp4;

import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

import java.util.Set;

public class QuestItemPlugin implements IEntityLoaderService {
    QuestItem quest1, quest2, quest3;

    @Override
    public void render(World world, int x, int y, int mapCode) {
        switch (mapCode){
            case 9:
                quest1 = CreateQuest("Find the bronze key","Find the bronze key, to open the room in the middle", world.getTileSize(), x, y, 8);
                world.addEntity(quest1);
                break;
            case 10:
                quest2 = CreateQuest("Escape!", "You found the golden key! Now you can escape the castle", world.getTileSize(), x, y, 8);
                break;
            case 11:
                quest3 = CreateQuest("Victory!", "You escaped the castle, congratulations!", world.getTileSize(), x, y, 8);
                break;
            default:
                break;
        }
        if (quest1 != null && quest2 != null && quest3 != null){
            quest1.addChildQuest(quest2);
            quest2.addChildQuest(quest3);
        }
    }

    /*
        //Define Quest relationships.
        parentQuest.addChildQuest(childQuest1);
        //parentQuest2.addChildQuest(childQuest2);

        //Add Quests to the world
        world.addEntity(parentQuest);

        childQuest1.addChildQuest(childQuest2);
        //world.addEntity(parentQuest2);
    */
    private QuestItem CreateQuest(String questName, String questDescription, int tileSize, int questX, int questY, int questRadius) {

        QuestItem quest = new QuestItem(questName, questDescription, questX, questY, questRadius);
        quest.setPolygonCoordinates(-5,-5,5,0,-5,5, 10, 10);

        quest.setX(questX * tileSize + tileSize / 2.0);
        quest.setY(questY * tileSize + tileSize / 2.0);

        quest.setRadius(questRadius);
        return quest;
    }

    @Override
    public Set<Integer> getMapCodes() {
        return Set.of(9, 10, 11);
    }

    @Override
    public void stop(World world) {
        world.getEntities(QuestItem.class).forEach(world::removeEntity);
    }
}
