package dk.sdu.smp4.player;

import dk.sdu.smp4.common.Services.GUI.EntityImage;

public class Animation {
    private EntityImage[] frames;
    private int currentFrame = 0;
    private double frameDuration; // in seconds
    private double elapsedTime = 0;

    public Animation(EntityImage[] frames, double frameDuration) {
        this.frames = frames;
        this.frameDuration = frameDuration;
    }

    public void update(double deltaTime) {
        elapsedTime += deltaTime;
        if (elapsedTime >= frameDuration) {
            currentFrame = (currentFrame + 1) % frames.length;
            elapsedTime = 0;
        }
    }

    public EntityImage getCurrentFrame() {
        return frames[currentFrame];
    }

    public void reset() {
        currentFrame = 0;
        elapsedTime = 0;
    }
}
