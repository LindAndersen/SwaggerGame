import dk.sdu.smp4.common.Services.GameLoop.IGamePluginService;
import dk.sdu.smp4.common.Services.GameLoop.ICamera;


module Camera {
    requires Common;

    provides dk.sdu.smp4.common.Services.GameLoop.IGamePluginService
            with dk.sdu.smp4.camera.CameraPlugin;

    provides dk.sdu.smp4.common.Services.GameLoop.ICamera
            with dk.sdu.smp4.camera.CameraPlugin;

    exports dk.sdu.smp4.camera;
}
