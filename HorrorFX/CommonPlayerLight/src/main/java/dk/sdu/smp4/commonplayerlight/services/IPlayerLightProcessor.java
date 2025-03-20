package dk.sdu.smp4.commonplayerlight.services;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface IPlayerLightProcessor {
    void processPlayerLight(Entity player, GameData gameData, World world);
}
