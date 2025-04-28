package dk.sdu.smp4.common.gui.services;

import dk.sdu.smp4.common.Services.GUI.IGUIManager;
import dk.sdu.smp4.common.Services.GUI.IHealthBar;
import dk.sdu.smp4.common.Services.GUI.IInventoryHUD;

public class GUIManagerProvider implements IGUIManager {
    private static GUIManager instance;

    public GUIManagerProvider() {
        // no-arg constructor needed for ServiceLoader
    }

    public static void setInstance(GUIManager guiManager) {
        instance = guiManager;
    }

    private GUIManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GUIManager instance has not been initialized yet!");
        }
        return instance;
    }

    @Override
    public void setQuestPane(String title, String description) {
        getInstance().setQuestPane(title, description);
    }

    @Override
    public void setPausedBox() {
        getInstance().setPausedBox();
    }

    @Override
    public IHealthBar getHealthBar() {
        return getInstance().getHealthBar();
    }

    @Override
    public IInventoryHUD getInventoryHUD() {
        return null;
    }
}

