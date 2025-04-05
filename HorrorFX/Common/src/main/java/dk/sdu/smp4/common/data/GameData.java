package dk.sdu.smp4.common.data;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();

    private final VBox questBox = new VBox();
    private final VBox pausedBox = new VBox();

    private final Pane gamePane = new Pane();
    private boolean isPaused;


    public GameData(){
        gamePane.setPrefSize(displayWidth, displayHeight);
    }


    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public void setQuestBox(String title, String description){
        setPaused(true);
        questBox.getChildren().clear();

        questBox.setMinSize(400, 400);
        questBox.setLayoutX((double) displayWidth /2-200);
        questBox.setLayoutY((double) displayHeight/2-200);
        questBox.setAlignment(Pos.CENTER);
        questBox.setStyle("-fx-background-color: lightgray;");

        Label labelDescription = new Label(description);
        questBox.getChildren().add(labelDescription);

        Button acceptButton = new Button("Confirm");
        acceptButton.setOnAction(e -> {
            ((Pane) questBox.getParent()).getChildren().remove(questBox);
            setPaused(false);
        });

        questBox.getChildren().add(acceptButton);
        gamePane.getChildren().add(questBox);
    }

    public void setPausedBox(Stage stage) {
        if (isPaused){
            gamePane.getChildren().remove(pausedBox);
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
                stage.close();
                setPaused(false);
            });

            pausedBox.getChildren().addAll(continueButton, quitButton);
            gamePane.getChildren().add(pausedBox);
        }
        setPaused(!isPaused);
    }

    public GameKeys getKeys() {
        return keys;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

}
