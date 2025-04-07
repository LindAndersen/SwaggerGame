package dk.sdu.smp4.playercirclelight;

import dk.sdu.smp4.commonplayerlight.data.CommonPlayerLight;

public class CircleLight extends CommonPlayerLight {

    public CircleLight(){
        setNumRays(100);
        setAngleWidth(360);
        setRadiusFactor(3);
        setShouldRotateAlternative(false);
    }
}
