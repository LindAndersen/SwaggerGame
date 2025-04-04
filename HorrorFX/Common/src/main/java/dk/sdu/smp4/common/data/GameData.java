package dk.sdu.smp4.common.data;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private final VBox questPane = new VBox();
    private final Pane gameWindow = new Pane();



    public Pane getGameWindow() {
        return gameWindow;
    }


    public VBox getQuestPane(){
        return questPane;
    }

    public void setQuestPane(String title, String description){
        questPane.getChildren().clear();

        questPane.setMinSize(400, 400);
        questPane.setLayoutX((double) displayWidth /2);
        questPane.setLayoutY((double) displayHeight/2);
        questPane.setAlignment(Pos.CENTER);

        Label labelDescription = new Label(description);
        questPane.getChildren().add(labelDescription);

        Button acceptButton = new Button("Confirm");
        acceptButton.setOnAction(e -> {
            ((Pane) questPane.getParent()).getChildren().remove(questPane);
        });

        questPane.getChildren().add(acceptButton);
        gameWindow.getChildren().add(questPane);
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

}
