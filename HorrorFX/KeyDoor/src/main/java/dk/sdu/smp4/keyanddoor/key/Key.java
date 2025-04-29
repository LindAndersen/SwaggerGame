package dk.sdu.smp4.keyanddoor.key;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.interactable.data.InventorySlotItems;

public class Key extends SoftEntity {
    private static final EntityImage bronzeKey = new EntityImage("/bronze.png",20, 20, true, true, Key.class);
    private static final EntityImage goldKey = new EntityImage("/gold.png",20, 20, true, true, Key.class);
    private static final EntityImage greyKey = new EntityImage("/grey.png",20, 20, true, true, Key.class);
    private static final EntityImage silverKey = new EntityImage("/silver.png",20, 20, true, true, Key.class);

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

