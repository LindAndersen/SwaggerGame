package dk.sdu.smp4.common.Services.GameLoop;

import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface IStructurePluginService {
    void start(GameData gameData, World world);
    void stop(GameData gameData, World world);
}
