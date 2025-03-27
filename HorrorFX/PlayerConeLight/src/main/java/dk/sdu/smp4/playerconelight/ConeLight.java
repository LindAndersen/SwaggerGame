package dk.sdu.smp4.playerconelight;

import dk.sdu.smp4.commonplayerlight.data.CommonPlayerLight;

public class ConeLight extends CommonPlayerLight {
    public ConeLight(){
        setNumRays(150);
        setAngleWidth(40);
        setRadiusFactor(30);
        setShouldRotateAlternative(true);
    }
}
