package dk.sdu.smp4;
import dk.sdu.smp4.common.data.SoftEntity;

public class QuestItem extends SoftEntity {
    public int getQuestRadius() {
        return questRadius;
    }

    public int getQuestY() {
        return questY;
    }

    public int getQuestX() {
        return questX;
    }

    public int getQuestID() {
        return questID;
    }

    public String getQuestDescription() {
        return questDescription;
    }


    public String getQuestName() {
        return questName;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public int getQuestCompX() {
        return questCompX;
    }

    public int getQuestCompY() {
        return questCompY;
    }

    private boolean isStarted = false;
    private String questName;
    private String questDescription;
    private int questID;
    private int questX;
    private int questY;
    private int questRadius;
    private int questCompX;
    private int questCompY;



    public QuestItem(String questName, String questDescription, int questID, int questX, int questY, int questRadius, int questCompX, int questCompY) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.questID = questID;
        this.questX = questX;
        this.questY = questY;
        this.questRadius = questRadius;
        this.questCompX = questX;
        this.questCompY = questY;
    }
}
