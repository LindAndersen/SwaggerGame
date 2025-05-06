import dk.sdu.smp4.camera.CameraFollowSystem;
import dk.sdu.smp4.commonplayer.services.ICameraProcessor;

module Camera {
    uses dk.sdu.smp4.common.Services.GUI.IGUIManager;
    requires Common;
    requires CommonPlayer;

    provides ICameraProcessor with CameraFollowSystem;
}
