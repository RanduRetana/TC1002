package Sprites;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class Application extends JFrame {
    @Serial
    private static final long serialVersionUID = 2195379760914381351L;
    public Application() {
        initUI();
    }
    private void initUI() {
        add(new Board());
        pack();
        setTitle("Sprites");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}
