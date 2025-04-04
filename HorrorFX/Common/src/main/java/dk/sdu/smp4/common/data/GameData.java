package dk.sdu.smp4.common.data;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private final VBox questPane = new VBox();
    private final Pane gamePane = new Pane();
//    private final Pane startPane = new Pane();

    public GameData(){
        gamePane.setPrefSize(displayWidth, displayHeight);
//        startPane.setPrefSize(displayWidth, displayHeight);
    }

//    public Pane getStartPane() {
//        Label title = new Label("HorrorFX");
//        Button startButton = new Button("Start");
//
//        startButton.setOnMouseClicked((MouseEvent event)->{
//            Stage stage = (Stage) startPane.getScene().getWindow();
//            Scene gameScene = new Scene(gamePane);
//            stage.setScene(gameScene);
//        });
//
//        Image image = new Image(getClass().getResource("/spider.gif").toExternalForm());
//        ImageView background = new ImageView(image);
//        background.setPreserveRatio(false);
//        background.setSmooth(true);
//        background.setCache(true);
//        background.fitWidthProperty().bind(startPane.widthProperty());
//        background.fitHeightProperty().bind(startPane.heightProperty());
//
//        startPane.getChildren().addAll(background,title,startButton);
//        return startPane;
//    }

    public Pane getGamePane() {
        return gamePane;
    }


    public VBox getQuestPane(){
        return questPane;
    }

    public void setQuestPane(String title, String description){
        questPane.getChildren().clear();

        questPane.setMinSize(400, 400);
        questPane.setLayoutX((double) displayWidth /2-200);
        questPane.setLayoutY((double) displayHeight/2-200);
        questPane.setAlignment(Pos.CENTER);
        questPane.setStyle("-fx-background-color: lightgray;");

        Label labelDescription = new Label(description);
        questPane.getChildren().add(labelDescription);

        Button acceptButton = new Button("Confirm");
        acceptButton.setOnAction(e -> {
            ((Pane) questPane.getParent()).getChildren().remove(questPane);
        });

        questPane.getChildren().add(acceptButton);
        gamePane.getChildren().add(questPane);
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
