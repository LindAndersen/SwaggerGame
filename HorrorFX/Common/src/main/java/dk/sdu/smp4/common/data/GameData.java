package dk.sdu.smp4.common.data;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;

import java.util.List;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private final Pane backgroundLayer = new Pane();
    private final Pane polygonLayer = new Pane();
    private final Pane textLayer = new Pane();
    private final Pane lightLayer = new Pane();
    private final StackPane root = new StackPane();

    public GameData(){
        backgroundLayer.setStyle("-fx-background-color: rgba(180, 140, 20, 0.1);");
        root.setAlignment(Pos.TOP_LEFT); // <- this is the key
        root.getChildren().addAll(backgroundLayer, polygonLayer, lightLayer, textLayer);

        for (Pane layer : List.of(backgroundLayer, polygonLayer, textLayer, lightLayer)) {
            layer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layer.prefWidthProperty().bind(root.widthProperty());
            layer.prefHeightProperty().bind(root.heightProperty());
            layer.setMouseTransparent(true);

            // TO BE VERY SAFE (fml) guarantee no offset
            layer.setLayoutX(0);
            layer.setLayoutY(0);
            layer.setTranslateX(0);
            layer.setTranslateY(0);
        }

        backgroundLayer.setMouseTransparent(false);
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
