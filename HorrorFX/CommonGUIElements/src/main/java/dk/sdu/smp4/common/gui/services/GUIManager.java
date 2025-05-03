package dk.sdu.smp4.common.gui.services;

import dk.sdu.smp4.common.Services.GUI.*;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.GameKeys;
import dk.sdu.smp4.common.events.data.GameOverEvent;
import dk.sdu.smp4.common.events.services.IEventBus;
import dk.sdu.smp4.common.gui.elements.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class GUIManager {
    private final Runnable startGameCallback;
    private final Runnable resetGameCallback;
    private final GameData gameData;
    private final Stage stage;
    private final Pane backgroundLayer = new Pane();
    private final Pane polygonLayer = new Pane();
    private final Pane textLayer = new Pane();
    private final Pane lightLayer = new Pane();
    private StackPane root = new StackPane();
    private final IHealthBar healthBar = new HealthBar();
    private final IInventoryHUD inventoryHUD = new InventoryHUD();
    private final IFlashlightBar flashlightBar = new FlashlightBar();

    public GUIManager(GameData gameData, Stage stage, Runnable startGameCallback, Runnable resetGameCallback) {
        this.gameData = gameData;
        this.stage = stage;
        this.startGameCallback = startGameCallback;
        this.resetGameCallback = resetGameCallback;
        setupFonts();
        start();
        handleInputs();
        setupEventHandling();

        stage.show();
    }

    private void start()
    {
        Scene scene = new Scene(root, gameData.getDisplayWidth(), gameData.getDisplayHeight());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("HorrorFX");

        StartMenu startMenu = new StartMenu(
                this::setupMainGame,
                stage::close
        );
        root.getChildren().add(startMenu);
    }

    private void setupFonts() {
        Font.loadFont(Objects.requireNonNull(getClass().getResource("/fonts/was.ttf")).toExternalForm(), 10);
        Font.loadFont(Objects.requireNonNull(getClass().getResource("/fonts/SpecialElite-Regular.ttf")).toExternalForm(), 10);
    }
    private void setupMainGame() {
        root.getChildren().clear();
        root.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        root.prefWidthProperty().bind(stage.getScene().widthProperty());
        root.prefHeightProperty().bind(stage.getScene().heightProperty());
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

        Image backgroundActualImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/main_background.png")));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(
                backgroundActualImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );

        Region inventoryNode = (Region) inventoryHUD;
        Region flashlightBarNode = (Region) flashlightBar;

        backgroundLayer.setBackground(new Background(backgroundImage));

        inventoryNode.layoutXProperty().bind(root.widthProperty().subtract(inventoryNode.widthProperty()).divide(2));
        inventoryNode.setLayoutY(gameData.getDisplayHeight() - 70);

        // Position flashlightBarNode 20px from the top and right
        flashlightBarNode.layoutXProperty().bind(
                root.widthProperty().subtract(flashlightBarNode.widthProperty()).subtract(20)
        );
        flashlightBarNode.setLayoutY(20); // 20px from top

        textLayer.getChildren().addAll(inventoryNode, (Node)healthBar, flashlightBarNode);

        startGameCallback.run();
    }

    public void handleInputs()
    {
        stage.getScene().setOnMouseMoved(event -> {
            gameData.getKeys().setMouseMoved(true);
            GameKeys.setMousePosition(event.getSceneX(), event.getSceneY());
        });

        stage.getScene().setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.ESCAPE) {
                setPausedBox();
            } else {
                setKey(code, true);
            }
        });

        stage.getScene().setOnKeyReleased(event -> setKey(event.getCode(), false));
    }

    private void setupEventHandling()
    {
        IEventBus eventBus = getEventBusSPI().stream().findFirst().orElse(null);
        assert eventBus != null;

        eventBus.subscribe(GameOverEvent.class, event -> showGameOverScreen());
    }

    private void setKey(KeyCode code, boolean pressed) {
        if (code == KeyCode.A) gameData.getKeys().setKey(GameKeys.LEFT, pressed);
        if (code == KeyCode.D) gameData.getKeys().setKey(GameKeys.RIGHT, pressed);
        if (code == KeyCode.W) gameData.getKeys().setKey(GameKeys.UP, pressed);
        if (code == KeyCode.SPACE) gameData.getKeys().setKey(GameKeys.SPACE, pressed);
        if (code == KeyCode.S) gameData.getKeys().setKey(GameKeys.DOWN, pressed);
        if (code == KeyCode.E) gameData.getKeys().setKey(GameKeys.INTERACT, pressed);
        if (code == KeyCode.R) gameData.getKeys().setKey(GameKeys.RELOAD, pressed);
    }

    void showGameOverScreen() {
        VBox gameOverBox = new VBox();
        gameOverBox.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameOverBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.85);");
        gameOverBox.setAlignment(Pos.CENTER);
        gameOverBox.setSpacing(20);

        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.getStyleClass().add("title-label");

        Button goAgainButton = new Button("Go Again");
        goAgainButton.getStyleClass().add("menu-button");
        goAgainButton.setOnAction(e -> {
            getTextLayer().getChildren().remove(gameOverBox);
            resetGameCallback.run();
        });

        Button quitButton = new Button("Quit");
        quitButton.getStyleClass().add("menu-button");
        quitButton.setOnAction(e -> System.exit(0));

        gameOverBox.getChildren().addAll(gameOverLabel, goAgainButton, quitButton);
        Platform.runLater(() -> getTextLayer().getChildren().add(gameOverBox));
        gameData.setPaused(true);

        gameOverBox.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());
    }

    public void setQuestPane(String title, String description) {
        gameData.setPaused(true);

        final QuestPopup[] popupRef = new QuestPopup[1];
        popupRef[0] = new QuestPopup(title, description, () -> {
            textLayer.getChildren().remove(popupRef[0]);
            gameData.setPaused(false);
        });

        QuestPopup popup = popupRef[0];
        centerPopup(popup);
        textLayer.getChildren().add(popup);
    }

    public void setPausedBox() {
        if (gameData.isPaused()) return;

        final PausePopup[] popupRef = new PausePopup[1];
        popupRef[0] = new PausePopup(
                () -> {
                    textLayer.getChildren().remove(popupRef[0]);
                    gameData.setPaused(false);
                },
                () -> {
                    textLayer.getChildren().remove(popupRef[0]);
                    stage.close();
                }
        );

        PausePopup popup = popupRef[0];
        centerPopup(popup);
        textLayer.getChildren().add(popup);
        gameData.setPaused(true);
    }

    private void centerPopup(Region popup) {
        popup.setLayoutX(gameData.getDisplayWidth() / 2.0 - popup.getMinWidth() / 2);
        popup.setLayoutY(gameData.getDisplayHeight() / 2.0 - popup.getMinHeight() / 2);
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

    public IHealthBar getHealthBar() {
        return healthBar;
    }

    public IInventoryHUD getInventoryHUD() {
        return inventoryHUD;
    }

    public IFlashlightBar getFlashlightBar()
    {
        return flashlightBar;
    }

    private Collection<? extends IEventBus> getEventBusSPI() {
        return ServiceLoader.load(IEventBus.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

}
