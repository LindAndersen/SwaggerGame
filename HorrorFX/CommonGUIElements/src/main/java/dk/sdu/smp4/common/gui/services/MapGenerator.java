package dk.sdu.smp4.common.gui.services;

import dk.sdu.smp4.common.Services.GameLoop.IEntityLoaderService;
import dk.sdu.smp4.common.data.World;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class MapGenerator {
    private World world;

    public MapGenerator(World world){
        this.world = world;
    }

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


    public void generate() {
        int[][] map = loadMapFromCSV("/maps/map.csv");
        world.setMap(map);

        Map<Integer, IEntityLoaderService> structurePluginMap = getPlugins();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0 | structurePluginMap.get(map[i][j]) == null){
                    continue;
                }
                structurePluginMap.get(map[i][j]).render(world, i, j);
            }

        }

    }

    private Map<Integer, IEntityLoaderService> getPlugins() {
        return ServiceLoader.load(IEntityLoaderService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toMap(
                        IEntityLoaderService::getMapCode,
                        plugin -> plugin
                ));
    }

}
