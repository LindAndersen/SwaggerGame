package dk.sdu.smp4.camera;

import dk.sdu.smp4.common.data.GameData;

public class Camera {
    private final double cameraSizeX = 250;
    private final double cameraSizeY = 250;
    private static Camera Instance;

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
}
