import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;

module Key {
    requires CommonInteractable;
    requires Common;
    requires javafx.graphics;
    provides IQuestInteractable with dk.sdu.smp4.key.KeyControlSystem;
    provides IGamePluginService with dk.sdu.smp4.key.KeyPlugin;
}