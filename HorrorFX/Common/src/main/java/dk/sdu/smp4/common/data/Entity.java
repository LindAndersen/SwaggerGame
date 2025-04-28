package dk.sdu.smp4.common.data;

import dk.sdu.smp4.common.Services.GUI.EntityImage;
import dk.sdu.smp4.common.Services.GUI.PolygonColor;

import java.io.Serializable;
import java.util.*;

public abstract class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private PolygonColor paint;
    private boolean shouldRotateAlternative;
    private boolean solid;
    private EntityImage image;

    public Entity()
    {
        paint = PolygonColor.BLACK;
        shouldRotateAlternative = false;

    }

    public void setImage(EntityImage image) {
        this.image = image;
    }

    public EntityImage getImage() {
        return image;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public String getID() {
        return ID.toString();
    }

    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public PolygonColor getPaint() {
        return paint;
    }

    public void setPaint(PolygonColor color) {
        this.paint = color;
    }

    public abstract void collide(World world, Entity entity);

    public boolean isShouldRotateAlternative() {
        return shouldRotateAlternative;
    }

    public void setShouldRotateAlternative(boolean shouldRotateAlternative) {
        this.shouldRotateAlternative = shouldRotateAlternative;
    }
}
