package Sprites;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SpaceShip extends Sprite{
    private int dx, dy;
    private List<Missile> missiles;

    public SpaceShip(){
        super(40, 60);
        missiles = new ArrayList<Missile>();
    }

    public void loadImage(){
        ImageIcon ii = new ImageIcon("Sprites/images/craft.png");
        image = ii.getImage();
    }

    public void move(){
        x += dx;
        y += dy;
    }
    public List<Missile> getMissiles(){
        return missiles;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            dx = -2;
        }
        if(key == KeyEvent.VK_RIGHT){
            dx = 2;
        }
        if(key == KeyEvent.VK_UP){
            dy = -2;
        }
        if(key == KeyEvent.VK_DOWN){
            dy = 2;
        }
        if(key == KeyEvent.VK_SPACE){
            fire();
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if(key == KeyEvent.VK_RIGHT){
            dx = 0;
        }
        if(key == KeyEvent.VK_UP){
            dy = 0;
        }
        if(key == KeyEvent.VK_DOWN){
            dy = 0;
        }
    }
    public void checkBorders(){
        if(x < 1){
            x = 300;
        }
        if(x > 300){
            x = 1;
        }
        if(y < 1){
            y = 300;
        }
        if(y > 300){
            y = 1;
        }
    }
    public void fire(){
        missiles.add(new Missile(x + width, y + height/2));
    }

}
