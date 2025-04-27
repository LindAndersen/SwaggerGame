package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.interactable.Services.IQuestInteractable;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;
import javafx.scene.image.Image;

import java.util.Objects;

public class Resin extends SoftEntity {
    private static final Image resinImage = new Image(Objects.requireNonNull(Resin.class.getResourceAsStream("/resin_placeholder.gif")),20, 20, true, true);
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
