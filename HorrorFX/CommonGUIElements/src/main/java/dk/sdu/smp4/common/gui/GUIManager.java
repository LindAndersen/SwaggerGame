package dk.sdu.smp4.common.gui;

import dk.sdu.smp4.common.data.GameData;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class GUIManager {
    private final GameData gameData;
    private final Pane backgroundLayer = new Pane();
    private final Pane polygonLayer = new Pane();
    private final Pane textLayer = new Pane();
    private final Pane lightLayer = new Pane();
    private final StackPane root = new StackPane();
    private final HealthBar healthBar = new HealthBar();
    private final InventoryHUD inventoryHUD = new InventoryHUD();

    public GUIManager(final GameData gameData) {
        this.gameData = gameData;
        setupUI();
    }

    private void setupUI() {
        root.setAlignment(Pos.TOP_LEFT);
        root.getChildren().addAll(backgroundLayer, polygonLayer, lightLayer, textLayer);

        for (Pane layer : List.of(backgroundLayer, polygonLayer, lightLayer, textLayer)) {
            layer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            layer.prefWidthProperty().bind(root.widthProperty());
            layer.prefHeightProperty().bind(root.heightProperty());
            layer.setMouseTransparent(true);
            layer.setLayoutX(0);
            layer.setLayoutY(0);
            layer.setTranslateX(0);
            layer.setTranslateY(0);
        }

        backgroundLayer.setMouseTransparent(false);
        textLayer.setMouseTransparent(false);

        Image backgroundActualImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/from_chat.png")));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(
                backgroundActualImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );

        backgroundLayer.setBackground(new Background(backgroundImage));
        textLayer.getChildren().add(healthBar);
        inventoryHUD.layoutXProperty().bind(root.widthProperty().subtract(inventoryHUD.widthProperty()).divide(2));
        inventoryHUD.setLayoutY(gameData.getDisplayHeight() - 70);
        textLayer.getChildren().add(inventoryHUD);
    }

    public void setQuestPane(String title, String description) {
        setPaused(true);

        final QuestPopup[] popupRef = new QuestPopup[1];
        popupRef[0] = new QuestPopup(title, description, () -> {
            textLayer.getChildren().remove(popupRef[0]);
            setPaused(false);
        });

        QuestPopup popup = popupRef[0];
        centerPopup(popup);
        textLayer.getChildren().add(popup);
    }

    public void setPausedBox(Stage stage) {
        if (isPaused) return;

        final PausePopup[] popupRef = new PausePopup[1];
        popupRef[0] = new PausePopup(
                () -> {
                    textLayer.getChildren().remove(popupRef[0]);
                    setPaused(false);
                },
                () -> {
                    textLayer.getChildren().remove(popupRef[0]);
                    stage.close();
                }
        );

        PausePopup popup = popupRef[0];
        centerPopup(popup);
        textLayer.getChildren().add(popup);
        setPaused(true);
    }

    private void centerPopup(Region popup) {
        popup.setLayoutX(gameData.getDisplayWidth() / 2.0 - popup.getMinWidth() / 2);
        popup.setLayoutY(gameData.getDisplayHeight() / 2.0 - popup.getMinHeight() / 2);
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

    public InventoryHUD getInventoryHUD() {
        return inventoryHUD;
    }

}
