//package GUI;
//
//import core.Portal;
//import graphicaluserinterface.AppTheme;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.*;
//
//public class LoginFrame extends JFrame {
//
//    private final Portal portal;
//
//    public LoginFrame(Portal portal) {
//        this.portal = portal;
//        initUI();
//    }
//
//    private void initUI() {
//        setTitle("DevSync — Login");
//        setSize(920, 560);
//        setMinimumSize(new Dimension(840, 520));
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//        getContentPane().setBackground(AppTheme.BG);
//
//        // LEFT PANEL
//        JPanel left = new JPanel();
//        left.setBackground(AppTheme.SIDEBAR);
//        left.setPreferredSize(new Dimension(360, 0));
//        left.setLayout(new GridBagLayout());
//        left.setBorder(new EmptyBorder(30, 25, 30, 25));
//
//        JLabel logo = new JLabel("DevSync");
//        logo.setFont(AppTheme.TITLE_FONT);
//        logo.setForeground(AppTheme.SIDEBAR_TEXT);
//
//        JLabel tagline = new JLabel(
//                "<html><center>Project Management Portal<br/><small>Modern • Secure</small></center></html>"
//        );
//        tagline.setFont(AppTheme.SUBTITLE_FONT);
//        tagline.setForeground(AppTheme.SIDEBAR_TEXT);
//
//        JPanel leftBox = new JPanel();
//        leftBox.setOpaque(false);
//        leftBox.setLayout(new BoxLayout(leftBox, BoxLayout.Y_AXIS));
//        leftBox.add(Box.createVerticalGlue());
//        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
//        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);
//        leftBox.add(logo);
//        leftBox.add(Box.createRigidArea(new Dimension(0, 14)));
//        leftBox.add(tagline);
//        leftBox.add(Box.createVerticalGlue());
//        left.add(leftBox);
//
//        // RIGHT PANEL
//        JPanel right = new JPanel(new GridBagLayout());
//        right.setBackground(AppTheme.BG);
//        right.setBorder(new EmptyBorder(40, 40, 40, 40));
//
//        JPanel card = new RoundedPanel(16, AppTheme.CARD);
//        card.setLayout(new GridBagLayout());
//        card.setPreferredSize(new Dimension(420, 440)); // bigger for role field
//
//        GridBagConstraints gc = new GridBagConstraints();
//        gc.insets = new Insets(10, 12, 10, 12);
//
//        // Welcome
//        gc.gridx = 0;
//        gc.gridy = 0;
//        gc.gridwidth = 2;
//        JLabel welcome = new JLabel("Welcome back");
//        welcome.setFont(AppTheme.TITLE_FONT);
//        welcome.setForeground(AppTheme.TEXT_PRIMARY);
//        card.add(welcome, gc);
//
//        // Subtitle
//        gc.gridy++;
//        JLabel sub = new JLabel("Sign in to continue");
//        sub.setFont(AppTheme.SUBTITLE_FONT);
//        sub.setForeground(AppTheme.TEXT_SECONDARY);
//        card.add(sub, gc);
//
//        gc.gridwidth = 1;
//        gc.gridy++;
//        gc.anchor = GridBagConstraints.WEST;
//
//        // Username
//        JLabel userLbl = new JLabel("Username");
//        userLbl.setForeground(AppTheme.TEXT_PRIMARY);
//        card.add(userLbl, gc);
//
//        gc.gridx = 1;
//        JTextField usernameField = new JTextField(20);
//        card.add(usernameField, gc);
//
//        // Password
//        gc.gridx = 0;
//        gc.gridy++;
//        JLabel passLbl = new JLabel("Password");
//        passLbl.setForeground(AppTheme.TEXT_PRIMARY);
//        card.add(passLbl, gc);
//
//        gc.gridx = 1;
//        JPasswordField passwordField = new JPasswordField(20);
//        card.add(passwordField, gc);
//
//        // ROLE FIELD
//        gc.gridx = 0;
//        gc.gridy++;
//        JLabel roleLbl = new JLabel("Role ID");
//        roleLbl.setForeground(AppTheme.TEXT_PRIMARY);
//        card.add(roleLbl, gc);
//
//        gc.gridx = 1;
//        JTextField roleField = new JTextField(20);
//        card.add(roleField, gc);
//
//        // Button
//        gc.gridx = 0;
//        gc.gridy++;
//        gc.gridwidth = 2;
//        JButton loginBtn = new JButton("Sign in");
//        loginBtn.setPreferredSize(new Dimension(260, 40));
//        loginBtn.setBackground(AppTheme.ACCENT);
//        loginBtn.setForeground(Color.white);
//        card.add(loginBtn, gc);
//
//        // Signup option
//        gc.gridy++;
//        JLabel register = new JLabel("<html><u>Create a new account</u></html>");
//        register.setForeground(new Color(70, 130, 180));
//        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        card.add(register, gc);
//
//        // Add to main
//        right.add(card);
//        add(left, BorderLayout.WEST);
//        add(right, BorderLayout.CENTER);
//
//        // --- Actions ---
//        loginBtn.addActionListener(e -> doLogin(
//                usernameField.getText(),
//                new String(passwordField.getPassword()),
//                roleField.getText()
//        ));
//
//        register.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                dispose();
//                new SignupFrame(portal).setVisible(true);
//            }
//        });
//    }
//
//    private void doLogin(String username, String password, String roleText) {
//
//        if (username.isEmpty() || password.isEmpty() || roleText.isEmpty()) {
//            JOptionPane.showMessageDialog(this,
//                    "Please enter username, password AND role ID",
//                    "Missing fields",
//                    JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        int roleId;
//        try {
//            roleId = Integer.parseInt(roleText);
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this,
//                    "Role ID must be a number",
//                    "Invalid Role",
//                    JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        // Check login from portal with role
//        boolean success = portal.User_Login(username, password, roleId);
//
//        if (!success) {
//            JOptionPane.showMessageDialog(this,
//                    "Invalid username, password, or role ID",
//                    "Login failed",
//                    JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        JOptionPane.showMessageDialog(this,
//                "Login successful!",
//                "Success",
//                JOptionPane.INFORMATION_MESSAGE);
//
//        SwingUtilities.invokeLater(() -> {
//            DashboardFrame dashboard = new DashboardFrame(username, roleId);
//            dashboard.setVisible(true);
//        });
//
//        this.dispose();
//    }
//}
