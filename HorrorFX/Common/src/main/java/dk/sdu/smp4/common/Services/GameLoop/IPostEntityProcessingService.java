package dk.sdu.smp4.common.Services.GameLoop;

import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
