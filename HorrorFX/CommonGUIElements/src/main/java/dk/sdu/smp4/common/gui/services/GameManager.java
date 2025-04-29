package dk.sdu.smp4.common.gui.services;

import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import javafx.stage.Stage;

public class GameManager {
    private World world;
    private GameData gameData;
    private GUIManager guiManager;
    private Renderer renderer;
    private Stage stage;

    public GameManager() {}

    public void init(Stage stage) {
        this.stage = stage;
        this.world = new World();
        this.gameData = new GameData();
        this.guiManager = new GUIManager(gameData, stage, this::startGame, this::resetGame);
        this.renderer = new Renderer(world, gameData, guiManager);
        GUIManagerProvider.setInstance(guiManager);
    }

    private void startGame() {
        renderer.start();
    }

    private void resetGame()
    {
        init(stage);
    }

    public World getWorld() { return world; }
    public GameData getGameData() { return gameData; }
    public GUIManager getGuiManager() { return guiManager; }
    public Renderer getRenderer() { return renderer; }
}
