package dk.sdu.smp4.Sound;

import dk.sdu.smp4.common.Services.IGamePluginService;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class BackgroundMusicSystem implements IGamePluginService {

    private Clip backgroundClip;

    @Override
    public void start(GameData gameData, World world) {
        try {
            URL musicURL = getClass().getResource("/background/test.wav");
            if (musicURL == null) {
                System.err.println("Background music not found");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicURL);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioIn);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY); // Looper musikken
            backgroundClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
        }
    }
}
