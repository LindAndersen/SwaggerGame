package dk.sdu.smp4.structureSystem;

public class Room extends Structure {
    Structure topWall, bottomWall, leftWall, rightWall;

    public Structure getTopWallLeft() {
        return topWall;
    }
    public Structure getTopWallRight() {
        return topWall;
    }


    public void setTopWallLeft(Structure topWall) {
        this.topWall = topWall;
    }

    public void setTopWallRight(Structure topWall) {
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
