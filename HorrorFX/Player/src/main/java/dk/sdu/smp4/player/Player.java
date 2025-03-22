package dk.sdu.smp4.player;

import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.World;

public class Player extends SoftEntity {

    private boolean hasKey = false;
  
    @Override
    public void collide(World world, Entity entity) {
    }

    public boolean hasKey(){
        return hasKey;
    }

    public void setHasKey(boolean hasKey){
        this.hasKey = hasKey;
    }
}
