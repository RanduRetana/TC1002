package Pong;

import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class Board extends JPanel implements ActionListener {
    private static final int B_WIDTH = 400;
    static final int B_HEIGHT = 300;
    private static final int DELAY = 8;
    private Timer timer;
    private Player player;
    private Player2 player2;
    private Ball ball;
    private boolean inGame = true;
    private int playerScore = 0;
    private int player2Score = 0;

    private int playerDy = 0;
    private int player2Dy = 0;
    private int ballDx = 0;
    private int ballDy = 0;

    private String countodwn = "";


    public Board(){
        initBoard();
    }

    private void initBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.white);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        player = new Player(10, B_HEIGHT / 2);
        player2 = new Player2(370, B_HEIGHT / 2);
        ball = new Ball(200, 150);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(inGame){
            drawObjects(g);
        }else{
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g){
        g.setColor(Color.black);
        g.drawRect(0, 0, B_WIDTH , B_HEIGHT );
        g.drawLine(200, 0, 200, B_HEIGHT);
        g.setFont(new Font("Helvetica", Font.BOLD, 20));
        g.drawString(countodwn,B_WIDTH / 2, B_HEIGHT / 2);
        g.drawString("Player 1: " + playerScore, 10, 20);
        g.drawString("Player 2: " + player2Score, 250, 20);
        g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        g.drawImage(player2.getImage(), player2.getX(), player2.getY(), this);
        g.drawImage(ball.getImage(), ball.getX(), ball.getY(), this);
    }

    private void drawGameOver(Graphics g){
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }


    @Override
    public void actionPerformed(java.awt.event.ActionEvent e){
        inGame();
        updatePlayer();
        updatePlayer2();
        updateBall();
        checkCollisions();
        repaint();
    }

    private void inGame(){
        if(!inGame){
            timer.stop();
        }
    }

    private void updatePlayer(){
        playerDy = player.getDy();
        player.move(0, playerDy);
    }

    private void updatePlayer2(){
        player2Dy = player2.getDy();
        player2.move(0, player2Dy);
    }

    private void updateBall(){
        ballDx = ball.getDx();
        ballDy = ball.getDy();
        ball.move(ballDx, ballDy);
    }

    private void checkCollisions(){
        if(ball.getBounds().intersects(player.getBounds())){
            ball.setDx(-ball.getDx());
        }
        if(ball.getBounds().intersects(player2.getBounds())){
            ball.setDx(-ball.getDx());
        }

        if(ball.getY() <= 0 || ball.getY() >= B_HEIGHT - ball.getHeight()){
            ball.setDy(-ball.getDy());
        }

        if(ball.getX() == 0){
            player2Score++;
            startCountdown();
        }
        if(ball.getX() == 400){
            playerScore++;
            startCountdown();
        }
        if(ball.getY() == 0){
            ballDy = 2;
        }
        if(ball.getY() == 300){
            ballDy = -2;
        }
    }

    private void resetBall() {
        ball.setX(B_WIDTH / 2);
        ball.setY(B_HEIGHT / 2);
        ball.setDx(-ball.getDx());
        ball.setDy(-ball.getDy());
        countodwn = "";
    }

    private void startCountdown(){
        new java.util.Timer().schedule(new java.util.TimerTask() {
            int counter = 3;
            @Override
            public void run() {
                if (counter == 0) {
                    resetBall();
                    this.cancel();
                } else {
                    countodwn = Integer.toString(counter);
                    counter--;
                }
            }
        }, 0, 1000);
    }

    private class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(java.awt.event.KeyEvent e){
            player.keyPressed(e);
            player2.keyPressed(e);
        }
        @Override
        public void keyReleased(java.awt.event.KeyEvent e){
            player.keyReleased(e);
            player2.keyReleased(e);
        }
    }
}
