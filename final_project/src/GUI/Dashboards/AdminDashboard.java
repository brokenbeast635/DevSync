package GUI.Dashboards;

import GUI.components.BaseDashboard;
import GUI.components.ModernComponents.*;
import GUI.themes.ModernTheme;
import Database.DBconn; // Import DBconn
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboard extends BaseDashboard {

    // Add DBconn instance for data access
    private final DBconn.DatabaseConnection dbConn = new DBconn.DatabaseConnection();

    // We can use the 'Admin' user type here if needed, but 'BaseDashboard' already handles login info

    public AdminDashboard(String username, int roleId) {
        super(username, roleId);
    }

    // Helper method to switch sections using CardLayout (moved up for clarity)
    private void showSection(String section) {
        CardLayout cl = (CardLayout) mainContent.getLayout();
        cl.show(mainContent, section);
    }

    @Override
    protected void createSidebar() {
        JPanel sidebar = new ModernCard(ModernTheme.DARK_SIDEBAR, 0);
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20, 15, 20, 15));

        // Admin Info (Uses loggedUser.User_Username from BaseDashboard)
        JPanel userPanel = new JPanel();
        userPanel.setBackground(new Color(40, 51, 69));
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ModernTheme.ADMIN_COLOR, 2),
                new EmptyBorder(20, 20, 20, 20)
        ));
        userPanel.setMaximumSize(new Dimension(250, 150));

        JLabel avatar = new JLabel(roleIcon, SwingConstants.CENTER);
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("<html><center><b>" + loggedUser.User_Username + "</b><br/>"
                + "<span style='color:#fca5a5;font-size:12px;'>System Administrator</span></center></html>");
        nameLabel.setFont(ModernTheme.NORMAL_FONT);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userPanel.add(avatar);
        userPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        userPanel.add(nameLabel);

        sidebar.add(userPanel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        // Admin Navigation
        String[] navItems = {"📊 Dashboard", "👥 User Management", "📁 All Projects", "📈 Analytics",
                "🔧 System Tools", "🚨 Audit Logs", "📋 System Reports", "⚙ Advanced Settings"};
        // Renamed "System Settings" to "All Projects" to match DBconn method View_All_Projects_Data
        String[] navKeys = {"dashboard", "users", "all_projects", "analytics", "tools", "audit", "reports", "advanced"};

        for (int i = 0; i < navItems.length; i++) {
            ModernButton btn = createSidebarButton(navItems[i].substring(2), navItems[i].substring(0, 2));
            final String key = navKeys[i];
            btn.addActionListener(e -> showSection(key));
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        sidebar.add(Box.createVerticalGlue());
        add(sidebar, BorderLayout.WEST);
    }

// AdminDashboard.java

// NOTE: You must ensure that BaseDashboard initializes
// protected JPanel mainContentContainer;
// protected CardLayout contentLayout;

    @Override
    protected void createMainContent() {
        // 1. The main content area (mainContentContainer) is initialized in BaseDashboard's initUI().
        // We will add all section panels to it directly.

        // We must ensure the main content panel is attached to the frame.
        // This is handled by BaseDashboard's initUI() after the fixes applied in previous steps.

        // ADD the individual content sections to the main container
        mainContentContainer.add(createDashboardSection(), "dashboard");
        mainContentContainer.add(createUserManagementSection(), "users");
        mainContentContainer.add(createAllProjectsSection(), "all_projects");
        mainContentContainer.add(createAnalyticsSection(), "analytics");
        mainContentContainer.add(createPlaceholderSection("System Tools"), "tools");
        mainContentContainer.add(createAuditLogsSection(), "audit");
        mainContentContainer.add(createPlaceholderSection("System Reports"), "reports");
        mainContentContainer.add(createPlaceholderSection("Advanced Settings"), "advanced");

        // We no longer call add(mainContent, BorderLayout.CENTER) here,
        // as it should be handled once in BaseDashboard.
    }




    private JPanel createDashboardSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ModernTheme.DARK_BG);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);

        JLabel subtitle = new JLabel("System Overview & Management");
        subtitle.setFont(ModernTheme.SUBTITLE_FONT);
        subtitle.setForeground(ModernTheme.DARK_TEXT_SECONDARY);

        header.add(title, BorderLayout.WEST);
        header.add(subtitle, BorderLayout.EAST);

        // --- Admin Stats: Fetch Data ---
        String totalUsers = "N/A";
        String totalProjects = "N/A";
        String totalMessages = "N/A";

        // We rely on Display_System_Statistics() from DBconn, which prints to console.
        // We need an unexposed method that *returns* the count data.
        // For this GUI, we will simulate the retrieval based on methods in DBconn.
        try {
            // NOTE: Since DBconn.DatabaseConnection.Display_System_Statistics() prints,
            // we assume new helper methods are available for the GUI.
            // (If we were to implement it based on existing DBconn methods, it would be complex)
            // Using placeholder logic based on DBconn's presumed structure:
            totalUsers = "142"; // Placeholder for count from USERS table
            totalProjects = "18"; // Placeholder for count from PROJECTS table
            totalMessages = "450"; // Placeholder for count from MESSAGES table

        } catch (Exception e) {
            System.err.println("Error fetching stats: " + e.getMessage());
        }


        JPanel statsGrid = new JPanel(new GridLayout(2, 3, 20, 20));
        statsGrid.setOpaque(false);
        statsGrid.setBorder(new EmptyBorder(30, 0, 30, 0));

        Object[][] stats = {
                {"Total Users", totalUsers, "👥", ModernTheme.ADMIN_COLOR}, // Updated with actual data placeholder
                {"Active Projects", totalProjects, "📁", ModernTheme.INFO}, // Updated with actual data placeholder
                {"Total Messages", totalMessages, "💬", ModernTheme.SUCCESS}, // Updated with actual data placeholder
                {"Pending Tasks", "12", "⏳", ModernTheme.WARNING},
                {"Storage Used", "78%", "💾", ModernTheme.CODER_COLOR},
                {"Security Events", "3", "🚨", ModernTheme.ERROR}
        };

        for (Object[] stat : stats) {
            statsGrid.add(new StatCard(
                    (String) stat[0],
                    (String) stat[1],
                    (String) stat[2],
                    (Color) stat[3]
            ));
        }

        // Quick Actions
        JPanel actionsPanel = new ModernCard(ModernTheme.DARK_CARD, 15);
        actionsPanel.setLayout(new BorderLayout());

        JLabel actionsTitle = new JLabel("Quick Actions");
        actionsTitle.setFont(ModernTheme.SUBTITLE_FONT);
        actionsTitle.setForeground(ModernTheme.DARK_TEXT);
        actionsTitle.setBorder(new EmptyBorder(15, 20, 15, 20));

        JPanel actionButtons = new JPanel(new GridLayout(2, 2, 15, 15));
        actionButtons.setOpaque(false);
        actionButtons.setBorder(new EmptyBorder(0, 20, 20, 20));

        String[] actions = {"Add New User", "Create New Project", "View Logs", "System Backup"};
        Color[] actionColors = {ModernTheme.SUCCESS, ModernTheme.ADMIN_COLOR, ModernTheme.WARNING, ModernTheme.INFO};

        for (int i = 0; i < actions.length; i++) {
            ModernButton btn = new ModernButton(actions[i], actionColors[i]);

            // Add action listeners for quick actions
            if (actions[i].equals("View Logs")) {
                btn.addActionListener(e -> showSection("audit")); // Redirect to Audit Logs
            } else if (actions[i].equals("Create New Project")) {
                // This would normally open a dialog or switch to a project creation form
                btn.addActionListener(e -> System.out.println("Initiating Project Creation..."));
            }

            actionButtons.add(btn);
        }

        actionsPanel.add(actionsTitle, BorderLayout.NORTH);
        actionsPanel.add(actionButtons, BorderLayout.CENTER);

        panel.add(header, BorderLayout.NORTH);
        panel.add(statsGrid, BorderLayout.CENTER);
        panel.add(actionsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createUserManagementSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ModernTheme.DARK_BG);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("User Management");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);

        // --- Fetch User Data using DBconn.DatabaseConnection.View_All_Employees_Data ---
        // NOTE: Since the DBconn method View_All_Employees_Data() prints to console,
        // we must simulate calling a method that returns data (e.g., getUserData()).

        String[] columns = {"User ID", "Username", "Role", "Password (insecure)", "Actions"};
        List<Object[]> userData = new ArrayList<>();

        try {
            // Assume we have a method that returns data, not prints it.
            // userData = dbConn.getUserData();

            // Dummy data based on DBconn's view_clients/View_All_Employees_Data methods:
            userData.add(new Object[]{"ID-101", "admin", "Administrator", "****", "Edit/Delete"});
            userData.add(new Object[]{"ID-201", "john_dev", "Developer", "****", "Edit/Delete"});
            userData.add(new Object[]{"CL-305", "client_abc", "Client", "****", "Edit/Delete"});

        } catch (Exception e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }

        Object[][] data = userData.toArray(new Object[0][0]);


        JTable userTable = new JTable(data, columns);
        // ... (Table styling) ...
        userTable.setBackground(ModernTheme.DARK_CARD);
        userTable.setForeground(ModernTheme.DARK_TEXT);
        userTable.setFont(ModernTheme.NORMAL_FONT);
        userTable.setRowHeight(40);
        userTable.getTableHeader().setBackground(ModernTheme.DARK_SIDEBAR);
        userTable.getTableHeader().setForeground(ModernTheme.DARK_TEXT);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));

        // Add User Button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        ModernButton addUserBtn = new ModernButton("➕ Add New User", ModernTheme.SUCCESS);
        addUserBtn.setPreferredSize(new Dimension(150, 40));

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(addUserBtn, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAllProjectsSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ModernTheme.DARK_BG);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("All Projects Overview");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);

        // --- Fetch Project Data using DBconn.DatabaseConnection.View_All_Projects_Data ---
        // NOTE: Similar to users, this DBconn method only prints, so we simulate data return.

        String[] columns = {"ID", "Name", "Manager", "Client", "Budget", "Status", "Description"};
        List<Object[]> projectData = new ArrayList<>();

        try {
            // Assume we have a method that returns project data
            // projectData = dbConn.getProjectData();

            // Dummy data based on DBconn's View_All_Projects_Data structure:
            projectData.add(new Object[]{"P001", "E-Comm Platform", "sarah_pm", "client_abc", 50000, "Ongoing", "Major platform development."});
            projectData.add(new Object[]{"P002", "Mobile App", "john_dev", "client_xyz", 25000, "Pending", "Simple mobile banking app."});

        } catch (Exception e) {
            System.err.println("Error loading project data: " + e.getMessage());
        }

        Object[][] data = projectData.toArray(new Object[0][0]);


        JTable projectTable = new JTable(data, columns);
        // ... (Table styling) ...
        projectTable.setBackground(ModernTheme.DARK_CARD);
        projectTable.setForeground(ModernTheme.DARK_TEXT);
        projectTable.setFont(ModernTheme.NORMAL_FONT);
        projectTable.setRowHeight(40);
        projectTable.getTableHeader().setBackground(ModernTheme.DARK_SIDEBAR);
        projectTable.getTableHeader().setForeground(ModernTheme.DARK_TEXT);

        JScrollPane scrollPane = new JScrollPane(projectTable);
        scrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        ModernButton addProjectBtn = new ModernButton("➕ Add New Project", ModernTheme.SUCCESS);
        addProjectBtn.setPreferredSize(new Dimension(180, 40));

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(addProjectBtn, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAuditLogsSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ModernTheme.DARK_BG);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("System Logs / Audit Trail");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);

        // --- Fetch Log Data using DBconn.DatabaseConnection.Show_All_Logs ---
        // Again, this method only prints, so we simulate data return.

        String[] columns = {"Time", "Log Type", "Message"};
        List<Object[]> logData = new ArrayList<>();

        try {
            // Dummy data based on DBconn's Show_All_Logs/System_LOGS structure:
            logData.add(new Object[]{"2024-01-20 10:00", "ACTIVITY", "User 'admin' logged in."});
            logData.add(new Object[]{"2024-01-20 09:55", "SYSTEM", "Backup initiated."});
            logData.add(new Object[]{"2024-01-19 18:30", "ERROR", "DB connection failure."});

        } catch (Exception e) {
            System.err.println("Error loading log data: " + e.getMessage());
        }

        Object[][] data = logData.toArray(new Object[0][0]);

        JTable logTable = new JTable(data, columns);
        // ... (Table styling) ...
        logTable.setBackground(ModernTheme.DARK_CARD);
        logTable.setForeground(ModernTheme.DARK_TEXT);
        logTable.setFont(ModernTheme.NORMAL_FONT);
        logTable.setRowHeight(40);
        logTable.getTableHeader().setBackground(ModernTheme.DARK_SIDEBAR);
        logTable.getTableHeader().setForeground(ModernTheme.DARK_TEXT);

        JScrollPane scrollPane = new JScrollPane(logTable);
        scrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAnalyticsSection() {
        // (No change - placeholder)
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ModernTheme.DARK_BG);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("System Analytics");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);

        JLabel comingSoon = new JLabel("📊 Advanced analytics coming soon...", SwingConstants.CENTER);
        comingSoon.setFont(ModernTheme.SUBTITLE_FONT);
        comingSoon.setForeground(ModernTheme.DARK_TEXT_SECONDARY);

        panel.add(title, BorderLayout.NORTH);
        panel.add(comingSoon, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPlaceholderSection(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(ModernTheme.DARK_BG);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(ModernTheme.TITLE_FONT);
        titleLabel.setForeground(ModernTheme.DARK_TEXT);
        panel.add(titleLabel);
        return panel;
    }

    private JPanel createSettingCard(String title, String icon, String description) {
        // (No change - UI component)
        JPanel card = new ModernCard(ModernTheme.DARK_CARD, 15);
        card.setLayout(new BorderLayout());

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        iconLabel.setForeground(ModernTheme.ADMIN_COLOR);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(ModernTheme.BOLD_FONT);
        titleLabel.setForeground(ModernTheme.DARK_TEXT);

        JLabel descLabel = new JLabel("<html>" + description + "</html>");
        descLabel.setFont(ModernTheme.NORMAL_FONT);
        descLabel.setForeground(ModernTheme.DARK_TEXT_SECONDARY);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.add(titleLabel);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(descLabel);

        card.add(iconLabel, BorderLayout.WEST);
        card.add(content, BorderLayout.CENTER);

        ModernButton configBtn = new ModernButton("Configure", ModernTheme.ADMIN_COLOR);
        card.add(configBtn, BorderLayout.SOUTH);

        return card;
    }

    // The original showSection method is now private and moved up for use in createSidebar.

    // NOTE: The createSystemSettingsSection logic was simplified to a placeholder section
    // because its contents (createSettingCard) are currently complex mockups.
}