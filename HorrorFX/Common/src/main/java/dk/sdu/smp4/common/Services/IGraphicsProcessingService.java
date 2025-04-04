package dk.sdu.smp4.common.Services;

import dk.sdu.smp4.common.data.GameData;
import dk.sdu.smp4.common.data.World;
import javafx.scene.layout.Pane;

public interface IGraphicsProcessingService {
    void render(GameData gameData, World world, Pane gameWindow);
}
