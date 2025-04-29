package dk.sdu.smp4.Sound;

import dk.sdu.smp4.common.Services.GameLoop.IPostEntityProcessingService;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public class MusicSystemProvider implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        MusicSystem.getInstance().play();
    }
}
