import dk.sdu.smp4.aispider.EnemyControlSystem;
import dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.enemy.services.EnemyTargetsSPI;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.map.services.IPathFinder;

module AISpider {
    uses EnemyTargetsSPI;
    uses IEventBus;
    uses IPathFinder;
    requires Common;
    requires CommonEnemy;
    requires CommonEvents;
    requires CommonMap;
    provides IEntityProcessingService with EnemyControlSystem;
    provides IEntityLoaderService with dk.sdu.smp4.aispider.EnemyPlugin;
    exports dk.sdu.smp4.aispider;
}