package dk.sdu.loadersystem;

import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.map.services.IMapGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MapGenerator implements IMapGenerator {

    private int[][] loadMapFromCSV(String absolutePath) {
        List<int[]> rows = new ArrayList<>();

        try (InputStream is = MapGenerator.class.getResourceAsStream(absolutePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] toks = line.split(",");
                int[] row = new int[toks.length];
                for (int i = 0; i < toks.length; i++) {
                    row[i] = Integer.parseInt(toks[i].trim());
                }
                rows.add(row);
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Failed to load CSV " + absolutePath, e);
        }

        if (rows.isEmpty()) {
            return new int[0][0];
        }

        int rowCount = rows.size();
        int colCount = rows.get(0).length;
        int[][] map = new int[colCount][rowCount];          // transpose dimensions

        for (int r = 0; r < rowCount; r++) {
            int[] srcRow = rows.get(r);
            for (int c = 0; c < colCount; c++) {
                map[c][r] = srcRow[c];                      // transpose
            }
        }
        return map;
    }

    @Override
    public void generate(World world) {
        int[][] map = loadMapFromCSV("/maps/map2.csv");
        world.setMap(map);

        world.setMapWidth(map.length*world.getTileSize());
        world.setMapHeight(map[0].length*world.getTileSize());

        Map<Integer, IEntityLoaderService> IDToPluginMap = getPluginMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0 | IDToPluginMap.get(map[i][j]) == null){
                    continue;
                }
                IDToPluginMap.get(map[i][j]).render(world, i, j, map[i][j]);
            }

        }
    }

    public void removeFromMap(World world, double x, double y)
    {
        int tileSize = world.getTileSize();

        int xTile = (int)(x- tileSize/2)/tileSize;
        int yTile = (int)(y- tileSize/2)/tileSize;

        world.getMap()[xTile][yTile] = 0;
    }


    private Collection<? extends IEntityLoaderService> getPlugins() {
        return ServiceLoader.load(IEntityLoaderService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Map<Integer, IEntityLoaderService> getPluginMap() {
        Map<Integer, IEntityLoaderService> IDToPluginMap = new HashMap<>();
        for (IEntityLoaderService plugin : getPlugins()){
            for (Integer key : plugin.getMapCodes()) {
                IDToPluginMap.put(key, plugin);
            }
        }
        return IDToPluginMap;
    }

}
