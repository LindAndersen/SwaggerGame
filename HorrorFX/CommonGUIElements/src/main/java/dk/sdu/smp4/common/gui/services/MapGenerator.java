package dk.sdu.smp4.common.gui.services;

import dk.sdu.smp4.common.Services.GameLoop.IStructurePluginService;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class MapGenerator {
    private int[][] map;
    private GameData gameData;
    private World world;


    public MapGenerator(GameData gameData, World world){
        this.gameData = gameData;
        this.world = world;
    }

    public void generate() {
        map = generateMap();
        try {
            drawMap();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private int[][] generateMap(){
        map = new int[200][200];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Math.random() < 0.3 ? 1 : 0;
            }
        }
        return map;
    }

    private void drawMap() throws InterruptedException {
        getStructures().forEach(entity -> {
            entity.start(gameData, world);
        });
    }

    private Collection<? extends IStructurePluginService> getStructures(){
        return ServiceLoader.load(IStructurePluginService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

}
