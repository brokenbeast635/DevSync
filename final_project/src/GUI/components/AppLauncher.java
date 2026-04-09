package GUI.components;

import GUI.components.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class AppLauncher {
    public static void main(String[] args) {
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Set global UI defaults for dark theme
            UIManager.put("Panel.background", GUI.themes.ModernTheme.DARK_BG);
            UIManager.put("Label.foreground", GUI.themes.ModernTheme.DARK_TEXT);
            UIManager.put("TextField.background", new Color(40, 51, 69));
            UIManager.put("TextField.foreground", GUI.themes.ModernTheme.DARK_TEXT);
            UIManager.put("TextField.caretForeground", GUI.themes.ModernTheme.DARK_TEXT);
            UIManager.put("TextArea.background", new Color(40, 51, 69));
            UIManager.put("TextArea.foreground", GUI.themes.ModernTheme.DARK_TEXT);
            UIManager.put("ComboBox.background", new Color(40, 51, 69));
            UIManager.put("ComboBox.foreground", GUI.themes.ModernTheme.DARK_TEXT);
            UIManager.put("Table.background", GUI.themes.ModernTheme.DARK_CARD);
            UIManager.put("Table.foreground", GUI.themes.ModernTheme.DARK_TEXT);
            UIManager.put("TableHeader.background", GUI.themes.ModernTheme.DARK_SIDEBAR);
            UIManager.put("TableHeader.foreground", GUI.themes.ModernTheme.DARK_TEXT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start the application
        SwingUtilities.invokeLater(() -> {
            // Initialize your Portal instance here
            core.Portal portal = null; // Replace with actual portal initialization

            // Start with login screen
            LoginFrame loginFrame = new LoginFrame(portal);
            loginFrame.setVisible(true);

            // Uncomment to test specific dashboards directly:
            // new AdminDashboard("admin", 1).setVisible(true);
            // new UIUXDesignerDashboard("designer", 2).setVisible(true);
            // new BackendEngineerDashboard("backend", 3).setVisible(true);
            // new FrontendEngineerDashboard("frontend", 4).setVisible(true);
            // new ClientDashboard("client", 5).setVisible(true);
            // new CoderDashboard("coder", 6).setVisible(true);
            // new FullstackEngineerDashboard("fullstack", 7).setVisible(true);
            // new QAEngineerDashboard("qa", 8).setVisible(true);
            // new DataEngineerDashboard("dataeng", 9).setVisible(true);
            // new DataScientistDashboard("datasci", 10).setVisible(true);
            // new MLEngineerDashboard("mleng", 11).setVisible(true);
            // new MobileDeveloperDashboard("mobile", 12).setVisible(true);
            // new AutomationEngineerDashboard("automation", 13).setVisible(true);
            // new ProjectManagerDashboard("pm", 14).setVisible(true);
            // new SREDashboard("sre", 15).setVisible(true);
            // new SoftwareArchitectDashboard("architect", 16).setVisible(true);
            // new ProductManagerDashboard("product", 17).setVisible(true);
        });
    }
}