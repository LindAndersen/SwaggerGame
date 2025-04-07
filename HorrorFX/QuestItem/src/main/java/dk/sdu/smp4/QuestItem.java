package dk.sdu.smp4;
import dk.sdu.smp4.common.data.SoftEntity;

import java.util.ArrayList;
import java.util.List;


public class QuestItem extends SoftEntity {
    private String questName;
    private String questDescription;
    private int questX;
    private int questY;
    private int questRadius;
    private String parentID;
    private List<QuestItem> children;

    public QuestItem(String questName, String questDescription, int questX, int questY, int questRadius) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.questX = questX;
        this.questY = questY;
        this.questRadius = questRadius;
        this.children = new ArrayList<>();
    }

    public void addChildQuest(QuestItem child)
    {
        children.add(child);
        child.setParentID(this.parentID);
    }

    public List<QuestItem> getChildren() {
        return children;
    }

    public int getQuestRadius() {
        return questRadius;
    }

    public int getQuestY() {
        return questY;
    }

    public int getQuestX() {
        return questX;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public String getQuestName() {
        return questName;
    }
    public String getParentID(){return parentID;}
    public void setParentID(String parentID){this.parentID = parentID;}
}
