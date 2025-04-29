package dk.sdu.smp4.resin;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

public class Resin extends SoftEntity {
    private static final EntityImage resinImage = new EntityImage("/resin_placeholder.gif",20, 20, true, true, Resin.class);
    public static final long FLASHLIGHT_TIME_INCREASE = 30*500;
    private final InventorySlotItems inventoryIdentifier = InventorySlotItems.RESIN;

    public Resin()
    {
        setImage(resinImage);
    }

    public InventorySlotItems getInventoryIdentifier() {
        return inventoryIdentifier;
    }
}
