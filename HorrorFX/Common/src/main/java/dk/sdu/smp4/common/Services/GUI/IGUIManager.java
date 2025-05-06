package dk.sdu.smp4.common.Services.GUI;

public interface IGUIManager {
    void setQuestPane(String title, String description);
    void setPausedBox();
    IHealthBar getHealthBar();
    IInventoryHUD getInventoryHUD();
    void updateCamera(double zoomX, double zoomY, double offsetX, double offsetY);

}
