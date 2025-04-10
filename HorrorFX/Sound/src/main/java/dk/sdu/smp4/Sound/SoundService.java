package dk.sdu.smp4.Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoundService {

    private static final Map<String, Clip> clipMap = new HashMap<>();

    public static void playSound(String path) {
        try {
            Clip clip = clipMap.get(path);

            if (clip == null) {
                URL soundURL = SoundService.class.getResource(path);
                if (soundURL == null) {
                    System.err.println("Couldn't find sound file: " + path);
                    return;
                }

                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
                clip = AudioSystem.getClip();
                clip.open(audioIn);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f); // decrease volume (0.0f is default volume, 80.0f is no sound)

                clipMap.put(path, clip);
            }

            if (!clip.isRunning()) {
                clip.setFramePosition(0); // rewind to the beginning
                clip.start();
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public static void stopSound(String path) {
        Clip clip = clipMap.get(path);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public static boolean isPlaying(String path) {
        Clip clip = clipMap.get(path);
        return clip != null && clip.isRunning();
    }
}
