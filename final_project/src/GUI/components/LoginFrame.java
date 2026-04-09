package GUI.components;

import GUI.Dashboards.*;
import GUI.components.ModernComponents.*;
import GUI.themes.ModernTheme;
import core.Portal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    private Portal portal;

    public LoginFrame(Portal portal) {
        this.portal = portal;
        initUI();
    }

    private void initUI() {
        setTitle("DevSync — Login");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(ModernTheme.DARK_BG);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(ModernTheme.DARK_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Left Panel (Info / Branding)
        JPanel leftPanel = new ModernCard(new Color(30, 41, 59), 25);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(400, 0));

        JPanel leftContent = new JPanel(new GridBagLayout());
        leftContent.setOpaque(false);

        JLabel logo = new JLabel("🚀 DevSync");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        logo.setForeground(Color.WHITE);

        JLabel tagline = new JLabel("<html><div style='text-align:center;color:#94a3b8;font-size:16px;'>"
                + "Professional Project Management<br/>For Modern Development Teams"
                + "</div></html>");

        JLabel features = new JLabel("<html><div style='text-align:left;color:#cbd5e1;font-size:14px;margin-top:40px;'>"
                + "🎯 17 Different Roles<br/>⚡ Real-time Collaboration<br/>📊 Advanced Analytics<br/>🔐 Enterprise Security"
                + "</div></html>");

        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.insets = new Insets(10, 0, 30, 0);
        leftGbc.gridy = 0; leftContent.add(logo, leftGbc);
        leftGbc.gridy = 1; leftContent.add(tagline, leftGbc);
        leftGbc.gridy = 2; leftContent.add(features, leftGbc);
        leftPanel.add(leftContent, BorderLayout.CENTER);

        // Right Panel (Login Form)
        ModernCard rightPanel = new ModernCard(ModernTheme.DARK_CARD, 20);
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(500, 0));

        GridBagConstraints rightGbc = new GridBagConstraints();
        rightGbc.gridwidth = GridBagConstraints.REMAINDER;
        rightGbc.fill = GridBagConstraints.HORIZONTAL;
        rightGbc.insets = new Insets(10, 40, 10, 40);

        JLabel welcome = new JLabel("Welcome Back");
        welcome.setFont(ModernTheme.TITLE_FONT);
        welcome.setForeground(ModernTheme.DARK_TEXT);
        rightGbc.gridy = 0; rightPanel.add(welcome, rightGbc);

        JLabel subtitle = new JLabel("Sign in to your account");
        subtitle.setFont(ModernTheme.SUBTITLE_FONT);
        subtitle.setForeground(ModernTheme.DARK_TEXT_SECONDARY);
        rightGbc.gridy = 1; rightPanel.add(subtitle, rightGbc);

        // Username
        rightGbc.gridy = 2; rightGbc.insets = new Insets(20, 40, 10, 40);
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(ModernTheme.DARK_TEXT);
        rightPanel.add(userLabel, rightGbc);

        rightGbc.gridy = 3;
        ModernTextField usernameField = new ModernTextField("Enter username");
        usernameField.setPreferredSize(new Dimension(350, 45));
        rightPanel.add(usernameField, rightGbc);

        // Password
        rightGbc.gridy = 4; rightGbc.insets = new Insets(15, 40, 10, 40);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(ModernTheme.DARK_TEXT);
        rightPanel.add(passLabel, rightGbc);

        rightGbc.gridy = 5;
        ModernPasswordField passwordField = new ModernPasswordField();
        passwordField.setPreferredSize(new Dimension(350, 45));
        rightPanel.add(passwordField, rightGbc);

        // Role ID
        rightGbc.gridy = 6; rightGbc.insets = new Insets(15, 40, 10, 40);
        JLabel roleLabel = new JLabel("Role ID (1-17):");
        roleLabel.setForeground(ModernTheme.DARK_TEXT);
        rightPanel.add(roleLabel, rightGbc);

        rightGbc.gridy = 7;
        ModernTextField roleField = new ModernTextField("Enter role ID");
        roleField.setPreferredSize(new Dimension(350, 45));
        rightPanel.add(roleField, rightGbc);

        // Login Button
        rightGbc.gridy = 8; rightGbc.insets = new Insets(30, 40, 10, 40);
        ModernButton loginBtn = new ModernButton("Sign In", ModernTheme.INFO);
        loginBtn.setPreferredSize(new Dimension(200, 50));
        rightPanel.add(loginBtn, rightGbc);

        // Register Link
        rightGbc.gridy = 9; rightGbc.insets = new Insets(20, 40, 40, 40);
        JLabel registerLabel = new JLabel("Don't have an account? ");
        registerLabel.setForeground(ModernTheme.DARK_TEXT_SECONDARY);
        JLabel registerLink = new JLabel("<html><u>Sign up now</u></html>");
        registerLink.setForeground(ModernTheme.INFO);
        registerLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        registerPanel.setOpaque(false);
        registerPanel.add(registerLabel);
        registerPanel.add(registerLink);
        rightPanel.add(registerPanel, rightGbc);

        // Add left and right to main panel
        gbc.gridx = 0; gbc.weightx = 0.4; mainPanel.add(leftPanel, gbc);
        gbc.gridx = 1; gbc.weightx = 0.6; mainPanel.add(rightPanel, gbc);

        add(mainPanel);

        // Event Handlers
        loginBtn.addActionListener(e -> performLogin(
                usernameField.getText().trim(),
                new String(passwordField.getPassword()),
                roleField.getText().trim()
        ));

        roleField.addActionListener(e -> performLogin(
                usernameField.getText().trim(),
                new String(passwordField.getPassword()),
                roleField.getText().trim()
        ));

        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new SignupFrame(portal).setVisible(true);
            }
        });
    }

    private void performLogin(String username, String password, String roleText) {
        if (username.isEmpty() || password.isEmpty() || roleText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all fields!",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int roleId;
        try {
            roleId = Integer.parseInt(roleText);
            if (roleId < 1 || roleId > 17) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Role ID must be a number between 1 and 17!",
                    "Invalid Role ID",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = portal.User_Login(username, password, roleId);

        if (!success) {
            JOptionPane.showMessageDialog(this,
                    "Invalid credentials! Please check your username, password, and role ID.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Login successful! Welcome " + username + " (" + ModernTheme.getRoleName(roleId) + ")",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();
        openDashboard(username, roleId);
    }


    private void openDashboard(String username, int roleId) {
        switch (roleId) {
            case 1: new AdminDashboard(username, roleId).setVisible(true); break;
            case 2: new UIUXDesignerDashboard(username, roleId).setVisible(true); break;
            case 3: new BackendEngineerDashboard(username, roleId).setVisible(true); break;
            case 4: new FrontendEngineerDashboard(username, roleId).setVisible(true); break;
            case 5: new ClientDashboard(username, roleId).setVisible(true); break;
            case 6: new CoderDashboard(username, roleId).setVisible(true); break;
            case 7: new FullstackEngineerDashboard(username, roleId).setVisible(true); break;
            case 8: new QAEngineerDashboard(username, roleId).setVisible(true); break;
            case 9: new DataEngineerDashboard(username, roleId).setVisible(true); break;
            case 10: new DataScientistDashboard(username, roleId).setVisible(true); break;
            case 11: new MLEngineerDashboard(username, roleId).setVisible(true); break;
            case 12: new MobileDeveloperDashboard(username, roleId).setVisible(true); break;
            case 13: new AutomationEngineerDashboard(username, roleId).setVisible(true); break;
            case 14: new ProjectManagerDashboard(username, roleId).setVisible(true); break;
            case 15: new SREDashboard(username, roleId).setVisible(true); break;
            case 16: new SoftwareArchitectDashboard(username, roleId).setVisible(true); break;
            case 17: new ProductManagerDashboard(username, roleId).setVisible(true); break;
            default: new ClientDashboard(username, roleId).setVisible(true);
        }
    }
}
