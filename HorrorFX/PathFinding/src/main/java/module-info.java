import dk.sdu.smp4.map.services.IPathFinder;
import dk.sdu.smp4.pathfinder.AstarPathFinder;

module PathFinding {
    requires Common;
    requires CommonMap;

    provides IPathFinder with AstarPathFinder;
    exports dk.sdu.smp4.pathfinder;
}