import dk.sdu.smp4.QuestItemInteractionSystem;
import dk.sdu.smp4.QuestItemPlugin;
import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;

module QuestItem {
    uses dk.sdu.smp4.common.events.services.IEventBus;
    uses dk.sdu.smp4.common.Services.GUI.IGUIManager;
    requires CommonInteractable;
    requires Common;
    requires CommonQuest;
    requires CommonEvents;
    provides IEntityLoaderService with QuestItemPlugin;
    provides dk.sdu.smp4.common.interactable.Services.IQuestInteractable with QuestItemInteractionSystem;


}