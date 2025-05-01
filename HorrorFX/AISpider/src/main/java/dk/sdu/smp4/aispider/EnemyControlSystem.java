package dk.sdu.smp4.aispider;

import dk.sdu.smp4.common.Services.GameLoop.IEntityProcessingService;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.events.data.PlayerPositionEvent;
import dk.sdu.smp4.common.events.data.SpiderPositionEvent;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.map.services.INode;
import dk.sdu.smp4.map.services.IPathFinder;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class EnemyControlSystem implements IEntityProcessingService {

    private double lastKnownPlayerX = -1;
    private double lastKnownPlayerY = -1;
    private final IEventBus eventBus;
    private final IPathFinder pathFinder;


    public EnemyControlSystem() {
        eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        pathFinder = getPathFinderSPI().stream().findFirst().orElse(null);
        assert eventBus != null;
        assert pathFinder != null;

        eventBus.subscribe(PlayerPositionEvent.class, event -> {
            lastKnownPlayerX = event.getX();
            lastKnownPlayerY = event.getY();
        });
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(AISpider.class)) {
            AISpider AISpider = (AISpider) entity;
            if (AISpider.isInCooldown()) continue;
            AISpider.setPreviousX(AISpider.getX());
            AISpider.setPreviousY(AISpider.getY());

            int tileSize = world.getTileSize();

            int spiderTileX = (int) (AISpider.getX() / tileSize);
            int spiderTileY = (int) (AISpider.getY() / tileSize);
            int playerTileX = (int) (lastKnownPlayerX / tileSize);
            int playerTileY = (int) (lastKnownPlayerY / tileSize);

            System.out.println();
            List<INode> path = pathFinder.findPath(world, spiderTileX, spiderTileY, playerTileX, playerTileY);

            if (path != null && path.size() > 1) {
                INode next = path.get(1);

                // Move to the middle of the tile
                double nextWorldX = next.getX() * tileSize + tileSize / 2.0;
                double nextWorldY = next.getY() * tileSize + tileSize / 2.0;

                double dx = nextWorldX - AISpider.getX();
                double dy = nextWorldY - AISpider.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);

                double threshold = tileSize / 1.5;

                if (distance < threshold && path.size() <= 2) {
                    dx = lastKnownPlayerX - AISpider.getX();
                    dy = lastKnownPlayerY - AISpider.getY();
                    distance = Math.sqrt(dx * dx + dy * dy);
                }

                if (distance > 1.0) {
                    double speed = 0.8;
                    AISpider.setRotation(Math.toDegrees(Math.atan2(dy, dx)));
                    AISpider.setX(AISpider.getX() + (dx / distance) * speed);
                    AISpider.setY(AISpider.getY() + (dy / distance) * speed);
                    handleImage(AISpider, dx);
                }


                eventBus.post(new SpiderPositionEvent(entity, entity.getX(), entity.getY()));
            }
        }
    }

    private void handleImage(AISpider AISpider, double dx)
    {
        if(dx < 0){
            AISpider.setMoveLeft();
        } else {
            AISpider.setMoveRight();
        }
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends IPathFinder> getPathFinderSPI() {
        return ServiceLoader.load(IPathFinder.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
