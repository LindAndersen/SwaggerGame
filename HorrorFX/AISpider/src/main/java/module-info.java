import dk.sdu.smp4.aispider.EnemyControlSystem;
import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.enemy.services.EnemyTargetsSPI;

module AISpider {
    exports dk.sdu.smp4.aispider;
    uses EnemyTargetsSPI;
    requires Common;
    requires CommonEnemy;
    requires javafx.graphics;
    provides IEntityProcessingService with EnemyControlSystem;
    provides IGamePluginService with dk.sdu.smp4.aispider.EnemyPlugin;

}