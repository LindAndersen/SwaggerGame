package dk.sdu.smp4.camera;

import dk.sdu.smp4.common.data.GameData;

public class Camera {
    private final double cameraSizeX = 445;
    private final double cameraSizeY = 250;
    private static Camera Instance;
    private double zoomX, zoomY;
    private double offsetX, offsetY;

    public static Camera getInstance()
    {
        if (Instance == null) {
            Instance = new Camera();
        }
        return Instance;
    }

    private Camera() {
    }

    public double getCameraSizeX() {
        return cameraSizeX;
    }

    public double getCameraSizeY() {
        return cameraSizeY;
    }

    public double getZoomX() {
        return zoomX;
    }

    public void setZoomX(double zoomX) {
        this.zoomX = zoomX;
    }

    public double getZoomY() {
        return zoomY;
    }

    public void setZoomY(double zoomY) {
        this.zoomY = zoomY;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }
}
