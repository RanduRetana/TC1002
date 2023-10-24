package Animation01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private static final long serialVersionUID = -3435484034135435184L;
    private static final int B_WIDTH = 350;
    private static final int B_HEIGHT = 350;
    private static final int INITIAL_X = -40;
    private static final int INITIAL_Y = -40;
    private static final int DELAY = 15;

    private int dx, dy;

    private Image star;
    private Timer timer;
    private int x, y;

    public Board() {
        initBoard();
    }

    private void initBoard(){
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        dx = 1;
        dy = 1;

        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;

        timer = new Timer(DELAY, this);
        timer.start();
    }
    private void loadImage() {
        ImageIcon ii = new ImageIcon("Animation01/images/star.png");
        star = ii.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStar(g);
    }

    private void drawStar(Graphics g) {
        g.drawImage(star, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;

        // If the image hits the left or right edge, reverse the horizontal direction
        if (x <= 0 || x >= B_WIDTH - 250) {
            dx *= -1;
        }

        // If the image hits the top or bottom edge, reverse the vertical direction
        if ( y <= 0 || y >= B_HEIGHT - 250) {
            dy *= -1;
        }

        repaint();
    }
}