package Snake;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class Board extends JPanel implements ActionListener {
    private static final int B_WIDTH = 300;
    private static final int B_HEIGHT = 300;
    private static final int DOT_SIZE = 10;
    private static final int ALL_DOTS = 900;
    private static final int RAND_POS = 29;
    private static final int DELAY = 140;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    private int dots;
    private int appleX;
    private int appleY;
    private Timer timer;
    private Image ball, apple, head;

    public Board() {
        initUI();
    }
    private void initUI() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }
    private void loadImages(){
        ImageIcon ii;

        ii = new ImageIcon("Snake/images/apple.png");
        apple = ii.getImage();

        ii = new ImageIcon("Snake/images/dot.png");
        ball = ii.getImage();

        ii = new ImageIcon("Snake/images/head.png");
        head = ii.getImage();
    }
    private void initGame(){
        dots = 3;
        for(int z = 0; z < dots; z++){
            x[z] = 50 - (z * DOT_SIZE);
            y[z] = 50;
    }
        locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }
    private void locateApple(){
        int aux;
        aux = (int) (Math.random() * RAND_POS);
        appleX = aux * DOT_SIZE;
        aux = (int) (Math.random() * RAND_POS);
        appleY = aux * DOT_SIZE;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }
    private void doDrawing(Graphics g){
        if(inGame){
            g.drawImage(apple, appleX, appleY, null);
            for(int z = 0; z < dots; z++){
                if(z == 0){
                    g.drawImage(head, x[z], y[z], null);
                }else{
                    g.drawImage(ball, x[z], y[z], null);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }else{
            drawGameOver(g);
        }
    }
    private void drawGameOver(Graphics g){
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }
    private void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)){
            dots++;
            locateApple();
        }
    }
    private void checkCollision(){
        for(int z = dots; z > 0; z--){
            if((z > 4) && (x[0] == x[z]) && (y[0] == y[z])){
                inGame = false;
            }
        }
        if(y[0] >= B_HEIGHT){
            inGame = false;
        }
        if(y[0] < 0){
            inGame = false;
        }
        if(x[0] >= B_WIDTH){
            inGame = false;
        }
        if(x[0] < 0){
            inGame = false;
        }
        if(!inGame){
            timer.stop();
        }
    }
    private void move(){
        for(int z = dots; z > 0; z--){
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if(leftDirection){
            x[0] -= DOT_SIZE;
        }
        if(rightDirection){
            x[0] += DOT_SIZE;
        }
        if(upDirection){
            y[0] -= DOT_SIZE;
        }
        if(downDirection){
            y[0] += DOT_SIZE;
        }
    }
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(java.awt.event.KeyEvent e) {
            int key = e.getKeyCode();
            if((key == java.awt.event.KeyEvent.VK_LEFT) && (!rightDirection)){
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if((key == java.awt.event.KeyEvent.VK_RIGHT) && (!leftDirection)){
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if((key == java.awt.event.KeyEvent.VK_UP) && (!downDirection)){
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            if((key == java.awt.event.KeyEvent.VK_DOWN) && (!upDirection)){
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
