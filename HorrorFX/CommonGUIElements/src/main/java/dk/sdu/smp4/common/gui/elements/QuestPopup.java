package dk.sdu.smp4.common.gui.elements;

public class QuestPopup extends GamePopup {

    public QuestPopup(String title, String description, Runnable onConfirm) {
        super();

        GameLabel titleLabel = new GameLabel(title, GameLabel.Type.TITLE);
        GameLabel descriptionLabel = new GameLabel(description, GameLabel.Type.DESCRIPTION);
        GameButton confirmButton = new GameButton("Confirm");

        confirmButton.setOnAction(e -> onConfirm.run());

        getChildren().addAll(titleLabel, descriptionLabel, confirmButton);
    }
}
