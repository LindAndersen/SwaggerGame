package dk.sdu.smp4;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.events.EventBus;
import dk.sdu.smp4.common.events.GameOverEvent;
import javafx.scene.image.Image;

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
    private final Image defaultGif = new Image(getClass().getResourceAsStream("/quest_hover_gif.gif"), 40, 40, true, true);

    public QuestItem(String questName, String questDescription, int questX, int questY, int questRadius) {
        setImage(defaultGif);
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
