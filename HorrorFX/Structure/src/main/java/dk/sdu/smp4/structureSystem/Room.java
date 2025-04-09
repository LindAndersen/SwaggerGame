package dk.sdu.smp4.structureSystem;

public class Room extends Structure {
    Structure topLeftWall, topRightWall, bottomWall, leftWall, rightWall;

    public Structure getTopWallLeft() {
        return topLeftWall;
    }
    public Structure getTopWallRight() {
        return topRightWall;
    }

    public void setTopWallLeft(Structure topWall) {
        this.topLeftWall = topWall;
    }

    public void setTopWallRight(Structure topWall) {
        this.topRightWall = topWall;
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
