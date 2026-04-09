package graphicaluserinterface;


import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    public SettingsPanel() {
        setLayout(new BorderLayout());
        setBackground(AppTheme.BG);

        JLabel lbl = new JLabel("Application Settings", SwingConstants.CENTER);
        lbl.setFont(AppTheme.TITLE_FONT);

        add(lbl, BorderLayout.CENTER);
    }
}
