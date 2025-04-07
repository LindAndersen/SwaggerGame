package dk.sdu.smp4.common.data;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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

    private final VBox questPane = new VBox();
    private final VBox pausedBox = new VBox();

    private boolean isPaused;

    public GameData(){
        //backgroundLayer.setStyle("-fx-background-color: rgba(180, 140, 20, 0.1);");
        root.setAlignment(Pos.TOP_LEFT); // <- this is the key
        root.getChildren().addAll(backgroundLayer, polygonLayer, lightLayer, textLayer);

        for (Pane layer : List.of(backgroundLayer, polygonLayer, lightLayer, textLayer)) {
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
        textLayer.setMouseTransparent(false);
        //Image backgroundActualImage = new Image(getClass().getResourceAsStream("/background.jpg"));
        Image backgroundActualImage = new Image(getClass().getResourceAsStream("/from_chat.png"));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(
                backgroundActualImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );

        Background background = new Background(backgroundImage);
        backgroundLayer.setBackground(background);
    }

    public VBox getQuestPane(){
        return questPane;
    }

    public void setQuestPane(String title, String description){
        setPaused(true);
        questPane.getChildren().clear();

        questPane.setMinSize(400, 400);
        questPane.setLayoutX((double) displayWidth /2);
        questPane.setLayoutY((double) displayHeight/2);
        questPane.setAlignment(Pos.CENTER);
        //questPane.setStyle("-fx-background-color: lightgray;");

        Label labelDescription = new Label(description);
        questPane.getChildren().add(labelDescription);

        Button acceptButton = new Button("Confirm");
        acceptButton.setOnAction(e -> {
            ((Pane) questPane.getParent()).getChildren().remove(questPane);
            setPaused(false);
        });

        questPane.getChildren().add(acceptButton);
        textLayer.getChildren().add(questPane);
    }

    public void setPausedBox(/*Stage stage*/) {
        if (isPaused){
            textLayer.getChildren().remove(pausedBox);
        } else {
            pausedBox.getChildren().clear();

            pausedBox.setMinSize(400, 400);
            pausedBox.setLayoutX((double) displayWidth /2-200);
            pausedBox.setLayoutY((double) displayHeight/2-200);
            pausedBox.setAlignment(Pos.CENTER);
            pausedBox.setStyle("-fx-background-color: lightgray;");

            Label labelDescription = new Label("Paused");
            pausedBox.getChildren().add(labelDescription);

            Button continueButton = new Button("Continue");
            continueButton.setOnAction(e -> {
                ((Pane) pausedBox.getParent()).getChildren().remove(pausedBox);
                setPaused(false);
            });
            Button quitButton = new Button("Quit to title");
            quitButton.setOnAction(e -> {
                ((Pane) pausedBox.getParent()).getChildren().remove(pausedBox);
                //stage.close();
            });

            pausedBox.getChildren().addAll(continueButton, quitButton);
            textLayer.getChildren().add(pausedBox);
        }
        setPaused(!isPaused);
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

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
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
