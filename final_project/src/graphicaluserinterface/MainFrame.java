package graphicaluserinterface;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    CardLayout card;
    JPanel cardPanel;

    public MainFrame() {

        setTitle("OOP Semester Project");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        card = new CardLayout();
        cardPanel = new JPanel(card);

        // Add Screens
        cardPanel.add(new DashboardPanel(), "dashboard");
        cardPanel.add(new StudentsPanel(), "students");
        cardPanel.add(new CoursesPanel(), "courses");
        cardPanel.add(new SettingsPanel(), "settings");

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(10, 1));
        sidebar.setBackground(AppTheme.SIDEBAR);

        String[] menuNames = {"Dashboard", "Students", "Courses", "Settings"};
        String[] menuKeys = {"dashboard", "students", "courses", "settings"};

        for (int i = 0; i < menuNames.length; i++) {
            JButton btn = new JButton(menuNames[i]);
            btn.setFocusPainted(false);
            btn.setForeground(AppTheme.SIDEBAR_TEXT);
            btn.setBackground(AppTheme.SIDEBAR);
            btn.setFont(AppTheme.NORMAL_FONT);
            String key = menuKeys[i];

            btn.addActionListener(e -> card.show(cardPanel, key));
            sidebar.add(btn);
        }

        add(sidebar, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);
    }
}

