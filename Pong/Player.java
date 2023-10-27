package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static Pong.Board.B_HEIGHT;

public class Player extends Sprite {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 50;
    private int dy;

    public Player(int x, int y) {
        super(x, y);
        loadImage();
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        if (y < 0) {
            y = 0;
        } else if (y > B_HEIGHT - HEIGHT){
            y = Board.HEIGHT - HEIGHT;
        }
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("Pong/images/playerLarge.png");
        image = ii.getImage();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    public int getDy() {
        return dy;
    }

}
