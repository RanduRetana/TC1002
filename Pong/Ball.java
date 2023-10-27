package Pong;

import javax.swing.*;

import static Pong.Board.B_HEIGHT;

public class Ball extends Sprite{
    private int dx, dy;
    private static final int HEIGHT = 29;
    public Ball(int x, int y){
        super(x, y);
        loadImage();
        dx = 2;
        dy = 2;
    }
    public void move(){
        x += dx;
        y += dy;
    }
    public void loadImage(){
        ImageIcon ii = new ImageIcon("Pong/images/boluki.png");
        image = ii.getImage();
    }
    public void setDx(int dx){
        this.dx = dx;
    }
    public void setDy(int dy){
        this.dy = dy;
    }
    public int getDx(){
        return dx;
    }
    public int getDy(){
        return dy;
    }

    public void setX(int i) {
        x = i;
    }

    public void setY(int i) {
        y = i;
    }
    public void move(int dx, int dy){
        x += dx;
        y += dy;
        if (y < 0) {
            dy = -dy;
        } else if (y > B_HEIGHT - HEIGHT) {
            dy = -dy;
        }
    }
}
