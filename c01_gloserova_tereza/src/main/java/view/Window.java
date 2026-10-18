package view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    // vlastní kreslící plocha
    private final Panel panel;

    public Window() {
        setTitle("PGRF1");
        // Zajištění, že se program ukončí po zavření okna křížkem
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new Panel();
        add(panel, BorderLayout.CENTER);
        pack();
        // Vycentrování okna doprostřed obrazovky
        setLocationRelativeTo(null);
    }

    public Panel getPanel() {
        return panel;
    }
}
