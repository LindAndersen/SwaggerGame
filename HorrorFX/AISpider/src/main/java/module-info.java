import dk.sdu.smp4.aispider.EnemyControlSystem;
import dk.sdu.smp4.common.Services.IEntityProcessingService;
import dk.sdu.smp4.common.Services.IGamePluginService;

module AISpider {
    requires Common;
    requires Player;
    provides IEntityProcessingService with EnemyControlSystem;
    provides IGamePluginService with dk.sdu.smp4.aispider.EnemyPlugin;

}