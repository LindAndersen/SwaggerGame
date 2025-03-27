package dk.sdu.smp4.common.lightsource.data;

import dk.sdu.smp4.common.data.SoftEntity;

public class CommonLightSource extends SoftEntity {
    public double NUM_RAYS;
    public double RAY_LENGTH;
    public double LIGHT_SOURCE_X;
    public double LIGHT_SOURCE_Y;


    public CommonLightSource()
    {
        NUM_RAYS = 360;
        RAY_LENGTH = this.getRadius();
        LIGHT_SOURCE_X = this.getX();
        LIGHT_SOURCE_Y = this.getY();
    }

    public double getNUM_RAYS() {
        return NUM_RAYS;
    }

    public void setNUM_RAYS(double NUM_RAYS) {
        this.NUM_RAYS = NUM_RAYS;
    }

    public double getRAY_LENGTH() {
        return RAY_LENGTH;
    }

    public void setRAY_LENGTH(double RAY_LENGTH) {
        this.RAY_LENGTH = RAY_LENGTH;
    }

    public double getLIGHT_SOURCE_X() {
        return LIGHT_SOURCE_X;
    }

    public void setLIGHT_SOURCE_X(double LIGHT_SOURCE_X) {
        this.LIGHT_SOURCE_X = LIGHT_SOURCE_X;
    }

    public double getLIGHT_SOURCE_Y() {
        return LIGHT_SOURCE_Y;
    }

    public void setLIGHT_SOURCE_Y(double LIGHT_SOURCE_Y) {
        this.LIGHT_SOURCE_Y = LIGHT_SOURCE_Y;
    }
}
