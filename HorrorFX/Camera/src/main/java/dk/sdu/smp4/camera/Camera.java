package dk.sdu.smp4.camera;

import dk.sdu.smp4.common.data.GameData;

public class Camera {
    private double cameraSizeX;
    private double cameraSizeY;
    private static Camera Instance;
    private double zoomX, zoomY;
    private double offsetX, offsetY;

    public static Camera getInstance(GameData gameData)
    {
        if (Instance == null) {
            Instance = new Camera(385, gameData.getHeightToWidthRatio());
        }
        return Instance;
    }

    private Camera(double x, double ratio) {
        cameraSizeX = x;
        cameraSizeY = x * ratio;
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
