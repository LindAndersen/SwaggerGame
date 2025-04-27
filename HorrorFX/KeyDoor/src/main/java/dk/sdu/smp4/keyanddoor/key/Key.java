package dk.sdu.smp4.keyanddoor.key;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;
import javafx.scene.image.Image;

public class Key extends SoftEntity {
    private static final Image bronzeKey = new Image(Key.class.getResourceAsStream("/bronze.png"),20, 20, true, true);
    private static final Image goldKey = new Image(Key.class.getResourceAsStream("/gold.png"),20, 20, true, true);
    private static final Image greyKey = new Image(Key.class.getResourceAsStream("/grey.png"),20, 20, true, true);
    private static final Image silverKey = new Image(Key.class.getResourceAsStream("/silver.png"),20, 20, true, true);

    private InventorySlotItems inventoryIdentifier;

    public Key(String keyId)
    {
        if(keyId.toLowerCase().contains("bronze"))
        {
            setImage(bronzeKey);
            inventoryIdentifier = InventorySlotItems.BRONZE_KEY;
        }else if (keyId.toLowerCase().contains("gold"))
        {
            setImage(goldKey);
            inventoryIdentifier = InventorySlotItems.GOLDEN_KEY;
        } else if (keyId.toLowerCase().contains("grey")) {
            setImage(greyKey);
            inventoryIdentifier = InventorySlotItems.GREY_KEY;
        } else
        {
            setImage(silverKey);
            inventoryIdentifier = InventorySlotItems.SILVER_KEY;
        }
    }

    public InventorySlotItems getInventoryIdentifier() {
        return inventoryIdentifier;
    }
}

