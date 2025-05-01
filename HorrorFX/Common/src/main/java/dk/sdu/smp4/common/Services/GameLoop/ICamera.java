package dk.sdu.smp4.common.Services.GameLoop;

public interface ICamera {

    void updateTarget(double x, double y);


    double translateX(double worldX);


    double translateY(double worldY);
}
