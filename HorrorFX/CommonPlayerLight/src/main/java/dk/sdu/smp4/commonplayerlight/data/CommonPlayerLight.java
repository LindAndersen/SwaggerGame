package dk.sdu.smp4.commonplayerlight.data;

import dk.sdu.smp4.common.lightsource.data.CommonLightSource;

public class CommonPlayerLight extends CommonLightSource {
    public CommonPlayerLight()
    {
        //setPaint(Color.WHITE.deriveColor(0,1,1,0));
    }

    @Override
    public void setRadius(double radius) {
        super.setRadius(radius*getRadiusFactor());
    }
}
