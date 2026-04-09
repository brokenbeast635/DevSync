package GUI.components;

import Chatbot.FloatChatbot;
import GUI.components.LoginFrame;
import GUI.components.ModernComponents.ModernButton;
import GUI.themes.ModernTheme;
import user.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseDashboard extends JFrame {
    protected user loggedUser;
    protected Color roleColor;
    protected String roleIcon;
    protected JPanel mainContent;
    protected CardLayout contentLayout;
    // BaseDashboard.java
// ...
    protected JPanel mainContentContainer; // This panel holds all the content cards
//    protected CardLayout contentLayout;
// ...

    // ✅ Constructor with only username and roleId
    public BaseDashboard(String username, int roleId) {
        this.loggedUser = new user(username, roleId);
        this.roleColor = ModernTheme.getRoleColor(roleId);
        this.roleIcon = ModernTheme.getRoleIcon(roleId);

        // Initialize the dashboard UI
        initUI();

        // Initialize ChatGPT API (hardcoded API key or read from env/config)
        String apiKey = System.getenv("OPENAI_API_KEY") != null ? System.getenv("OPENAI_API_KEY") : "your-openai-api-key-here"; // <-- replace with your API key
        FloatChatbot.initialize(apiKey);

        // Add floating ChatGPT button
        addFloatingChatbot();

        setVisible(true);
    }

    // BaseDashboard.java (Changes in initUI method)

    protected void initUI() {
        setTitle("DevSync — " + ModernTheme.getRoleName(loggedUser.User_Role) + " Dashboard");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(ModernTheme.DARK_BG);

        // Initialize CardLayout and Container
        contentLayout = new CardLayout();
        mainContentContainer = new JPanel(contentLayout);
        mainContentContainer.setBackground(ModernTheme.DARK_BG);

        createTopBar();
        createSidebar();
        createMainContent(); // This will add the default dashboard content to mainContentContainer

        // Add the CardLayout container to the center of the Frame
        add(mainContentContainer, BorderLayout.CENTER);
    }
    protected void createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(ModernTheme.DARK_SIDEBAR);
        topBar.setPreferredSize(new Dimension(0, 70));
        topBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Left side
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        leftPanel.setOpaque(false);
        JLabel logo = new JLabel("🚀 DevSync");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);
        JLabel welcome = new JLabel("Welcome, " + loggedUser.User_Username);
        welcome.setFont(ModernTheme.BOLD_FONT);
        welcome.setForeground(ModernTheme.DARK_TEXT);
        leftPanel.add(logo);
        leftPanel.add(Box.createHorizontalStrut(20));
        leftPanel.add(welcome);

        // Right side
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setOpaque(false);
        ModernButton logoutBtn = new ModernButton("Logout", ModernTheme.ERROR);
        logoutBtn.setPreferredSize(new Dimension(100, 40));
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(BaseDashboard.this,
                        "Are you sure you want to logout?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    SwingUtilities.invokeLater(() -> new LoginFrame(null).setVisible(true));
                }
            }
        });
        rightPanel.add(logoutBtn);

        topBar.add(leftPanel, BorderLayout.WEST);
        topBar.add(rightPanel, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);
    }

    protected abstract void createSidebar();
    protected abstract void createMainContent();

    protected ModernButton createSidebarButton(String text, String icon) {
        ModernButton btn = new ModernButton(icon + " " + text, roleColor);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setPreferredSize(new Dimension(220, 45));
        btn.setMaximumSize(new Dimension(220, 45));
        return btn;
    }

    private void addFloatingChatbot() {
        JButton chatBtn = new JButton("💬 Chat");
        chatBtn.setBackground(new Color(0, 122, 255));
        chatBtn.setForeground(Color.WHITE);
        chatBtn.setFocusPainted(false);
        chatBtn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Use glass pane for floating button
        JComponent glass = (JComponent) getGlassPane();
        glass.setLayout(null);
        glass.setVisible(true);
        glass.add(chatBtn);

        // Initial position
        chatBtn.setBounds(getWidth() - 200, getHeight() - 140, 150, 45);

        // Open ChatGPT window on click
        chatBtn.addActionListener(e -> FloatChatbot.openChatWindow(this));

        // Reposition on resize
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                chatBtn.setBounds(getWidth() - 200, getHeight() - 140, 150, 45);
            }
        });

        glass.revalidate();
        glass.repaint();
    }

    // Common method to create a task card
    protected JPanel createTaskCard(String taskId, String title, String status, int progress, Color statusColor) {
        JPanel card = new ModernComponents.ModernCard(ModernTheme.DARK_CARD, 10);
        card.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("<html><b>" + taskId + ":</b> " + title + "</html>");
        titleLabel.setFont(ModernTheme.BOLD_FONT);
        titleLabel.setForeground(ModernTheme.DARK_TEXT);

        JLabel statusLabel = new JLabel("Status: " + status);
        statusLabel.setFont(ModernTheme.NORMAL_FONT);
        statusLabel.setForeground(statusColor);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(progress);
        progressBar.setString(progress + "%");
        progressBar.setStringPainted(true);
        progressBar.setForeground(statusColor);
        progressBar.setBackground(new Color(51, 65, 85));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.add(titleLabel);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(statusLabel);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(progressBar);

        card.add(content, BorderLayout.CENTER);
        return card;
    }
    /** Shared "Change Password" panel reused by every dashboard */
    protected JPanel buildPasswordChangePanel(Database.DBconn.DatabaseConnection db) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(GUI.themes.ModernTheme.DARK_BG);
        p.setBorder(new javax.swing.border.EmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Change Password");
        title.setFont(GUI.themes.ModernTheme.TITLE_FONT);
        title.setForeground(GUI.themes.ModernTheme.DARK_TEXT);
        p.add(title, java.awt.BorderLayout.NORTH);

        GUI.components.ModernComponents.ModernCard form =
                new GUI.components.ModernComponents.ModernCard(GUI.themes.ModernTheme.DARK_CARD, 12);
        form.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints g = new java.awt.GridBagConstraints();
        g.insets = new java.awt.Insets(12, 24, 12, 24);
        g.fill = java.awt.GridBagConstraints.HORIZONTAL;

        String[] labels = {"Current Password:", "New Password:", "Confirm New Password:"};
        JPasswordField[] fields = new JPasswordField[3];
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(GUI.themes.ModernTheme.DARK_TEXT_SECONDARY);
            g.gridy = i; g.gridx = 0; form.add(lbl, g);
            fields[i] = new JPasswordField(20);
            fields[i].setBackground(new java.awt.Color(40, 51, 69));
            fields[i].setForeground(GUI.themes.ModernTheme.DARK_TEXT);
            fields[i].setCaretColor(java.awt.Color.WHITE);
            g.gridx = 1; form.add(fields[i], g);
        }

        JLabel status = new JLabel(" ");
        status.setForeground(GUI.themes.ModernTheme.SUCCESS);
        g.gridy = 3; g.gridx = 0; g.gridwidth = 2; form.add(status, g);

        GUI.components.ModernComponents.ModernButton changeBtn =
                new GUI.components.ModernComponents.ModernButton("Change Password", roleColor);
        changeBtn.setPreferredSize(new java.awt.Dimension(180, 44));
        g.gridy = 4; form.add(changeBtn, g);

        changeBtn.addActionListener(e -> {
            String oldPw  = new String(fields[0].getPassword());
            String newPw  = new String(fields[1].getPassword());
            String confPw = new String(fields[2].getPassword());
            if (oldPw.isEmpty() || newPw.isEmpty() || confPw.isEmpty()) {
                status.setForeground(GUI.themes.ModernTheme.ERROR);
                status.setText("All fields are required.");
                return;
            }
            if (!newPw.equals(confPw)) {
                status.setForeground(GUI.themes.ModernTheme.ERROR);
                status.setText("New passwords do not match.");
                return;
            }
            if (newPw.length() < 6) {
                status.setForeground(GUI.themes.ModernTheme.ERROR);
                status.setText("Password must be at least 6 characters.");
                return;
            }
            boolean ok = db.changePassword(loggedUser.User_Username, oldPw, newPw);
            if (ok) {
                status.setForeground(GUI.themes.ModernTheme.SUCCESS);
                status.setText("Password changed successfully!");
                for (JPasswordField f : fields) f.setText("");
            } else {
                status.setForeground(GUI.themes.ModernTheme.ERROR);
                status.setText("Current password is incorrect.");
            }
        });

        p.add(form, java.awt.BorderLayout.CENTER);
        return p;
    }

}