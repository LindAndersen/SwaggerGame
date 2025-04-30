package dk.sdu.smp4.common.Services.GameLoop;

import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public interface IStructurePluginService {
    int getMapCode();
    void render(World world, int x, int y);
}
