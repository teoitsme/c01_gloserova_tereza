package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private BufferedImage img;
    private String hintMode = "LINE";

    public Panel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void setImage(BufferedImage img) {
        this.img = img;
    }

    // Změna textu nápovědy (volá se při přepnutí klávesami L / P)
    public void setHintMode(String mode) {
        this.hintMode = mode;
    }

    // Hlavní vykreslovací metoda frameworku Swing (volá se při každém repaint())
    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, null);
        }

        // Vykreslení nápovědy přes obrázek do levého horního rohu
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Aktivní režim: " + hintMode, 15, 25);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("[L] Úsečky | [P] Polygon | [C] Smazat", 15, 45);
    }
}
