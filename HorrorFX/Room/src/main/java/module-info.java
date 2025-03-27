import dk.sdu.smp4.roomsystem.RoomPlugin;
import dk.sdu.smp4.common.Services.IGamePluginService;

module Room {
    requires Common;
    provides IGamePluginService with RoomPlugin;
    requires Structure;
}