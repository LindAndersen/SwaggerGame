package dk.sdu.smp4.Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundService {

    public static void playSound(String path) {
        try {
            URL soundURL = SoundService.class.getResource(path);
            if (soundURL == null) {
                System.err.println("Couldn't find sound file: " + path);
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
