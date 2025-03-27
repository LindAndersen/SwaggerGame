package dk.sdu.smp4.commonplayerlight.data;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.SoftEntity;
import dk.sdu.smp4.common.data.World;
import dk.sdu.smp4.common.lightsource.data.CommonLightSource;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CommonPlayerLight extends CommonLightSource {
    public CommonPlayerLight()
    {
        setPaint(Color.YELLOW.deriveColor(0,1,1,0.2));
    }

    @Override
    public void setRadius(double radius) {
        super.setRadius(radius*getRadiusFactor());
    }
}
