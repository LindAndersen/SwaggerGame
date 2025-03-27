package dk.sdu.smp4.commonplayerlight.data;

import dk.sdu.smp4.common.data.SoftEntity;
import javafx.scene.paint.Color;

public class CommonPlayerLight extends SoftEntity {
    public CommonPlayerLight()
    {
        setPaint(Color.GREEN.deriveColor(1,1,1,0.3));
    }
}
