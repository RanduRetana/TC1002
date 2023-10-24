package board;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Board extends JPanel {
    private static final long serialVersionUID = 2195379760914381351L;
    public Board() {
        super();
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        rh.put(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY
        );

        g2d.setRenderingHints(rh);

        Dimension size = this.getSize();
        double width = size.getWidth();
        double height = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);

        for (double angle = 0; angle < 360; angle += 5) {
            AffineTransform at = AffineTransform.getTranslateInstance(
                width / 2, height / 2
            );
            at.rotate(Math.toRadians(angle));
            g2d.draw(at.createTransformedShape(e));
        }
    }
}
