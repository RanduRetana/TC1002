package Sprites;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Board extends JPanel implements ActionListener {
    private static final int DELAY = 10;
    private Timer timer;
    private SpaceShip spaceship;
    private List<Alien> aliens;
    private boolean ingame;
    private static final int B_WIDTH = 400;
    private static final int B_HEIGHT = 300;
    private static final int[][] positions = {
        {2380, 29}, {2500,59}, {1380, 89},
        {780, 109}, {580, 139}, {680, 239},
        {790, 259}, {760, 50}, {790, 150},
        {980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 60},
        {940, 59}, {990, 30}, {920, 200},
        {900, 259}, {660, 50}, {540, 90},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30}
    };

    public Board(){
        initUI();
    }
    private void initUI(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        spaceship = new SpaceShip();
        initAliens();
        ingame = true;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initAliens(){
        aliens = new ArrayList<>();
        for(int[] p : positions){
            aliens.add(new Alien(p[0], p[1]));
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(ingame){
            doDrawing(g);
        }else{
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void doDrawing(Graphics g){
        if (spaceship.isVisible()) {
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), null);
        }

        List<Missile> missiles = spaceship.getMissiles();
        for(Missile missile : missiles){
            g.drawImage(missile.getImage(), missile.getX(), missile.getY(), null);
        }

        for(Alien alien : aliens){
            if(alien.isVisible()){
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), null);
            }
        }
        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    private void drawGameOver(Graphics g){
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 24);
        FontMetrics fm = getFontMetrics(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg))/2, B_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        updateSpaceShip();
        updateMissiles();
        updateAliens();
        checkCollisions();
        repaint();
        inGame();
    }
    private void inGame(){
        if(!ingame){
            timer.stop();
        }
    }
    private void updateSpaceShip(){
        if (spaceship.isVisible()) {
            spaceship.move();
        }
    }
    private void updateMissiles(){
        List<Missile> missiles = spaceship.getMissiles();
        ListIterator<Missile> iterator = missiles.listIterator();
        while(iterator.hasNext()){
            Missile missile = iterator.next();
            if(missile.isVisible()){
                missile.move();
            }else{
                iterator.remove();
            }
        }
    }
    private void updateAliens(){
        if(aliens.isEmpty()){
            ingame = false;
            return;
        } else {
            ListIterator<Alien> iterator = aliens.listIterator();
            while(iterator.hasNext()){
                Alien alien = iterator.next();
                if(alien.isVisible()){
                    alien.move();
                }else{
                    iterator.remove();
                }
            }
        }
    }
    private void checkCollisions(){
        Rectangle rSpaceShip = spaceship.getBounds();
        for(Alien alien : aliens){
            Rectangle rAlien = alien.getBounds();
            if(rSpaceShip.intersects(rAlien)){
                spaceship.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }
        List<Missile> missiles = spaceship.getMissiles();
        for(Missile missile : missiles){
            Rectangle rMissile = missile.getBounds();
            for(Alien alien : aliens){
                Rectangle rAlien = alien.getBounds();
                if(rMissile.intersects(rAlien)){
                    missile.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }
    private class TAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            spaceship.keyReleased(e);
        }
        @Override
        public void keyPressed(KeyEvent e){
            spaceship.keyPressed(e);
        }
    }
}
