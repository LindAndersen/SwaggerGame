package dk.sdu.smp4.commonplayerlight.services;

import dk.sdu.smp4.common.data.DynamicEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface IPlayerLightPlugin {
    void createPlayerLight(DynamicEntity player, GameData gameData, World world);
}
