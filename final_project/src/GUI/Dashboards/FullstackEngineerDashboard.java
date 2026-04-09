package GUI.Dashboards;

import Database.DBconn;
import GUI.components.BaseDashboard;
import GUI.components.ModernComponents.*;
import GUI.themes.ModernTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class FullstackEngineerDashboard extends BaseDashboard {

    private final DBconn.DatabaseConnection db = new DBconn.DatabaseConnection();

    public FullstackEngineerDashboard(String username, int roleId) { super(username, roleId); }


    private JPanel makeUserPanel(String role, Color accent) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 51, 69));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accent, 2),
                new EmptyBorder(15, 15, 15, 15)));
        panel.setMaximumSize(new Dimension(250, 130));
        JLabel avatar = new JLabel(roleIcon, SwingConstants.CENTER);
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel name = new JLabel("<html><center><b>" + loggedUser.User_Username + "</b><br/>"
                + "<span style='color:" + hex(accent) + ";font-size:11px;'>" + role + "</span></center></html>");
        name.setForeground(Color.WHITE);
        name.setFont(ModernTheme.NORMAL_FONT);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(avatar); panel.add(Box.createRigidArea(new Dimension(0,8))); panel.add(name);
        return panel;
    }
    private String hex(Color c){return String.format("#%02x%02x%02x",c.getRed(),c.getGreen(),c.getBlue());}
    private void styleTable(JTable t) {
        t.setBackground(ModernTheme.DARK_CARD); t.setForeground(ModernTheme.DARK_TEXT);
        t.setFont(ModernTheme.NORMAL_FONT); t.setRowHeight(32); t.setShowGrid(false);
        t.getTableHeader().setBackground(ModernTheme.DARK_SIDEBAR);
        t.getTableHeader().setForeground(ModernTheme.DARK_TEXT);
        t.getTableHeader().setFont(ModernTheme.BOLD_FONT);
    }


    @Override
    protected void createSidebar() {
        JPanel sidebar = new ModernCard(ModernTheme.DARK_SIDEBAR, 0);
        sidebar.setPreferredSize(new Dimension(260, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20, 12, 20, 12));
        sidebar.add(makeUserPanel("Fullstack Engineer", ModernTheme.FULLSTACK_COLOR));
        sidebar.add(Box.createRigidArea(new Dimension(0, 25)));

        ModernButton btn_dashboard = createSidebarButton("Dashboard", "🔧");
        btn_dashboard.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn_dashboard.addActionListener(e -> contentLayout.show(mainContentContainer, "dashboard"));
        sidebar.add(btn_dashboard);
        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        ModernButton btn_tasks = createSidebarButton("My Tasks", "✅");
        btn_tasks.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn_tasks.addActionListener(e -> contentLayout.show(mainContentContainer, "tasks"));
        sidebar.add(btn_tasks);
        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        ModernButton btn_feature = createSidebarButton("Add Feature", "🌐");
        btn_feature.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn_feature.addActionListener(e -> contentLayout.show(mainContentContainer, "feature"));
        sidebar.add(btn_feature);
        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        ModernButton btn_tech = createSidebarButton("Technologies", "⚙");
        btn_tech.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn_tech.addActionListener(e -> contentLayout.show(mainContentContainer, "tech"));
        sidebar.add(btn_tech);
        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        ModernButton btn_messages = createSidebarButton("Messages", "💬");
        btn_messages.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn_messages.addActionListener(e -> contentLayout.show(mainContentContainer, "messages"));
        sidebar.add(btn_messages);
        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        ModernButton btn_password = createSidebarButton("Password", "🔑");
        btn_password.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn_password.addActionListener(e -> contentLayout.show(mainContentContainer, "password"));
        sidebar.add(btn_password);
        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        sidebar.add(Box.createVerticalGlue());
        add(sidebar, BorderLayout.WEST);
    }

    @Override
    protected void createMainContent() {
        mainContentContainer.add(build_dashboard(), "dashboard");
        mainContentContainer.add(build_tasks(), "tasks");
        mainContentContainer.add(build_feature(), "feature");
        mainContentContainer.add(build_tech(), "tech");
        mainContentContainer.add(build_messages(), "messages");
        mainContentContainer.add(build_password(), "password");
        contentLayout.show(mainContentContainer, "dashboard");
    }


    private JPanel build_dashboard() {
        JPanel p = new JPanel(new BorderLayout()); p.setBackground(ModernTheme.DARK_BG);
        p.setBorder(new EmptyBorder(30,30,30,30));
        JLabel ttl = new JLabel("Fullstack Engineer Dashboard"); ttl.setFont(ModernTheme.TITLE_FONT); ttl.setForeground(ModernTheme.DARK_TEXT);
        p.add(ttl, BorderLayout.NORTH);
        JPanel stats = new JPanel(new GridLayout(2,3,20,20)); stats.setOpaque(false); stats.setBorder(new EmptyBorder(25,0,25,0));

        stats.add(new StatCard("Features","15","🔧",ModernTheme.FULLSTACK_COLOR));
        stats.add(new StatCard("APIs","32","🔌",ModernTheme.INFO));
        stats.add(new StatCard("DB Tables","18","🗄",ModernTheme.SUCCESS));
        stats.add(new StatCard("PR Open","4","📋",ModernTheme.WARNING));
        stats.add(new StatCard("Tests Pass","94%","✅",ModernTheme.SUCCESS));
        stats.add(new StatCard("Tech Debt","Low","⚠",ModernTheme.CODER_COLOR));
        p.add(stats, BorderLayout.CENTER); return p;
    }
    private JPanel build_tasks() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(ModernTheme.DARK_BG);
        p.setBorder(new EmptyBorder(30,30,30,30));
        JLabel ttl = new JLabel("My Tasks"); ttl.setFont(ModernTheme.TITLE_FONT); ttl.setForeground(ModernTheme.DARK_TEXT);
        p.add(ttl, BorderLayout.NORTH);
        String[] cols = {"Task","Project","Status"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(cols,0);
        JTable table = new JTable(model); styleTable(table);
        ModernButton load = new ModernButton("Load My Tasks", ModernTheme.FULLSTACK_COLOR);
        load.setPreferredSize(new Dimension(160,38));
        load.addActionListener(e -> {
            model.setRowCount(0);
            try {
                java.util.List<String[]> tasks = db.getTasksForUser(loggedUser.User_Username);
                if(tasks.isEmpty()) model.addRow(new Object[]{" No tasks assigned","",""});
                else for(String[] t:tasks) model.addRow(new Object[]{t[1],t[3],t[2]});
            } catch(Exception ex){ model.addRow(new Object[]{" Error: "+ex.getMessage(),"",""}); }
        });
        JPanel top=new JPanel(new FlowLayout(FlowLayout.LEFT)); top.setOpaque(false); top.add(load);
        JPanel c=new JPanel(new BorderLayout()); c.setOpaque(false);
        c.add(top,BorderLayout.NORTH); c.add(new JScrollPane(table),BorderLayout.CENTER);
        p.add(c,BorderLayout.CENTER); return p;
    }
    private JPanel build_feature() {
        JPanel p = new JPanel(new BorderLayout()); p.setBackground(ModernTheme.DARK_BG); p.setBorder(new EmptyBorder(30,30,30,30));
        JLabel ttl=new JLabel("Add Fullstack Feature"); ttl.setFont(ModernTheme.TITLE_FONT); ttl.setForeground(ModernTheme.DARK_TEXT); p.add(ttl,BorderLayout.NORTH);
        ModernCard form=new ModernCard(ModernTheme.DARK_CARD,12); form.setLayout(new GridBagLayout());
        GridBagConstraints g=new GridBagConstraints(); g.insets=new Insets(10,20,10,20); g.fill=GridBagConstraints.HORIZONTAL;

        JTextField featName = new JTextField(20); featName.setBackground(new Color(40,51,69)); featName.setForeground(ModernTheme.DARK_TEXT); featName.setCaretColor(Color.WHITE);
        JTextField featDesc = new JTextField(20); featDesc.setBackground(new Color(40,51,69)); featDesc.setForeground(ModernTheme.DARK_TEXT); featDesc.setCaretColor(Color.WHITE);

        g.gridy=0; g.gridx=0; JLabel lbl_featName=new JLabel("Feature Name:"); lbl_featName.setForeground(ModernTheme.DARK_TEXT_SECONDARY); form.add(lbl_featName,g); g.gridx=1; form.add(featName,g);
        g.gridy=1; g.gridx=0; JLabel lbl_featDesc=new JLabel("Description:"); lbl_featDesc.setForeground(ModernTheme.DARK_TEXT_SECONDARY); form.add(lbl_featDesc,g); g.gridx=1; form.add(featDesc,g);
        JLabel status=new JLabel(" "); status.setForeground(ModernTheme.SUCCESS);
        g.gridy=2; g.gridx=0; g.gridwidth=2; form.add(status,g);
        ModernButton btn=new ModernButton("Save", ModernTheme.FULLSTACK_COLOR); btn.setPreferredSize(new Dimension(140,40));
        g.gridy=3; form.add(btn,g);
        btn.addActionListener(e->{
            if(featName.getText().trim().isEmpty() || featDesc.getText().trim().isEmpty()){ status.setForeground(ModernTheme.ERROR); status.setText("Fill all required fields."); return; }
            try { db.Save_Fullstack_Task(loggedUser.User_Username, featName+": "+featDesc,(featName.getText().trim(), featDesc.getText().trim()); status.setForeground(ModernTheme.SUCCESS); status.setText("Saved!"); featName.setText(""); featDesc.setText(""); }
            catch(Exception ex){ status.setForeground(ModernTheme.ERROR); status.setText("Error: "+ex.getMessage()); }
        });
        p.add(form,BorderLayout.CENTER); return p;
    }
    private JPanel build_tech() {
        JPanel p = new JPanel(new BorderLayout()); p.setBackground(ModernTheme.DARK_BG); p.setBorder(new EmptyBorder(30,30,30,30));
        JLabel ttl=new JLabel("Add Technology"); ttl.setFont(ModernTheme.TITLE_FONT); ttl.setForeground(ModernTheme.DARK_TEXT); p.add(ttl,BorderLayout.NORTH);
        ModernCard form=new ModernCard(ModernTheme.DARK_CARD,12); form.setLayout(new GridBagLayout());
        GridBagConstraints g=new GridBagConstraints(); g.insets=new Insets(10,20,10,20); g.fill=GridBagConstraints.HORIZONTAL;

        JTextField techName = new JTextField(20); techName.setBackground(new Color(40,51,69)); techName.setForeground(ModernTheme.DARK_TEXT); techName.setCaretColor(Color.WHITE);

        g.gridy=0; g.gridx=0; JLabel lbl_techName=new JLabel("Technology:"); lbl_techName.setForeground(ModernTheme.DARK_TEXT_SECONDARY); form.add(lbl_techName,g); g.gridx=1; form.add(techName,g);
        JLabel status=new JLabel(" "); status.setForeground(ModernTheme.SUCCESS);
        g.gridy=1; g.gridx=0; g.gridwidth=2; form.add(status,g);
        ModernButton btn=new ModernButton("Save", ModernTheme.FULLSTACK_COLOR); btn.setPreferredSize(new Dimension(140,40));
        g.gridy=2; form.add(btn,g);
        btn.addActionListener(e->{
            if(techName.getText().trim().isEmpty()){ status.setForeground(ModernTheme.ERROR); status.setText("Fill all required fields."); return; }
            try { db.Save_Fullstack_Technology(loggedUser.User_Username,(techName.getText().trim()); status.setForeground(ModernTheme.SUCCESS); status.setText("Saved!"); techName.setText(""); }
            catch(Exception ex){ status.setForeground(ModernTheme.ERROR); status.setText("Error: "+ex.getMessage()); }
        });
        p.add(form,BorderLayout.CENTER); return p;
    }
    private JPanel build_messages() {
        JPanel p=new JPanel(new BorderLayout()); p.setBackground(ModernTheme.DARK_BG); p.setBorder(new EmptyBorder(30,30,30,30));
        JLabel ttl=new JLabel("Messages"); ttl.setFont(ModernTheme.TITLE_FONT); ttl.setForeground(ModernTheme.DARK_TEXT); p.add(ttl,BorderLayout.NORTH);
        JTabbedPane tabs=new JTabbedPane(); tabs.setBackground(ModernTheme.DARK_CARD); tabs.setForeground(ModernTheme.DARK_TEXT);

        // Send tab
        JPanel sendTab=new JPanel(new GridBagLayout()); sendTab.setBackground(ModernTheme.DARK_BG);
        GridBagConstraints g=new GridBagConstraints(); g.insets=new Insets(10,20,10,20); g.fill=GridBagConstraints.HORIZONTAL;
        JTextField toField=new JTextField(20); toField.setBackground(new Color(40,51,69)); toField.setForeground(ModernTheme.DARK_TEXT); toField.setCaretColor(Color.WHITE);
        JTextArea msgArea=new JTextArea(4,20); msgArea.setBackground(new Color(40,51,69)); msgArea.setForeground(ModernTheme.DARK_TEXT); msgArea.setCaretColor(Color.WHITE); msgArea.setLineWrap(true);
        JLabel sendStatus=new JLabel(" "); sendStatus.setForeground(ModernTheme.SUCCESS);
        ModernButton sendBtn=new ModernButton("Send",ModernTheme.FULLSTACK_COLOR); sendBtn.setPreferredSize(new Dimension(120,38));
        g.gridy=0;g.gridx=0;sendTab.add(new JLabel("To:"){{setForeground(ModernTheme.DARK_TEXT_SECONDARY);}},g);g.gridx=1;sendTab.add(toField,g);
        g.gridy=1;g.gridx=0;sendTab.add(new JLabel("Message:"){{setForeground(ModernTheme.DARK_TEXT_SECONDARY);}},g);g.gridx=1;sendTab.add(new JScrollPane(msgArea),g);
        g.gridy=2;g.gridx=0;g.gridwidth=2;sendTab.add(sendStatus,g);g.gridy=3;sendTab.add(sendBtn,g);
        sendBtn.addActionListener(e->{
            if(toField.getText().trim().isEmpty()||msgArea.getText().trim().isEmpty()){sendStatus.setForeground(ModernTheme.ERROR);sendStatus.setText("Fill all fields.");return;}
            try{db.Send_Message(loggedUser.User_Username,toField.getText().trim(),msgArea.getText().trim());sendStatus.setForeground(ModernTheme.SUCCESS);sendStatus.setText("Sent!");toField.setText("");msgArea.setText("");}
            catch(Exception ex){sendStatus.setForeground(ModernTheme.ERROR);sendStatus.setText("Error: "+ex.getMessage());}
        });

        // Inbox tab
        JTextArea inbox=new JTextArea("Click Refresh to load messages...\n");
        inbox.setEditable(false); inbox.setBackground(new Color(30,41,59)); inbox.setForeground(ModernTheme.DARK_TEXT); inbox.setFont(ModernTheme.NORMAL_FONT);
        ModernButton refresh=new ModernButton("Refresh Inbox",ModernTheme.FULLSTACK_COLOR); refresh.setPreferredSize(new Dimension(160,36));
        refresh.addActionListener(e->{
            inbox.setText("");
            try{java.util.List<String[]> msgs=db.getMessagesForUser(loggedUser.User_Username);
                if(msgs.isEmpty()) inbox.setText("No messages.\n");
                else for(String[] m:msgs) inbox.append("["+m[2]+"] "+m[0]+": "+m[1]+"\n---\n");
            }catch(Exception ex){inbox.setText("Error: "+ex.getMessage());}
        });
        JPanel inboxTab=new JPanel(new BorderLayout()); inboxTab.setBackground(ModernTheme.DARK_BG);
        JPanel rtop=new JPanel(new FlowLayout(FlowLayout.LEFT)); rtop.setOpaque(false); rtop.add(refresh);
        inboxTab.add(rtop,BorderLayout.NORTH); inboxTab.add(new JScrollPane(inbox),BorderLayout.CENTER);

        tabs.addTab("Send Message",sendTab); tabs.addTab("Inbox",inboxTab);
        p.add(tabs,BorderLayout.CENTER); return p;
    }
    private JPanel build_password() { return buildPasswordChangePanel(db); }
}
