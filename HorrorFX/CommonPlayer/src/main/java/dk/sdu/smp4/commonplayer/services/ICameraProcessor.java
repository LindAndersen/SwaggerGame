package dk.sdu.smp4.commonplayer.services;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface ICameraProcessor {
    void updateTarget(Entity player, GameData gameData, World world);
}
