package dk.sdu.smp4.common.enemy.services;

import java.util.List;

public interface IPathFinder {
    List<INode> findPath(IWalkableGrid grid, int startX, int startY, int goalX, int goalY);
}
