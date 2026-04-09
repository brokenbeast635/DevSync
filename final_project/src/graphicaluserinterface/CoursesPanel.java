package graphicaluserinterface;



import javax.swing.*;
import java.awt.*;

public class CoursesPanel extends JPanel {

    public CoursesPanel() {
        setLayout(new BorderLayout());
        setBackground(AppTheme.BG);

        JLabel lbl = new JLabel("Courses Management", SwingConstants.CENTER);
        lbl.setFont(AppTheme.TITLE_FONT);
        lbl.setForeground( AppTheme.ACCENT);

        add(lbl, BorderLayout.NORTH);
    }
}
