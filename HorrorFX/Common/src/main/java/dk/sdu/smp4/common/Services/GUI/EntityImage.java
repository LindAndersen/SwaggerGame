package dk.sdu.smp4.common.Services.GUI;

public class EntityImage {
    private String path;
    private double requestedWidth;
    private double requestedHeight;
    private boolean preserveRatio;
    private boolean smooth;
    private final Class<?> resourceClass;

    public EntityImage(String path, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, Class<?> resourceClass) {
        this.path = path;
        this.requestedWidth = requestedWidth;
        this.requestedHeight = requestedHeight;
        this.preserveRatio = preserveRatio;
        this.smooth = smooth;
        this.resourceClass = resourceClass;
    }

    public String getPath() {
        return path;
    }

    public double getRequestedWidth() {
        return requestedWidth;
    }

    public double getRequestedHeight() {
        return requestedHeight;
    }

    public boolean isPreserveRatio() {
        return preserveRatio;
    }

    public boolean isSmooth() {
        return smooth;
    }

    public Class<?> getResourceClass() {
        return resourceClass;
    }
}
