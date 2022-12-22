package main;

public class SnakeHead {
    Direction dir = Direction.STILL;
    int x,y;

    public SnakeHead(int x, int y) {
        this.x = x;
        this.y = y;

    }
    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }







}
