import dk.sdu.smp4.QuestItemInteractionSystem;
import dk.sdu.smp4.QuestItemPlugin;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;

module QuestItem {
    requires CommonInteractable;
    requires Common;
    requires CommonQuest;
    requires java.desktop;
    requires java.management;
    requires javafx.controls;
    provides IGamePluginService with QuestItemPlugin;
    provides dk.sdu.smp4.common.interactable.Services.IQuestInteractable with QuestItemInteractionSystem;


}