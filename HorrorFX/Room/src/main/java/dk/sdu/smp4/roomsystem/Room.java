package dk.sdu.smp4.roomsystem;

import dk.sdu.smp4.common.data.Entity;
import dk.sdu.smp4.common.data.StaticEntity;
import dk.sdu.smp4.structureSystem.Structure;

import java.util.ArrayList;
import java.util.List;

public class Room {
    Structure topWall, bottomWall, leftWall, rightWall;

    public Structure getTopWall() {
        return topWall;
    }

    public void setTopWall(Structure topWall) {
        this.topWall = topWall;
    }

    public Structure getBottomWall() {
        return bottomWall;
    }

    public void setBottomWall(Structure bottomWall) {
        this.bottomWall = bottomWall;
    }

    public Structure getLeftWall() {
        return leftWall;
    }

    public void setLeftWall(Structure leftWall) {
        this.leftWall = leftWall;
    }

    public Structure getRightWall() {
        return rightWall;
    }

    public void setRightWall(Structure rightWall) {
        this.rightWall = rightWall;
    }
}
