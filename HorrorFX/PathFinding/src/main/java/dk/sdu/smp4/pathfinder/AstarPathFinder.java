package dk.sdu.smp4.pathfinder;

import dk.sdu.smp4.common.enemy.services.IPathFinder;
import dk.sdu.smp4.common.enemy.services.IWalkableGrid;
import dk.sdu.smp4.common.enemy.services.INode;

import java.util.*;

public class AstarPathFinder implements IPathFinder {

    @Override
    public List<INode> findPath(IWalkableGrid grid, int startX, int startY, int goalX, int goalY) {
        INode startNode = grid.getNode(startX, startY);
        INode goalNode = grid.getNode(goalX, goalY);

        if (startNode == null || goalNode == null || !goalNode.isWalkable()) {
            return null;
        }

        //Already visited nodes
        Set<INode> closedSet = new HashSet<>();
        //Potential nodes. Automatically sorts from lowest to highest f cost
        PriorityQueue<INode> openSet = new PriorityQueue<>(Comparator.comparingDouble(INode::getFCost));
        Map<INode, NodeRecord> nodeRecords = new HashMap<>();

        NodeRecord startRecord = new NodeRecord(startNode, null, 0, heuristic(startNode, goalNode));
        nodeRecords.put(startNode, startRecord);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            INode current = openSet.poll();
            NodeRecord currentRecord = nodeRecords.get(current);

            if (current.equals(goalNode)) {
                return reconstructPath(current, nodeRecords);
            }

            closedSet.add(current);

            for (INode neighbor : grid.getNeighbors(current)) {
                //If already visited or already considered, dont include in possible nodes
                if (!neighbor.isWalkable() || closedSet.contains(neighbor)) continue;

                //Dont get misled, we just use heuristic method because its euclidian distance
                double relativeG = heuristic(neighbor, current);
                double tentativeG = currentRecord.gCost + relativeG;
                NodeRecord neighborRecord = nodeRecords.get(neighbor);

                if (neighborRecord == null || tentativeG < neighborRecord.gCost) {
                    double hCost = heuristic(neighbor, goalNode);
                    neighborRecord = new NodeRecord(neighbor, current, tentativeG, hCost);
                    nodeRecords.put(neighbor, neighborRecord);

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null;
    }

    // Euclidean distance
    private double heuristic(INode a, INode b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private List<INode> reconstructPath(INode endNode, Map<INode, NodeRecord> nodeRecords) {
        List<INode> path = new ArrayList<>();
        NodeRecord current = nodeRecords.get(endNode);

        while (current != null) {
            path.add(current.node);
            current = nodeRecords.get(current.parent);
        }

        Collections.reverse(path);
        return path;
    }

    private static class NodeRecord {
        INode node;
        INode parent;
        double gCost;
        double hCost;

        NodeRecord(INode node, INode parent, double gCost, double hCost) {
            this.node = node;
            this.parent = parent;
            this.gCost = gCost;
            this.hCost = hCost;
        }

        double getFCost() {
            return gCost + hCost;
        }
    }

    public static class BasicNode implements INode {
        private final int x, y;
        private final boolean walkable;
        private double gCost, hCost;
        private INode parent;

        public BasicNode(int x, int y, boolean walkable) {
            this.x = x;
            this.y = y;
            this.walkable = walkable;
        }

        @Override public int getX() { return x; }
        @Override public int getY() { return y; }
        @Override public boolean isWalkable() { return walkable; }
        @Override public double getGCost() { return gCost; }
        @Override public double getHCost() { return hCost; }
        @Override public double getFCost() { return gCost + hCost; }
        @Override public INode getParent() { return parent; }

        @Override public void setGCost(double gCost) { this.gCost = gCost; }
        @Override public void setHCost(double hCost) { this.hCost = hCost; }
        @Override public void setParent(INode parent) { this.parent = parent; }
    }

    public static class WalkableGrid implements IWalkableGrid {
        private final int width, height;
        private final BasicNode[][] nodes;

        public WalkableGrid(boolean[][] walkableMap) {
            this.width = walkableMap.length;
            this.height = walkableMap[0].length;
            this.nodes = new BasicNode[width][height];

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    nodes[x][y] = new BasicNode(x, y, walkableMap[x][y]);
                }
            }
        }

        @Override
        public INode getNode(int x, int y) {
            if (x < 0 || y < 0 || x >= width || y >= height) return null;
            return nodes[x][y];
        }

        @Override
        public List<INode> getNeighbors(INode node) {
            List<INode> neighbors = new ArrayList<>();
            int[][] dirs = {
                    {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                    {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
            };
            for (int[] dir : dirs) {
                int nx = node.getX() + dir[0];
                int ny = node.getY() + dir[1];

                // Prevent diagonal corner cutting
                if (Math.abs(dir[0]) + Math.abs(dir[1]) == 2) { // diagonal
                    INode n1 = getNode(node.getX() + dir[0], node.getY());
                    INode n2 = getNode(node.getX(), node.getY() + dir[1]);
                    if ((n1 == null || !n1.isWalkable()) || (n2 == null || !n2.isWalkable())) {
                        continue;
                    }
                }

                INode neighbor = getNode(nx, ny);
                if (neighbor != null && neighbor.isWalkable()) {
                    neighbors.add(neighbor);
                }
            }
            return neighbors;
        }
    }
}
