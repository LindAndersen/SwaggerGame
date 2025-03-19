import dk.sdu.smp4.QuestItemInteractionSystem;
import dk.sdu.smp4.QuestItemPlugin;

module QuestItem {
    requires Common;
    requires Player;
    provides dk.sdu.smp4.common.Services.IGamePluginService with QuestItemPlugin;
    provides dk.sdu.smp4.common.Services.IEntityProcessingService with QuestItemInteractionSystem;


}