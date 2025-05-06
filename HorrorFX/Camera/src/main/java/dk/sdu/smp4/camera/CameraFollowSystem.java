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
        double zoomX = gameData.getDisplayWidth()/ camera.getCameraSizeX();
        double zoomY = gameData.getDisplayHeight()/ camera.getCameraSizeY();
        double offsetX = -player.getX()+ camera.getCameraSizeX()/2;
        double offsetY = -player.getY()+ camera.getCameraSizeY()/2;

        getIGUIManagerSPI().stream().findFirst().ifPresent(spi -> {spi.updateCamera(zoomX, zoomY, offsetX, offsetY);});

    }

    private Collection<? extends IGUIManager> getIGUIManagerSPI() {
        return ServiceLoader.load(IGUIManager.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}

