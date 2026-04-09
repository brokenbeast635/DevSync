package graphicaluserinterface;


import javax.swing.*;
import java.awt.*;

public class StudentsPanel extends JPanel {

    public StudentsPanel() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBackground(AppTheme.BG);
        setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JLabel l1 = new JLabel("Student Name:");
        JTextField nameField = new JTextField();

        JLabel l2 = new JLabel("Roll No:");
        JTextField rollField = new JTextField();

        JButton addBtn = new JButton("Add Student");

        l1.setFont(AppTheme.NORMAL_FONT);
        l2.setFont(AppTheme.NORMAL_FONT);

        add(l1); add(nameField);
        add(l2); add(rollField);
        add(new JLabel());
        add(addBtn);

        addBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Student Added Successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }
}

