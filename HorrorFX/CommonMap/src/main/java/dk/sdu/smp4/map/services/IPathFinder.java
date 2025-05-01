package dk.sdu.smp4.map.services;

import dk.sdu.smp4.common.data.World;

import java.util.List;

public interface IPathFinder {
    List<INode> findPath(World world, int startX, int startY, int goalX, int goalY);
}
