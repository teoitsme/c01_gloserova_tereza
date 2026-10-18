import controller.Controller;
import view.Window;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Zde vytváříme tvoji vlastní třídu Window z balíčku view
            Window window = new Window();

            // Předáme panel do controlleru
            new Controller(window.getPanel());

            // Zobrazíme okno
            window.setVisible(true);
        });
    }
}