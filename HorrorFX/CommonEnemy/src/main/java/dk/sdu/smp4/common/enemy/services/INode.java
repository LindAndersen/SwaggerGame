package dk.sdu.smp4.common.enemy.services;

public interface INode {
    int getX();
    int getY();
    boolean isWalkable();

    void setParent(INode parent);
    void setGCost(double gCost);
    void setHCost(double gCost);

    double getGCost();
    double getHCost();
    double getFCost();
    INode getParent();
}
