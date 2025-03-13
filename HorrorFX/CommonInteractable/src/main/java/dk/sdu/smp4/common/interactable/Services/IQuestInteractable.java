package dk.sdu.smp4.common.interactable.Services;


public interface IQuestInteractable {
    void interact(Entity e);

    void consume(GameData gameData, World world);
}
