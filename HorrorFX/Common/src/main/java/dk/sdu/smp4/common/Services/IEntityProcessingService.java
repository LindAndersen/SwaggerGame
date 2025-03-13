package dk.sdu.smp4.common.Services;

import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface IEntityProcessingService {
    void process(GameData gameData, World world);

}
