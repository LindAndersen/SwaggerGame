package dk.sdu.smp4.common.Services.GameLoop;

import dk.sdu.smp4.common.data.World;

import java.util.Set;

public interface IEntityLoaderService {
    void render(World world, int x, int y, int mapCode);
    Set<Integer> getMapCodes();
    void stop(World world);
}
