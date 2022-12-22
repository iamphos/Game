package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake {
    public static boolean waitToMove = false;

    public static GamePanel gp = new GamePanel();

    public static Score score = new Score(0);
    public static SnakeHead head = new SnakeHead(gp.tileSize*3,gp.tileSize*3);
    public static ArrayList <SnakeTail> tails = new ArrayList<>();

    public static PickUp pickUp = new PickUp();


    public static void addTail() {
        if(tails.size() < 1) {
            tails.add(new SnakeTail(head.getX(), head.getY()));
        }
        else {
            tails.add(new SnakeTail(tails.get(tails.size()-1).x, tails.get(tails.size()-1).y));

        }
    }

    public static void move() {
        if(tails.size() >= 2) {
            for(int i = tails.size()-1; i >= 1; i--) {
                if(tails.get(i).isWait()) {
                    tails.get(i).setWait(false);
                }
                else {
                    tails.get(i).setX(tails.get(i-1).getX());
                    tails.get(i).setY(tails.get(i-1).getY());
                }
            }
        }

        switch (Snake.head.dir) {
            case UP -> Snake.head.setY(Snake.head.getY() - gp.playerSpeed);
            case DOWN -> Snake.head.setY(Snake.head.getY() + gp.playerSpeed);
            case LEFT -> Snake.head.setX(Snake.head.getX()- gp.playerSpeed);
            case RIGHT -> Snake.head.setX(Snake.head.getX() + gp.playerSpeed);
            default -> {break;}
        }


        //Move first tail to head
        if(tails.size() >= 1) {
            if(tails.get(0).isWait()) {
                tails.get(0).setWait(false);
            }
            else {
                tails.get(0).setX(head.getX());
                tails.get(0).setY(head.getY());
            }
        }

        //Move Head

    }


    public static Point ptc(int x, int y) {
        GamePanel gp = new GamePanel();
        Point p = new Point(0, 0);
        p.x = x * gp.tileSize ;
        p.y = y * gp.tileSize ;
        return p;

    }
}
