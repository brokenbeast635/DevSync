import GUI.components.LoginFrame;
import GUI.components.SignupFrame;
import core.Portal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Apply system look-and-feel for best native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Portal initialises demo data and DB connection
        Portal portal = new Portal();

        SwingUtilities.invokeLater(() -> {
            LoginFrame lf = new LoginFrame(portal);
            lf.setVisible(true);
        });
    }
}
