package dk.sdu.smp4.common.gui.util;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import javafx.scene.image.Image;

import java.util.Objects;

public class EntityImageConverter {
    public static Image convertEntityImage(EntityImage entityImage, Class<?> ressourceClass) {
        return new Image(
                Objects.requireNonNull(ressourceClass.getResourceAsStream(entityImage.getPath())),
                entityImage.getRequestedWidth(),
                entityImage.getRequestedHeight(),
                entityImage.isPreserveRatio(),
                entityImage.isSmooth()
        );
    }
}
