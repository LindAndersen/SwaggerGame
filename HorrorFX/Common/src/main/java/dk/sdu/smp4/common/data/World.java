package dk.sdu.smp4.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private int tileSize = 40;
    private int[][] map;
    

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int[][] getMap() {
        return map;
    }

    public int getTileSize(){
        return tileSize;
    }

    public boolean[][] getWalkableTiles(){
        boolean[][] tiles = new boolean[map.length][];

        for (int i = 0; i < map.length; i++) {
            tiles[i] = new boolean[map[i].length];

            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = map[i][j] == 0;
            }
        }
        return tiles;
    }
    
    
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public List<Entity> getEntities(Class<?>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<?> entityType : entityTypes) {
                if (entityType.isAssignableFrom(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

}