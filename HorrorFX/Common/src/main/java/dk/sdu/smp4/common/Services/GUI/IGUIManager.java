package dk.sdu.smp4.common.Services.GUI;

public interface IGUIManager {
    void showQuestPopup(String title, String description);
    void showPausePopup();
    IHealthBar getHealthBar();
    IInventoryHUD getInventoryHUD();
}
