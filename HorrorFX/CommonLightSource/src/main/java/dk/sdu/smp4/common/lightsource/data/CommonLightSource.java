package dk.sdu.smp4.common.lightsource.data;

import dk.sdu.smp4.common.data.SoftEntity;

public class CommonLightSource extends SoftEntity {
    private int numRays;
    private double angleWidth;
    private double radiusFactor;
    private double strokeWidth;

    public CommonLightSource(){
        this.strokeWidth = 0.5;
    }

    public CommonLightSource(int numRays, double angleWidth, double radiusFactor)
    {
        this.numRays = numRays;
        this.angleWidth = angleWidth;
        this.radiusFactor = radiusFactor;
        this.strokeWidth = 0.5;
    }

    public int getNumRays() {
        return numRays;
    }

    public void setNumRays(int numRays) {
        this.numRays = numRays;
    }

    public double getAngleWidth() {
        return angleWidth;
    }

    public void setAngleWidth(double angleWidth) {
        this.angleWidth = angleWidth;
    }

    public double getRadiusFactor() {
        return radiusFactor;
    }

    public void setRadiusFactor(double radiusFactor) {
        this.radiusFactor = radiusFactor;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}
