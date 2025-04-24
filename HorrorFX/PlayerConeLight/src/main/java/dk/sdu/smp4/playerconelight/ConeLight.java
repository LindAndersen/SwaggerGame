package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.commonplayerlight.data.CommonPlayerLight;
import dk.sdu.smp4.commonplayerlight.services.IToggleableLight;

public class ConeLight extends CommonPlayerLight implements IToggleableLight {
    private static int TOGGLE_COOLDOWN = 500;
    private long lastToggle = 0;
    private boolean toggled = true;

    public ConeLight(){
        setNumRays(80);
        setAngleWidth(50);
        setRadiusFactor(2);
        setShouldRotateAlternative(true);
    }

    @Override
    public void toggle() {
        long now = System.currentTimeMillis();
        if(now - lastToggle > TOGGLE_COOLDOWN){
            lastToggle = now;
            toggled = !toggled;
        }
    }

    @Override
    public boolean isToggled(){
        return toggled;
    }
}
