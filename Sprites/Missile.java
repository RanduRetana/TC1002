package Sprites;

import javax.swing.*;

public class Missile extends Sprite{
    private static final int BOARD_WIDTH = 390;
    private static final int MISSILE_SPEED = 2;

    public Missile(int x, int y){
        super(x, y);
    }
    void loadImage(){
        ImageIcon ii = new ImageIcon("Sprites/images/missile.png");
        image = ii.getImage();
    }
    public void move(){
        x += MISSILE_SPEED;
        if(x > BOARD_WIDTH){
            visible = false;
        }
    }
}
