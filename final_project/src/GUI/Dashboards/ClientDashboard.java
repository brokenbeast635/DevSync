package GUI.Dashboards;

import GUI.components.BaseDashboard;
import GUI.components.ModernComponents;
import GUI.components.ModernComponents.*;
import GUI.themes.ModernTheme;
import Database.DBconn; // Import DBconn
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClientDashboard extends BaseDashboard {

    // 1. Add DBconn instance
    private final DBconn.DatabaseConnection dbConn = new DBconn.DatabaseConnection();

    public ClientDashboard(String username, int roleId) {
        super(username, roleId);
        // The username is stored in the BaseDashboard's 'loggedUser' field.
    }

    // --- Utility method for creating the user header panel ---
    private JPanel createUserPanel(String roleName, Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel roleLabel = new JLabel(roleName);
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(roleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return panel;
    }

    // ClientDashboard.java

    @Override
    protected void createSidebar() {
        JPanel sidebar = new ModernCard(ModernTheme.DARK_SIDEBAR, 0);
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20, 15, 20, 15));

        // FIX: Use the correct field access
        JPanel userPanel = createUserPanel("Client: " + loggedUser.User_Username, ModernTheme.CLIENT_COLOR);
        sidebar.add(userPanel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        String[] navItems = {"📊 Dashboard", "📁 My Projects", "💬 Messages",
                "📈 Reports", "💰 Billing", "👥 Team",
                "📅 Calendar", "⚙ Settings"};

        // Define unique string keys for the CardLayout
        String[] cardNames = {"Dashboard", "MyProjects", "Messages", "Reports", "Billing", "Team", "Calendar", "Settings"};

        for (int i = 0; i < navItems.length; i++) {
            String item = navItems[i];
            String cardName = cardNames[i];

            ModernButton btn = createSidebarButton(item.substring(2), item.substring(0, 2));
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);

            // --- ADD ACTION LISTENER ---
            btn.addActionListener(e -> {
                // This command tells the CardLayout to switch to the panel associated with 'cardName'
                contentLayout.show(mainContentContainer, cardName);
            });
            // ---------------------------

            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        sidebar.add(Box.createVerticalGlue());
        add(sidebar, BorderLayout.WEST);
    }

    @Override
    protected void createMainContent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ModernTheme.DARK_BG);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Client Dashboard");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);

        ModernButton newProjectBtn = new ModernButton("➕ New Project", ModernTheme.CLIENT_COLOR);
        newProjectBtn.setPreferredSize(new Dimension(150, 40));

        header.add(title, BorderLayout.WEST);
        header.add(newProjectBtn, BorderLayout.EAST);

        // --- Data Fetching Logic (Still using dummy data, but setup for real data) ---

        // This is where you would call dbConn.getClientStats(loggedUser.User_Username)
        String activeProjects = "4";
        String pendingTasks = "12";
        String budgetUsed = "$24.5K";

        JPanel statsGrid = new JPanel(new GridLayout(2, 3, 20, 20));
        statsGrid.setOpaque(false);
        statsGrid.setBorder(new EmptyBorder(30, 0, 30, 0));

        Object[][] stats = {
                {"Active Projects", activeProjects, "📁", ModernTheme.CLIENT_COLOR},
                {"Pending Tasks", pendingTasks, "⏳", ModernTheme.WARNING},
                {"Budget Used", budgetUsed, "💰", ModernTheme.SUCCESS},
                {"Messages", "8", "💬", ModernTheme.INFO},
                {"Team Members", "15", "👥", ModernTheme.CODER_COLOR},
                {"Satisfaction", "92%", "⭐", ModernTheme.SUCCESS}
        };

        for (Object[] stat : stats) {
            statsGrid.add(new ModernComponents.StatCard(
                    (String)stat[0],
                    (String)stat[1],
                    (String)stat[2],
                    (Color)stat[3]
            ));
        }

        // This is where you would call dbConn.getClientProjects(loggedUser.User_Username)
        String[][] projects = {
                {"E-Commerce Platform", "In Development", "$50,000", "75"},
                {"Mobile Banking App", "On Hold", "$35,000", "40"},
                {"Healthcare System", "Completed", "$120,000", "100"},
                {"Educational Platform", "Planning", "$25,000", "10"}
        };

        JPanel projectPanel = new ModernCard(ModernTheme.DARK_CARD, 15);
        projectPanel.setLayout(new BorderLayout());

        // FIX 2: Use loggedUser.User_Username
        JLabel projectTitle = new JLabel("Project Overview (Client: " + loggedUser.User_Username + ")");
        projectTitle.setFont(ModernTheme.SUBTITLE_FONT);
        projectTitle.setForeground(ModernTheme.DARK_TEXT);
        projectTitle.setBorder(new EmptyBorder(15, 20, 15, 20));

        JPanel projectGrid = new JPanel(new GridLayout(2, 2, 15, 15));
        projectGrid.setOpaque(false);
        projectGrid.setBorder(new EmptyBorder(0, 20, 20, 20));

        for (String[] project : projects) {
            projectGrid.add(createProjectCard(project[0], project[1], project[2], project[3] + "%"));
        }

        projectPanel.add(projectTitle, BorderLayout.NORTH);
        projectPanel.add(projectGrid, BorderLayout.CENTER);

        mainContentContainer.add(panel, "main");
    }

    private Color getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "in development":
            case "active":
                return ModernTheme.SUCCESS;
            case "on hold":
            case "pending":
                return ModernTheme.WARNING;
            case "completed":
                return ModernTheme.INFO;
            case "planning":
                return ModernTheme.ERROR;
            default:
                return ModernTheme.INFO;
        }
    }

    private JPanel createProjectCard(String name, String status, String budget, String progress) {
        JPanel card = new ModernCard(ModernTheme.DARK_CARD, 10);
        card.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(ModernTheme.BOLD_FONT);
        nameLabel.setForeground(ModernTheme.DARK_TEXT);

        JLabel statusLabel = new JLabel("Status: " + status);
        statusLabel.setFont(ModernTheme.NORMAL_FONT);
        statusLabel.setForeground(getStatusColor(status));

        JLabel budgetLabel = new JLabel("Budget: " + budget);
        budgetLabel.setFont(ModernTheme.NORMAL_FONT);
        budgetLabel.setForeground(ModernTheme.DARK_TEXT_SECONDARY);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(Integer.parseInt(progress.replace("%", "")));
        progressBar.setString(progress);
        progressBar.setStringPainted(true);
        progressBar.setForeground(ModernTheme.CLIENT_COLOR);
        progressBar.setBackground(new Color(51, 65, 85));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.add(nameLabel);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(statusLabel);
        content.add(Box.createRigidArea(new Dimension(0, 5)));
        content.add(budgetLabel);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(progressBar);

        card.add(content, BorderLayout.CENTER);

        ModernButton viewBtn = new ModernButton("View Details", ModernTheme.CLIENT_COLOR);
        card.add(viewBtn, BorderLayout.SOUTH);

        return card;
    }
}