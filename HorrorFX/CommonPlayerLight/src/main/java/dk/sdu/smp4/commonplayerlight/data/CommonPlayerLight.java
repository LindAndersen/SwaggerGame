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
        setPaint(Color.GREEN.deriveColor(1,1,1,0.3));
    }
}
