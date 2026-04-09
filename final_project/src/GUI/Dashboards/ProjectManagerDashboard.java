package GUI.Dashboards;

import Database.DBconn;
import GUI.components.BaseDashboard;
import GUI.components.ModernComponents.*;
import GUI.themes.ModernTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ProjectManagerDashboard extends BaseDashboard {

    private final DBconn.DatabaseConnection db = new DBconn.DatabaseConnection();

    public ProjectManagerDashboard(String username, int roleId) {
        super(username, roleId);
    }

    private JPanel makeUserPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 51, 69));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ModernTheme.PM_COLOR, 2),
                new EmptyBorder(15, 15, 15, 15)));
        panel.setMaximumSize(new Dimension(250, 130));
        JLabel avatar = new JLabel(roleIcon, SwingConstants.CENTER);
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 32));
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel name = new JLabel("<html><center><b>" + loggedUser.User_Username + "</b><br/>"
                + "<span style='color:#86efac;font-size:11px;'>Project Manager</span></center></html>");
        name.setForeground(Color.WHITE);
        name.setFont(ModernTheme.NORMAL_FONT);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(avatar);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(name);
        return panel;
    }

    @Override
    protected void createSidebar() {
        JPanel sidebar = new ModernCard(ModernTheme.DARK_SIDEBAR, 0);
        sidebar.setPreferredSize(new Dimension(260, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20, 12, 20, 12));
        sidebar.add(makeUserPanel());
        sidebar.add(Box.createRigidArea(new Dimension(0, 25)));

        String[][] nav = {
            {"📊","Dashboard","dashboard"},
            {"📁","My Projects","projects"},
            {"✅","Assign Tasks","assign"},
            {"👥","Team","team"},
            {"📈","Progress","progress"},
            {"💬","Messages","messages"},
            {"📋","Reports","reports"},
            {"🔑","Change Password","password"}
        };
        for (String[] item : nav) {
            ModernButton btn = createSidebarButton(item[1], item[0]);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            final String card = item[2];
            btn.addActionListener(e -> contentLayout.show(mainContentContainer, card));
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        }
        sidebar.add(Box.createVerticalGlue());
        add(sidebar, BorderLayout.WEST);
    }

    @Override
    protected void createMainContent() {
        mainContentContainer.add(buildDashboard(), "dashboard");
        mainContentContainer.add(buildProjectsPanel(), "projects");
        mainContentContainer.add(buildAssignPanel(), "assign");
        mainContentContainer.add(buildTeamPanel(), "team");
        mainContentContainer.add(buildProgressPanel(), "progress");
        mainContentContainer.add(buildMessagesPanel(), "messages");
        mainContentContainer.add(buildReportsPanel(), "reports");
        mainContentContainer.add(buildPasswordPanel(), "password");
        contentLayout.show(mainContentContainer, "dashboard");
    }

    private JPanel buildDashboard() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ModernTheme.DARK_BG);
        p.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Project Manager Dashboard");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);
        p.add(title, BorderLayout.NORTH);

        JPanel stats = new JPanel(new GridLayout(2, 3, 20, 20));
        stats.setOpaque(false);
        stats.setBorder(new EmptyBorder(30, 0, 30, 0));
        Object[][] data = {
            {"Active Projects","6","📁",ModernTheme.PM_COLOR},
            {"Team Members","24","👥",ModernTheme.INFO},
            {"Tasks Due","18","⏰",ModernTheme.WARNING},
            {"On Schedule","78%","📊",ModernTheme.SUCCESS},
            {"Budget Used","$156K","💰",ModernTheme.CODER_COLOR},
            {"Open Issues","7","⚠",ModernTheme.ERROR}
        };
        for (Object[] d : data)
            stats.add(new StatCard((String)d[0],(String)d[1],(String)d[2],(Color)d[3]));
        p.add(stats, BorderLayout.CENTER);

        JPanel card = new ModernCard(ModernTheme.DARK_CARD, 12);
        card.setLayout(new BorderLayout());
        JLabel sub = new JLabel("Project Portfolio");
        sub.setFont(ModernTheme.SUBTITLE_FONT);
        sub.setForeground(ModernTheme.DARK_TEXT);
        sub.setBorder(new EmptyBorder(12,16,12,16));
        card.add(sub, BorderLayout.NORTH);
        String[] cols = {"Project","Status","Progress","Due Date"};
        Object[][] rows = {
            {"E-Commerce Redesign","Active","65%","2024-03-15"},
            {"Mobile Banking App","Active","42%","2024-04-01"},
            {"Healthcare Portal","Review","89%","2024-02-28"},
            {"Learning Platform","Planning","23%","2024-05-10"},
            {"CRM System","Active","71%","2024-03-20"}
        };
        JTable t = new JTable(rows, cols);
        styleTable(t);
        card.add(new JScrollPane(t), BorderLayout.CENTER);
        p.add(card, BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildProjectsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ModernTheme.DARK_BG);
        p.setBorder(new EmptyBorder(30, 30, 30, 30));
        JLabel title = new JLabel("My Projects");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);
        p.add(title, BorderLayout.NORTH);

        JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setBackground(new Color(30, 41, 59));
        output.setForeground(ModernTheme.DARK_TEXT);
        output.setFont(ModernTheme.NORMAL_FONT);
        output.setBorder(new EmptyBorder(10,10,10,10));

        ModernButton loadBtn = new ModernButton("Load My Projects", ModernTheme.PM_COLOR);
        loadBtn.setPreferredSize(new Dimension(180, 40));
        loadBtn.addActionListener(e -> {
            output.setText("Loading projects for: " + loggedUser.User_Username + "\n\n");
            try {
                java.util.List<String[]> projects = db.getProjectsForManager(loggedUser.User_Username);
                if (projects.isEmpty()) {
                    output.append("No projects found.\n");
                } else {
                    for (String[] proj : projects)
                        output.append("• [" + proj[0] + "] " + proj[1] + " — Status: " + proj[2] + "\n");
                }
            } catch (Exception ex) {
                output.append("Error loading: " + ex.getMessage() + "\n");
            }
        });
        p.add(loadBtn, BorderLayout.NORTH);
        p.add(new JScrollPane(output), BorderLayout.CENTER);
        return p;
    }

    private JPanel buildAssignPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ModernTheme.DARK_BG);
        p.setBorder(new EmptyBorder(30, 30, 30, 30));
        JLabel title = new JLabel("Assign Tasks");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);
        p.add(title, BorderLayout.NORTH);

        ModernCard form = new ModernCard(ModernTheme.DARK_CARD, 12);
        form.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,20,10,20);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridwidth = 2;

        JLabel sub = new JLabel("Create & Assign a Task");
        sub.setFont(ModernTheme.SUBTITLE_FONT);
        sub.setForeground(ModernTheme.DARK_TEXT);
        g.gridy = 0; form.add(sub, g);

        g.gridwidth = 1;
        String[] labels = {"Project ID:","Task Title:","Task Description:","Assign To (Username):"};
        JTextField[] fields = new JTextField[4];
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(ModernTheme.DARK_TEXT_SECONDARY);
            g.gridy = i+1; g.gridx = 0; form.add(lbl, g);
            fields[i] = new JTextField(20);
            fields[i].setBackground(new Color(40,51,69));
            fields[i].setForeground(ModernTheme.DARK_TEXT);
            fields[i].setCaretColor(Color.WHITE);
            g.gridx = 1; form.add(fields[i], g);
        }

        JLabel resultLbl = new JLabel(" ");
        resultLbl.setForeground(ModernTheme.SUCCESS);
        g.gridy = labels.length+1; g.gridx = 0; g.gridwidth = 2;
        form.add(resultLbl, g);

        ModernButton assignBtn = new ModernButton("Assign Task", ModernTheme.PM_COLOR);
        assignBtn.setPreferredSize(new Dimension(160, 44));
        g.gridy = labels.length+2;
        form.add(assignBtn, g);

        assignBtn.addActionListener(e -> {
            String pid = fields[0].getText().trim();
            String ttl = fields[1].getText().trim();
            String dsc = fields[2].getText().trim();
            String usr = fields[3].getText().trim();
            if (pid.isEmpty() || ttl.isEmpty() || usr.isEmpty()) {
                resultLbl.setForeground(ModernTheme.ERROR);
                resultLbl.setText("Please fill Project ID, Title, and Assignee.");
                return;
            }
            try {
                db.Create_Task_For_Manager(pid, ttl, dsc, usr, loggedUser.User_Username);
                resultLbl.setForeground(ModernTheme.SUCCESS);
                resultLbl.setText("Task assigned successfully!");
                for (JTextField f : fields) f.setText("");
            } catch (Exception ex) {
                resultLbl.setForeground(ModernTheme.ERROR);
                resultLbl.setText("Error: " + ex.getMessage());
            }
        });

        p.add(form, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildTeamPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ModernTheme.DARK_BG);
        p.setBorder(new EmptyBorder(30, 30, 30, 30));
        JLabel title = new JLabel("Team Members");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);
        p.add(title, BorderLayout.NORTH);

        JTextArea output = new JTextArea("Click 'Load Team' to see all registered users.\n");
        output.setEditable(false);
        output.setBackground(new Color(30,41,59));
        output.setForeground(ModernTheme.DARK_TEXT);
        output.setFont(ModernTheme.NORMAL_FONT);
        output.setBorder(new EmptyBorder(10,10,10,10));

        ModernButton loadBtn = new ModernButton("Load Team", ModernTheme.INFO);
        loadBtn.setPreferredSize(new Dimension(140, 40));
        loadBtn.addActionListener(e -> {
            output.setText("All Users:\n\n");
            try {
                java.util.List<String[]> users = db.getAllUsersForAdmin();
                for (String[] u : users)
                    output.append("• " + u[0] + " — Role: " + u[1] + "\n");
            } catch (Exception ex) {
                output.append("Error: " + ex.getMessage() + "\n");
            }
        });

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setOpaque(false);
        top.add(loadBtn);

        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.add(top, BorderLayout.NORTH);
        center.add(new JScrollPane(output), BorderLayout.CENTER);
        p.add(center, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildProgressPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ModernTheme.DARK_BG);
        p.setBorder(new EmptyBorder(30, 30, 30, 30));
        JLabel title = new JLabel("Project Progress");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);
        p.add(title, BorderLayout.NORTH);

        ModernCard form = new ModernCard(ModernTheme.DARK_CARD, 12);
        form.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));

        JLabel lbl = new JLabel("Project ID:");
        lbl.setForeground(ModernTheme.DARK_TEXT_SECONDARY);
        JTextField pidField = new JTextField(15);
        pidField.setBackground(new Color(40,51,69));
        pidField.setForeground(ModernTheme.DARK_TEXT);
        pidField.setCaretColor(Color.WHITE);

        JTextArea out = new JTextArea(10, 40);
        out.setEditable(false);
        out.setBackground(new Color(30,41,59));
        out.setForeground(ModernTheme.DARK_TEXT);
        out.setFont(ModernTheme.NORMAL_FONT);

        ModernButton checkBtn = new ModernButton("Check Progress", ModernTheme.PM_COLOR);
        checkBtn.setPreferredSize(new Dimension(160, 38));
        checkBtn.addActionListener(e -> {
            String pid = pidField.getText().trim();
            if (pid.isEmpty()) { out.setText("Enter a project ID."); return; }
            try {
                String status = db.getProjectStatus(pid);
                out.setText("Project: " + pid + "\nStatus: " + (status != null ? status : "Not found"));
            } catch (Exception ex) {
                out.setText("Error: " + ex.getMessage());
            }
        });

        form.add(lbl); form.add(pidField); form.add(checkBtn);

        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.add(form, BorderLayout.NORTH);
        center.add(new JScrollPane(out), BorderLayout.CENTER);
        p.add(center, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildMessagesPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ModernTheme.DARK_BG);
        p.setBorder(new EmptyBorder(30, 30, 30, 30));
        JLabel title = new JLabel("Send Message");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);
        p.add(title, BorderLayout.NORTH);

        ModernCard form = new ModernCard(ModernTheme.DARK_CARD, 12);
        form.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,20,10,20);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel toLbl = new JLabel("To (Username):");
        toLbl.setForeground(ModernTheme.DARK_TEXT_SECONDARY);
        JTextField toField = new JTextField(20);
        toField.setBackground(new Color(40,51,69));
        toField.setForeground(ModernTheme.DARK_TEXT);
        toField.setCaretColor(Color.WHITE);

        JLabel msgLbl = new JLabel("Message:");
        msgLbl.setForeground(ModernTheme.DARK_TEXT_SECONDARY);
        JTextArea msgArea = new JTextArea(4, 20);
        msgArea.setBackground(new Color(40,51,69));
        msgArea.setForeground(ModernTheme.DARK_TEXT);
        msgArea.setCaretColor(Color.WHITE);
        msgArea.setLineWrap(true);

        JLabel status = new JLabel(" ");
        status.setForeground(ModernTheme.SUCCESS);
        ModernButton sendBtn = new ModernButton("Send Message", ModernTheme.PM_COLOR);
        sendBtn.setPreferredSize(new Dimension(160, 40));

        g.gridx=0; g.gridy=0; form.add(toLbl,g);
        g.gridx=1; form.add(toField,g);
        g.gridx=0; g.gridy=1; form.add(msgLbl,g);
        g.gridx=1; form.add(new JScrollPane(msgArea),g);
        g.gridx=0; g.gridy=2; g.gridwidth=2; form.add(status,g);
        g.gridy=3; form.add(sendBtn,g);

        sendBtn.addActionListener(e -> {
            String to  = toField.getText().trim();
            String msg = msgArea.getText().trim();
            if (to.isEmpty() || msg.isEmpty()) {
                status.setForeground(ModernTheme.ERROR);
                status.setText("Fill in recipient and message.");
                return;
            }
            try {
                db.Send_Message(loggedUser.User_Username, to, msg);
                status.setForeground(ModernTheme.SUCCESS);
                status.setText("Message sent to " + to + "!");
                toField.setText(""); msgArea.setText("");
            } catch (Exception ex) {
                status.setForeground(ModernTheme.ERROR);
                status.setText("Error: " + ex.getMessage());
            }
        });
        p.add(form, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildReportsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ModernTheme.DARK_BG);
        p.setBorder(new EmptyBorder(30, 30, 30, 30));
        JLabel title = new JLabel("Weekly Reports");
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.DARK_TEXT);
        p.add(title, BorderLayout.NORTH);

        ModernCard form = new ModernCard(ModernTheme.DARK_CARD, 12);
        form.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,20,10,20);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel pidLbl = new JLabel("Project ID:");
        pidLbl.setForeground(ModernTheme.DARK_TEXT_SECONDARY);
        JTextField pidField = new JTextField(20);
        pidField.setBackground(new Color(40,51,69));
        pidField.setForeground(ModernTheme.DARK_TEXT);
        pidField.setCaretColor(Color.WHITE);

        JLabel rptLbl = new JLabel("Report Content:");
        rptLbl.setForeground(ModernTheme.DARK_TEXT_SECONDARY);
        JTextArea rptArea = new JTextArea(5, 20);
        rptArea.setBackground(new Color(40,51,69));
        rptArea.setForeground(ModernTheme.DARK_TEXT);
        rptArea.setCaretColor(Color.WHITE);
        rptArea.setLineWrap(true);

        JLabel status = new JLabel(" ");
        status.setForeground(ModernTheme.SUCCESS);
        ModernButton sendBtn = new ModernButton("Submit Report", ModernTheme.PM_COLOR);
        sendBtn.setPreferredSize(new Dimension(160, 40));

        g.gridx=0;g.gridy=0;form.add(pidLbl,g);
        g.gridx=1;form.add(pidField,g);
        g.gridx=0;g.gridy=1;form.add(rptLbl,g);
        g.gridx=1;form.add(new JScrollPane(rptArea),g);
        g.gridx=0;g.gridy=2;g.gridwidth=2;form.add(status,g);
        g.gridy=3;form.add(sendBtn,g);

        sendBtn.addActionListener(e -> {
            String pid = pidField.getText().trim();
            String rpt = rptArea.getText().trim();
            if (pid.isEmpty() || rpt.isEmpty()) {
                status.setForeground(ModernTheme.ERROR);
                status.setText("Fill in all fields.");
                return;
            }
            try {
                db.Send_Message(loggedUser.User_Username, "admin",
                        "WEEKLY REPORT [" + pid + "]: " + rpt);
                status.setForeground(ModernTheme.SUCCESS);
                status.setText("Report sent to admin!");
                pidField.setText(""); rptArea.setText("");
            } catch (Exception ex) {
                status.setForeground(ModernTheme.ERROR);
                status.setText("Error: " + ex.getMessage());
            }
        });
        p.add(form, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildPasswordPanel() {
        return buildPasswordChangePanel(db);
    }

    private void styleTable(JTable t) {
        t.setBackground(ModernTheme.DARK_CARD);
        t.setForeground(ModernTheme.DARK_TEXT);
        t.setFont(ModernTheme.NORMAL_FONT);
        t.setRowHeight(32);
        t.setShowGrid(false);
        t.getTableHeader().setBackground(ModernTheme.DARK_SIDEBAR);
        t.getTableHeader().setForeground(ModernTheme.DARK_TEXT);
        t.getTableHeader().setFont(ModernTheme.BOLD_FONT);
    }
}
