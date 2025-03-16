package dk.sdu.smp4.commonplayerlight.data;

import dk.sdu.smp4.common.data.Entity;
import javafx.scene.paint.Color;

public class CommonPlayerLight extends Entity {
    public CommonPlayerLight()
    {
        setPaint(Color.GREEN.deriveColor(1,1,1,0.3));
    }
}
