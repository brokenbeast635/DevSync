package graphicaluserinterface;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(AppTheme.BG);

        JLabel title = new JLabel("Welcome to OOP Project Dashboard", SwingConstants.CENTER);
        title.setFont(AppTheme.TITLE_FONT);
        title.setForeground(AppTheme.ACCENT);

        add(title, BorderLayout.CENTER);
    }
}

