package dk.sdu.smp4.common.data;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private Paint paint;
    private boolean shouldRotateAlternative;

    public Entity()
    {
        paint = Color.BLACK;
        shouldRotateAlternative = false;

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

    public void setRadius(float radius) {
        this.radius = radius;
    }
        
    public float getRadius() {
        return this.radius;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(String color) {
        this.paint = Color.web(color);
    }

    public void setPaint(Color color)
    {
        this.paint = color;
    }

    public void collide(World world, Entity entity) {
    }

    public boolean isShouldRotateAlternative() {
        return shouldRotateAlternative;
    }

    public void setShouldRotateAlternative(boolean shouldRotateAlternative) {
        this.shouldRotateAlternative = shouldRotateAlternative;
    }
}
