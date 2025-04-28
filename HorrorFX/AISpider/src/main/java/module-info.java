import dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.enemy.services.EnemyTargetsSPI;
import dk.sdu.smp4.common.events.services.IEventBus;

module AISpider {
    exports dk.sdu.smp4.aispider;
    uses EnemyTargetsSPI;
    uses IEventBus;
    requires Common;
    requires CommonEnemy;
    requires javafx.graphics;
    requires CommonEvents;
    provides IEntityProcessingService with EnemyControlSystem;
    provides IGamePluginService with dk.sdu.smp4.aispider.EnemyPlugin;

}