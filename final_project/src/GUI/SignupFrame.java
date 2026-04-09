//package GUI;
//
//import core.Portal;
//import graphicaluserinterface.AppTheme;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//
//public class SignupFrame extends JFrame {
//
//    private final Portal portal;
//
//    public SignupFrame(Portal portal) {
//        this.portal = portal;
//        initUI();
//    }
//
//    private void initUI() {
//        setTitle("DevSync — Signup");
//        setSize(600, 500);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        panel.setBackground(AppTheme.BG);
//
//        GridBagConstraints gc = new GridBagConstraints();
//        gc.insets = new Insets(10, 10, 10, 10);
//        gc.fill = GridBagConstraints.HORIZONTAL;
//
//        JLabel title = new JLabel("Create Account");
//        title.setFont(AppTheme.TITLE_FONT);
//        gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2;
//        panel.add(title, gc);
//
//        gc.gridwidth = 1;
//
//        // Username
//        gc.gridy++;
//        panel.add(new JLabel("Username:"), gc);
//        gc.gridx = 1;
//        JTextField usernameField = new JTextField(20);
//        panel.add(usernameField, gc);
//
//        // Password
//        gc.gridx = 0;
//        gc.gridy++;
//        panel.add(new JLabel("Password:"), gc);
//        gc.gridx = 1;
//        JPasswordField passwordField = new JPasswordField(20);
//        panel.add(passwordField, gc);
//
//        // Email (if database supports email)
//        gc.gridx = 0;
//        gc.gridy++;
//        panel.add(new JLabel("Email:"), gc);
//        gc.gridx = 1;
//        JTextField emailField = new JTextField(20);
//        panel.add(emailField, gc);
//
//        // Role Selection (Must match your LOGIN table)
//        gc.gridx = 0;
//        gc.gridy++;
//        panel.add(new JLabel("Role:"), gc);
//        gc.gridx = 1;
//
//        String[] roles = {
//                "Client (5)",
//                "Coder (6)",
//                "Project Manager (14)"
//        };
//        JComboBox<String> roleBox = new JComboBox<>(roles);
//        panel.add(roleBox, gc);
//
//        // Create account button
//        gc.gridx = 0;
//        gc.gridy++;
//        gc.gridwidth = 2;
//        JButton createBtn = new JButton("Create Account");
//        panel.add(createBtn, gc);
//
//        // Back button
//        gc.gridy++;
//        JButton back = new JButton("Back to Login");
//        panel.add(back, gc);
//
//        add(panel);
//
//        // --- Actions ---
//        createBtn.addActionListener(e -> {
//            String username = usernameField.getText().trim();
//            String password = new String(passwordField.getPassword());
//            String email = emailField.getText().trim();
//
//            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "All fields are required",
//                        "Missing Fields", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//
//            // Extract Role ID
//            String selectedRole = (String) roleBox.getSelectedItem();
//            int roleId = Integer.parseInt(selectedRole.replaceAll(".*\\((\\d+)\\).*", "$1"));
//
//            // Call Portal Signup
//            boolean status = portal.User_Signup(username, password, roleId);
//
//            if (status) {
//                JOptionPane.showMessageDialog(this,
//                        "Account created successfully!",
//                        "Success", JOptionPane.INFORMATION_MESSAGE);
//                dispose();
//                new LoginFrame(portal).setVisible(true);
//            } else {
//                JOptionPane.showMessageDialog(this,
//                        "Signup failed. Username may already exist.",
//                        "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        back.addActionListener(e -> {
//            dispose();
//            new LoginFrame(portal).setVisible(true);
//        });
//    }
//}
