package dk.sdu.smp4.door;

import dk.sdu.smp4.common.data.HardEntity;


public class Door extends HardEntity {
    private String requiredKey;

    public void setRequiredKey(String requiredKey) {
        this.requiredKey = requiredKey;
    }

    public String getRequiredKey() {
        return requiredKey;
    }
}
