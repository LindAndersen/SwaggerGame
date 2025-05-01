package dk.sdu.smp4.common.Services.GUI;

public interface IGUIManager {
    void setQuestPane(String title, String description);
    void setPausedBox();
    IHealthBar getHealthBar();
    IInventoryHUD getInventoryHUD();
    IFlashlightBar getFlashlightBar();
}
