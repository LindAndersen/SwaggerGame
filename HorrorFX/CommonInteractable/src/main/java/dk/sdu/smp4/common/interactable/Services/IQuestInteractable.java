package dk.sdu.smp4.common.interactable.Services;


import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface IQuestInteractable {
    void interact(Entity e, GameData gameData, World world);
    void consume(GameData gameData, World world);
}
