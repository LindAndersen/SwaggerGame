import dk.sdu.smp4.QuestItemInteractionSystem;
import dk.sdu.smp4.QuestItemPlugin;

module QuestItem {
    requires CommonInteractable;
    requires Common;
    requires java.desktop;
    provides dk.sdu.smp4.common.Services.IGamePluginService with QuestItemPlugin;
    provides dk.sdu.smp4.common.interactable.Services.IQuestInteractable with QuestItemInteractionSystem;


}