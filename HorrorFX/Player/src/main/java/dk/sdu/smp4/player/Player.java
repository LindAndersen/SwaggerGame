package dk.sdu.smp4.player;

import dk.sdu.smp4.common.data.Entity;

public class Player extends Entity {
    private boolean hasKey = false;

    public boolean hasKey(){
        return hasKey;
    }

    public void setHasKey(boolean hasKey){
        this.hasKey = hasKey;
    }

}
