package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Tile settings
    final int originalTileSize = 16;  // 16x16 Tile
    final int scale = 3;


    //Screen settings
    final int tileSize = originalTileSize * scale;
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

        if(keyH.upPressed) {
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed) {
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(playerX, playerY,tileSize,tileSize);
        g2.dispose();
    }
}
