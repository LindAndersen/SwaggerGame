package dk.sdu.smp4.map.services;

import dk.sdu.smp4.common.data.World;

public interface IMapGenerator {
    void generate(World world);
    void removeFromMap(World world, double x, double y);
}
