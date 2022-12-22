package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W && Snake.head.dir != Direction.DOWN) {
            Snake.head.setDir(Direction.UP);
        }
        if(code == KeyEvent.VK_S && Snake.head.dir != Direction.UP) {
            Snake.head.setDir(Direction.DOWN);
        }
        if(code == KeyEvent.VK_A && Snake.head.dir != Direction.RIGHT) {
            Snake.head.setDir(Direction.LEFT);
        }
        if(code == KeyEvent.VK_D && Snake.head.dir != Direction.LEFT) {
            Snake.head.setDir(Direction.RIGHT);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
