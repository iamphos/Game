package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    Point p;

    //Tile settings
    final int originalTileSize = 16;  // 16x16 Tile
    final int scale = 3;


    //Screen settings
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // fps
    int FPS = 10;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // set Player default position
    int playerX = 0;
    int playerY = 0;
    int playerSpeed = tileSize;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setBounds(100,100, screenWidth, screenHeight);
        this.setDoubleBuffered(true);  //rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null){

            // Update screen
            update();

            // re-Draw
            repaint();



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public void update() {
        Snake.move();
        Collision.collidePickUp();

        if(Collision.collideSelf()) {
            Snake.tails.clear();

        }
        if(Collision.collideWall()) {
            Snake.tails.clear();
            Snake.pickUp.reset();
            Snake.head.setX(tileSize*3);
            Snake.head.setY(tileSize*3);
            Snake.head.setDir(Direction.STILL);
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Border
        g2.setColor(Color.GRAY);
        for (int i = 0; i < screenWidth; i++) {
            for (int j = 0; j < screenWidth; j++) {
                g2.drawRect(i * tileSize, j * tileSize, tileSize, tileSize);
            }
        }

        //Scoreboard
        Font myFont2 = new Font("Roboto", 1, 20);
        g2.setFont(myFont2);
        g2.setColor(Color.RED);
        g2.drawString("Score: " + Snake.score.getScore(), tileSize*12, tileSize/2);


        if (Snake.head.dir == Direction.STILL) {
            Font myFont = new Font("Roboto", Font.BOLD, 50);
            g2.setFont(myFont);
            g2.setColor(Color.BLACK);
            g2.drawString("MOVE TO START", 300, 90);
        }

        //Pickup
        g2.setColor(Color.BLUE);
        p = Snake.ptc(Snake.pickUp.getX(), Snake.pickUp.getY());
        g2.fillRect(Snake.pickUp.getX(), Snake.pickUp.getY(), tileSize, tileSize);

        //Tail
        g2.setColor(Color.GREEN);
        for(int i = 0; i < Snake.tails.size(); i++) {
            p = Snake.ptc(Snake.tails.get(i).getX(), Snake.tails.get(i).getY());
            g2.fillRect(p.x,p.y, tileSize,tileSize);
        }




        //Head
        g2.setColor(Color.BLACK);
        g2.fillRect(Snake.head.getX(), Snake.head.getY(), tileSize, tileSize);
        g2.dispose();



    }
}
