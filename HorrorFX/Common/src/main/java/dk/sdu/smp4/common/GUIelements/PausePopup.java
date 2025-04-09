package dk.sdu.smp4.common.GUIelements;

public class PausePopup extends GamePopup {

    public PausePopup(Runnable onContinue, Runnable onQuit) {
        super();

        GameLabel pausedLabel = new GameLabel("Paused", GameLabel.Type.TITLE);

        GameButton continueButton = new GameButton("Continue");
        continueButton.setOnAction(e -> onContinue.run());

        GameButton quitButton = new GameButton("Quit to Title");
        quitButton.setOnAction(e -> onQuit.run());

        getChildren().addAll(pausedLabel, continueButton, quitButton);
    }
}
