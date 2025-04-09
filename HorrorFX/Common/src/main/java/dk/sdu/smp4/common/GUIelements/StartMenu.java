package dk.sdu.smp4.common.GUIelements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class StartMenu extends StackPane {

    public StartMenu(Runnable onStart, Runnable onQuit) {
        setPrefSize(800, 800); // or dynamic sizing
        getStyleClass().add("start-pane");

        Label title = new Label("HorrorFX");
        title.getStyleClass().add("title-label");

        GameButton startButton = new GameButton("Start");
        startButton.setOnMouseClicked(e -> onStart.run());

        GameButton quitButton = new GameButton("Quit");
        quitButton.setOnMouseClicked(e -> onQuit.run());

        VBox buttons = new VBox(20, startButton, quitButton);
        buttons.setMaxWidth(225);
        buttons.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setTop(title);
        layout.setCenter(buttons);
        BorderPane.setAlignment(title, Pos.TOP_CENTER);

        Image image = new Image(getClass().getResource("/images/dungeon.gif").toExternalForm());
        ImageView background = new ImageView(image);
        background.setPreserveRatio(false);
        background.setSmooth(true);
        background.setCache(true);
        background.fitWidthProperty().bind(widthProperty());
        background.fitHeightProperty().bind(heightProperty());

        getChildren().addAll(background, layout);
    }
}
