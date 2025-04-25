package dk.sdu.smp4.key;

import dk.sdu.smp4.common.data.SoftEntity;
import javafx.scene.image.Image;

public class Key extends SoftEntity {
    private static final Image bronzeKey = new Image(Key.class.getResourceAsStream("/bronze.png"),20, 20, true, true);
    private static final Image goldKey = new Image(Key.class.getResourceAsStream("/gold.png"),20, 20, true, true);
    private static final Image greyKey = new Image(Key.class.getResourceAsStream("/grey.png"),20, 20, true, true);
    private static final Image silverKey = new Image(Key.class.getResourceAsStream("/silver.png"),20, 20, true, true);

    private String inventoryIdentifier;

    public Key(String keyId)
    {
        if(keyId.toLowerCase().contains("bronze"))
        {
            setImage(bronzeKey);
            inventoryIdentifier = "bronze_key";
        }else if (keyId.toLowerCase().contains("gold"))
        {
            setImage(goldKey);
            inventoryIdentifier = "golden_key";
        } else if (keyId.toLowerCase().contains("grey")) {
            setImage(greyKey);
            inventoryIdentifier = "grey_key";
        } else
        {
            setImage(silverKey);
            inventoryIdentifier = "silver_key";
        }
    }

    public String getInventoryIdentifier() {
        return inventoryIdentifier;
    }
}

