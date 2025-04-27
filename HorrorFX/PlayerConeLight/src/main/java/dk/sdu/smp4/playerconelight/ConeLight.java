package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.interactable.Services.InventorySPI;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;
import dk.sdu.smp4.commonplayerlight.data.CommonPlayerLight;
import dk.sdu.smp4.commonplayerlight.services.IToggleableLight;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class ConeLight extends CommonPlayerLight implements IToggleableLight {
    private static int TOGGLE_COOLDOWN = 300;
    private static long FLASHLIGHT_START_TIME = 60_000;
    private long flashlightCurrentTime;
    private long timeReference;
    private long lastToggle = 0;
    private boolean toggled = true;
    private boolean toggleLocked = false;

    public ConeLight(){
        setNumRays(80);
        setAngleWidth(50);
        setRadiusFactor(2);
        setShouldRotateAlternative(true);

        flashlightCurrentTime = FLASHLIGHT_START_TIME;
        timeReference = System.currentTimeMillis();
    }

    @Override
    public void toggle() {
        if(toggleLocked){return;}

        long now = System.currentTimeMillis();
        if(now - lastToggle > TOGGLE_COOLDOWN){
            lastToggle = now;
            toggled = !toggled;
        }
    }

    @Override
    public boolean isOn(){
        return toggled;
    }

    @Override
    public void reload(Entity player) {
        InventorySPI inventorySPI = getInventorySPIs().stream().findFirst().orElse(null);
        if(inventorySPI == null){return;}
        if(!(inventorySPI.containsInventoryForEntity(player) && inventorySPI.contains(player, InventorySlotItems.RESIN))){return;}

        changeTime(Resin.FLASHLIGHT_TIME_INCREASE);
        toggleLocked = false;
        inventorySPI.remove(player, InventorySlotItems.RESIN, 1);
    }

    public void changeTime(long time)
    {
        flashlightCurrentTime += time;
    }

    public void tick()
    {
        // time left - (current time - last time since change)
        flashlightCurrentTime -= (System.currentTimeMillis() - timeReference);
        if(flashlightCurrentTime <= 0 && isOn()){
            toggle();
            toggleLocked = true;
        }
    }

    public long getFlashLightCurrentTime()
    {
        return flashlightCurrentTime;
    }

    private Collection<? extends InventorySPI> getInventorySPIs() {
        return ServiceLoader.load(InventorySPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
