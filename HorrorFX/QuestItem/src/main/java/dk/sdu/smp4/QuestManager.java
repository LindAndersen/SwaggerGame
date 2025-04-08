package dk.sdu.smp4;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QuestManager {
    private Map<String, QuestItem> activeQuests;

    public QuestManager() {
        this.activeQuests = new HashMap<>();
    }

    public boolean isActiveQuest(QuestItem questItem) {
        return activeQuests.containsKey(questItem.getID());
    }
    public void addQuest(QuestItem questItem){
        activeQuests.put(questItem.getID(),questItem);
        //Add subQuests to map
    }

    public void RemoveQuest(QuestItem questItem)
    {
        activeQuests.remove(questItem.getID());
    }

    public boolean isSubQuest(QuestItem questItem)
    {
        for(QuestItem activeQuest : activeQuests.values())
        {
            if(activeQuest.getID().equals(questItem.getParentID()))
            {
                return true;
            }
        }

        return false;
    }
}
