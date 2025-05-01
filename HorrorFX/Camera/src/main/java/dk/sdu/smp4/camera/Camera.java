package dk.sdu.smp4.camera;

import dk.sdu.smp4.common.Services.GameLoop.ICamera;

public class Camera implements ICamera {

    private double cameraX;
    private double cameraY;
    private final int screenWidth;
    private final int screenHeight;
    private double worldWidth = Double.MAX_VALUE;
    private double worldHeight = Double.MAX_VALUE;

    public Camera(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void updateTarget(double targetX, double targetY) {
        cameraX = targetX - screenWidth / 2.0;
        cameraY = targetY - screenHeight / 2.0;

        cameraX = Math.max(0, Math.min(cameraX, worldWidth - screenWidth));
        cameraY = Math.max(0, Math.min(cameraY, worldHeight - screenHeight));
    }

    @Override
    public double translateX(double worldX) {
        return worldX - cameraX;
    }

    @Override
    public double translateY(double worldY) {
        return worldY - cameraY;
    }

    public void setWorldSize(double width, double height) {
        this.worldWidth = width;
        this.worldHeight = height;
    }
}
