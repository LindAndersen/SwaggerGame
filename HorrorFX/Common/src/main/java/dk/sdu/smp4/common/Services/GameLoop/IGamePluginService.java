package dk.sdu.smp4.common.Services.GameLoop;

import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface IGamePluginService {
    void start(GameData gameData, World world);
    void stop(GameData gameData, World world);
}
