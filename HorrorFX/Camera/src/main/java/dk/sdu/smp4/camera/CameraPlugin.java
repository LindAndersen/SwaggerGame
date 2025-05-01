package dk.sdu.smp4.camera;

import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.Services.GameLoop.ICamera;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;

public class CameraPlugin implements IGamePluginService, ICamera {

    private Camera camera;

    @Override
    public void start(GameData gameData, World world) {
        camera = new Camera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        camera.setWorldSize(3000, 3000);
    }

    @Override
    public void stop(GameData gameData, World world) {
        camera = null;
    }

    @Override
    public void updateTarget(double x, double y) {
        if (camera != null) {
            camera.updateTarget(x, y);
        }
    }

    @Override
    public double translateX(double worldX) {
        return camera != null ? camera.translateX(worldX) : worldX;
    }

    @Override
    public double translateY(double worldY) {
        return camera != null ? camera.translateY(worldY) : worldY;
    }
}
