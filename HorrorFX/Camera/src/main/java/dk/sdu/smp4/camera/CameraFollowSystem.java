package dk.sdu.smp4.camera;

import dk.sdu.smp4.common.Services.GUI.IGUIManager;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.commonplayer.services.ICameraProcessor;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class CameraFollowSystem implements ICameraProcessor {

    @Override
    public void updateTarget(Entity player, GameData gameData, World world) {
        Camera camera = Camera.getInstance();
        double cameraWidth = camera.getCameraSizeX();
        double cameraHeight = camera.getCameraSizeY();
        double zoomX = gameData.getDisplayWidth() / cameraWidth;
        double zoomY = gameData.getDisplayHeight() / cameraHeight;

        // Default camera center based on player
        double cameraX = player.getX() - cameraWidth / 2.0;
        double cameraY = player.getY() - cameraHeight / 2.0;

        // Clamp camera within world bounds
        cameraX = Math.max(0, Math.min(cameraX, world.getMapWidth() - cameraWidth));
        cameraY = Math.max(0, Math.min(cameraY, world.getMapHeight() - cameraHeight));

        // Offset is negative of top-left corner of camera
        double offsetX = -cameraX;
        double offsetY = -cameraY;

        camera.setZoomX(zoomX);
        camera.setZoomY(zoomY);
        camera.setOffsetX(offsetX);
        camera.setOffsetY(offsetY);

        getIGUIManagerSPI().stream().findFirst().ifPresent(spi -> {spi.updateCamera(zoomX, zoomY, offsetX, offsetY);});

    }

    @Override
    public double getPlayerAngle(Entity player, GameData gameData, World world, double mouseScreenX, double mouseScreenY)
    {
        Camera camera = Camera.getInstance();

        double mouseWorldX = (mouseScreenX / camera.getZoomX()) - camera.getOffsetX();
        double mouseWorldY = (mouseScreenY / camera.getZoomY()) - camera.getOffsetY();

        double dx = mouseWorldX - player.getX();
        double dy = mouseWorldY - player.getY();

        return Math.toDegrees(Math.atan2(dy, dx));
    }

    private Collection<? extends IGUIManager> getIGUIManagerSPI() {
        return ServiceLoader.load(IGUIManager.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}

