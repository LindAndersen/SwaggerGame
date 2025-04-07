package dk.sdu.smp4.common.data;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.Serializable;
import java.util.*;

public abstract class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private Paint paint;
    private boolean shouldRotateAlternative;
    private boolean solid;
    private Map<String, Object> properties = new HashMap<>();
    private Set<String> inventory = new HashSet<>();
    private String type;
    private Image image;

    public Entity()
    {
        paint = Color.BLACK;
        shouldRotateAlternative = false;

    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
    public Object getProperty(String key) {
        return properties.get(key);
    }

    public void setProperties(String key, Object value){
        properties.put(key, value);
    }

    public Set<String> getInventory(){
        return inventory;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
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
