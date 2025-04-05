package dk.sdu.smp4.common.data;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private final Pane backgroundLayer = new Pane();
    private final Pane polygonLayer = new Pane();
    private final Pane textLayer = new Pane();
    private final Pane lightLayer = new Pane();
    private final StackPane root = new StackPane(backgroundLayer, polygonLayer, textLayer, lightLayer);

    public GameData(){
        backgroundLayer.setStyle("-fx-background-color: rgba(245, 245, 245, 0.2);");
    }

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public StackPane getRoot() {
        return root;
    }

    public Pane getBackgroundLayer() {
        return backgroundLayer;
    }

    public Pane getPolygonLayer() {
        return polygonLayer;
    }

    public Pane getTextLayer() {
        return textLayer;
    }

    public Pane getLightLayer() {
        return lightLayer;
    }
}
