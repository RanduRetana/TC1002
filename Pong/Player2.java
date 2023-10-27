package Pong;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Player2 extends Sprite{
    private int dy;
    private int WIDTH = 15;
    private int HEIGHT = 50;
    public Player2(int x, int y){
        super(x, y);
        loadImage();
    }
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    public void loadImage(){
        ImageIcon ii = new ImageIcon("Pong/images/playerLarge.png");
        image = ii.getImage();
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == 87){
            dy = -2;
        }
        if(key == 83){
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 87 || key == 83) {
            dy = 0;
        }
    }

    public int getDy(){
        return dy;
    }
}
