package dk.sdu.smp4.key;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.SoftEntity;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Key extends SoftEntity {
    private final Image bronzeKey = new Image(getClass().getResourceAsStream("/bronze.png"),20, 20, true, true);
    private final Image goldKey = new Image(getClass().getResourceAsStream("/gold.png"),20, 20, true, true);
    private final Image greyKey = new Image(getClass().getResourceAsStream("/grey.png"),20, 20, true, true);
    private final Image silverKey = new Image(getClass().getResourceAsStream("/silver.png"),20, 20, true, true);

    private Map<String, Object> properties = new HashMap<>();

    public Key()
    {

    }

    public Key(String keyId)
    {
        setProperties("keyId", keyId);
        if(keyId.toLowerCase().contains("bronze"))
        {
            setImage(bronzeKey);
        }else if (keyId.toLowerCase().contains("gold"))
        {
            setImage(goldKey);
        } else if (keyId.toLowerCase().contains("grey")) {
            setImage(greyKey);
        } else
        {
            setImage(silverKey);
        }
    }


    public Object getProperty(String key) {
        return properties.get(key);
    }

    public void setProperties(String key, Object value){
        properties.put(key, value);
    }
}

