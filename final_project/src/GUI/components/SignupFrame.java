package GUI.components;

import GUI.components.ModernComponents.*;
import GUI.themes.ModernTheme;
import core.Portal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupFrame extends JFrame {

    private Portal portal;

    public SignupFrame(Portal portal) {
        this.portal = portal;
        initUI();
    }

    private void initUI() {
        setTitle("DevSync — Sign Up");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(ModernTheme.DARK_BG);
        setLayout(new BorderLayout());

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(ModernTheme.DARK_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Form Card
        ModernCard formCard = new ModernCard(ModernTheme.DARK_CARD, 20);
        formCard.setLayout(new GridBagLayout());
        formCard.setPreferredSize(new Dimension(600, 550));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 30, 10, 30);
        gbc.weightx = 1;

        // Title
        JLabel title = new JLabel("Create Account");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formCard.add(title, gbc);

        // Subtitle
        JLabel subtitle = new JLabel("Join DevSync today");
        subtitle.setFont(ModernTheme.SUBTITLE_FONT);
        subtitle.setForeground(ModernTheme.DARK_TEXT_SECONDARY);
        gbc.gridy = 1;
        formCard.add(subtitle, gbc);

        gbc.gridwidth = 1;

        // Username
        gbc.gridy = 2; gbc.gridx = 0;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(ModernTheme.DARK_TEXT);
        formCard.add(userLabel, gbc);

        gbc.gridx = 1;
        ModernTextField usernameField = new ModernTextField("Enter username");
        usernameField.setPreferredSize(new Dimension(300, 40));
        formCard.add(usernameField, gbc);

        // Password
        gbc.gridy = 3; gbc.gridx = 0;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(ModernTheme.DARK_TEXT);
        formCard.add(passLabel, gbc);

        gbc.gridx = 1;
        ModernPasswordField passwordField = new ModernPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 40));
        formCard.add(passwordField, gbc);

        // Confirm Password
        gbc.gridy = 4; gbc.gridx = 0;
        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setForeground(ModernTheme.DARK_TEXT);
        formCard.add(confirmLabel, gbc);

        gbc.gridx = 1;
        ModernPasswordField confirmField = new ModernPasswordField();
        confirmField.setPreferredSize(new Dimension(300, 40));
        formCard.add(confirmField, gbc);

        // Email
        gbc.gridy = 5; gbc.gridx = 0;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(ModernTheme.DARK_TEXT);
        formCard.add(emailLabel, gbc);

        gbc.gridx = 1;
        ModernTextField emailField = new ModernTextField("Enter email");
        emailField.setPreferredSize(new Dimension(300, 40));
        formCard.add(emailField, gbc);

        // Role Selection
        gbc.gridy = 6; gbc.gridx = 0;
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setForeground(ModernTheme.DARK_TEXT);
        formCard.add(roleLabel, gbc);

        gbc.gridx = 1;
        String[] roles = {
                "Admin (1)", "UI/UX Designer (2)", "Backend Engineer (3)",
                "Frontend Engineer (4)", "Client (5)", "Coder (6)",
                "Fullstack Engineer (7)", "QA Engineer (8)", "Data Engineer (9)",
                "Data Scientist (10)", "ML Engineer (11)", "Mobile Developer (12)",
                "Automation Test Engineer (13)", "Project Manager (14)",
                "Site Reliability Engineer (15)", "Software Architect (16)",
                "Product Manager (17)"
        };
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        roleCombo.setPreferredSize(new Dimension(300, 40));
        roleCombo.setBackground(new Color(40, 51, 69));
        roleCombo.setForeground(ModernTheme.DARK_TEXT);
        roleCombo.setFont(ModernTheme.NORMAL_FONT);
        formCard.add(roleCombo, gbc);

        // Create Account Button
        gbc.gridy = 7; gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 30, 10, 30);
        ModernButton createBtn = new ModernButton("Create Account", ModernTheme.SUCCESS);
        createBtn.setPreferredSize(new Dimension(200, 50));
        formCard.add(createBtn, gbc);

        // Back to Login
        gbc.gridy = 8;
        gbc.insets = new Insets(10, 30, 30, 30);
        JLabel loginLink = new JLabel("<html><u>Already have an account? Sign in</u></html>");
        loginLink.setForeground(ModernTheme.INFO);
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLink.setHorizontalAlignment(SwingConstants.CENTER);
        formCard.add(loginLink, gbc);

        mainPanel.add(formCard, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // Action Listeners
        createBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());
            String email = emailField.getText().trim();
            String selectedRole = (String) roleCombo.getSelectedItem();

            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "All fields are required!",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this,
                        "Passwords do not match!",
                        "Password Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Extract Role ID
            int roleId = Integer.parseInt(selectedRole.replaceAll(".*\\((\\d+)\\).*", "$1"));

            // Call database signup
            boolean success = portal.User_Signup(username, password, roleId);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Account created successfully!\nYou can now login.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoginFrame(portal).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Signup failed. Username may already exist.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginFrame(portal).setVisible(true);
            }
        });
    }
}
