package main;

import java.util.concurrent.ThreadLocalRandom;

public class PickUp {
    int x,y;
    GamePanel gp = new GamePanel();

    public PickUp() {
        this.x = ThreadLocalRandom.current().nextInt(0, 16)* gp.tileSize;
        this.y = ThreadLocalRandom.current().nextInt(0,16) * gp.tileSize;

    }

    public void reset() {
        this.x = ThreadLocalRandom.current().nextInt(0, 16)* gp.tileSize;
        this.y = ThreadLocalRandom.current().nextInt(0,16) * gp.tileSize;
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
