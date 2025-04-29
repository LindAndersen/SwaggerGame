package dk.sdu.smp4.commonplayerlight.services;

import dk.sdu.smp4.common.data.Entity;

public interface IToggleableLight {
    void toggle();
    boolean isOn();
    void reload(Entity player);
}
