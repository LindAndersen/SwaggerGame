package dk.sdu.smp4.collisionSystem;

import dk.sdu.smp4.common.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CollisionDetectorTest {

    private CollisionDetector detector;
    private World world;
    private GameData gameData;


    @BeforeEach
    void setup(){
        detector = new CollisionDetector();
        world = new World();
        gameData = new GameData();
    }

    @Test
    void shouldCallCollideWhenSoftEntitiesOverlap(){ //Verify that two overlapping entities collide
        SoftEntity entity1 = mock(SoftEntity.class);
        SoftEntity entity2 = mock(SoftEntity.class);

        when(entity1.getID()).thenReturn("1");
        when(entity2.getID()).thenReturn("2");

        world.addEntity(entity1);
        world.addEntity(entity2);

        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        when(entity1.getPolygonCoordinates()).thenReturn(new double[]{0, 0, 10, 0, 0, 10});

        when(entity2.getX()).thenReturn(5.0);
        when(entity2.getY()).thenReturn(5.0);
        when(entity2.getPolygonCoordinates()).thenReturn(new double[]{0, 0, 10, 0, 0, 10});

        detector.process(gameData, world);

        verify(entity1).collide(world, entity2);
        verify(entity2).collide(world, entity1);
    }

    @Test
    void shouldNotCallCollideWhenSoftEntitiesDoNotOverlap(){ //Verify that two non-overlapping entities don't collide
        SoftEntity entity1 = mock(SoftEntity.class);
        SoftEntity entity2 = mock(SoftEntity.class);

        when(entity1.getID()).thenReturn("1");
        when(entity2.getID()).thenReturn("2");

        world.addEntity(entity1);
        world.addEntity(entity2);

        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        when(entity1.getPolygonCoordinates()).thenReturn(new double[]{0, 0, 10, 0, 0, 10});

        when(entity2.getX()).thenReturn(100.0);
        when(entity2.getY()).thenReturn(100.0);
        when(entity2.getPolygonCoordinates()).thenReturn(new double[]{0, 0, 10, 0, 0, 10});

        detector.process(gameData, world);

        verify(entity1, never()).collide(any(), any());
        verify(entity2, never()).collide(any(), any());
    }

    @Test
    void shouldNotCallCollideForHardEntities(){ //Verify that hard entities do not collide
        HardEntity entity1 = mock(HardEntity.class);
        HardEntity entity2 = mock(HardEntity.class);

        when(entity1.getID()).thenReturn("1");
        when(entity2.getID()).thenReturn("2");

        world.addEntity(entity1);
        world.addEntity(entity2);

        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        when(entity1.getPolygonCoordinates()).thenReturn(new double[]{0, 0, 10, 0, 0, 10});

        when(entity2.getX()).thenReturn(0.0);
        when(entity2.getY()).thenReturn(0.0);
        when(entity2.getPolygonCoordinates()).thenReturn(new double[]{0, 0, 10, 0, 0, 10});

        detector.process(gameData, world);

        verify(entity1, never()).collide(any(), any());
        verify(entity2, never()).collide(any(), any());
    }
}