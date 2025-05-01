package dk.sdu.smp4.common.enemy.services;

import java.util.List;

public interface IWalkableGrid {
    INode getNode(int x, int y);
    List<INode> getNeighbors(INode node);
}
