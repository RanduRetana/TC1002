package board2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Board extends JPanel {
    private Image image;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        loadImage();
        int w = image.getWidth(this);
        int h = image.getHeight(this);
        setPreferredSize(new Dimension(w, h));
    }
    private void loadImage() {
        ImageIcon ii = new ImageIcon("board2/images/boca.jpg");
        image = ii.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
