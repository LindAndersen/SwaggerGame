package dk.sdu.smp4.commonplayerlight.services;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface IPlayerLightProcessor {
    void processPlayerLight(SoftEntity player, GameData gameData, World world);
}
