//package GUI;
//
//import graphicaluserinterface.AppTheme;
//import user.user;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class DashboardFrame extends JFrame {
//
//    private final user loggedUser;
//
//    public DashboardFrame(String username, int roleId) {
//        this.loggedUser = new user(username, roleId);
//        initUI();
//    }
//
//
//
//    private void initUI() {
//
//        setTitle("DevSync — Dashboard");
//        setSize(1350, 800);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // 🌈 GRADIENT BACKGROUND PANEL
//        JPanel background = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                Graphics2D g2d = (Graphics2D) g;
//                GradientPaint gp = new GradientPaint(
//                        0, 0, new Color(240, 242, 255),
//                        0, getHeight(), new Color(225, 230, 245)
//                );
//                g2d.setPaint(gp);
//                g2d.fillRect(0, 0, getWidth(), getHeight());
//            }
//        };
//        background.setLayout(new BorderLayout());
//        add(background);
//
//        // ================= SIDEBAR =================
//        JPanel sidebar = new JPanel();
//        sidebar.setBackground(new Color(25, 29, 38));
//        sidebar.setPreferredSize(new Dimension(270, 0));
//        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
//
//        JLabel name = new JLabel("<html><b>" + loggedUser.User_Username + "</b></html>");
//        name.setFont(new Font("Segoe UI", Font.BOLD, 22));
//        name.setForeground(Color.WHITE);
//        name.setBorder(BorderFactory.createEmptyBorder(30, 25, 0, 10));
//
//        JLabel role = new JLabel("Role : " + loggedUser.User_Role);
//        role.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        role.setForeground(new Color(180, 190, 210));
//        role.setBorder(BorderFactory.createEmptyBorder(5, 25, 30, 10));
//
//        sidebar.add(name);
//        sidebar.add(role);
//
//        sidebar.add(makeMenuButton("Dashboard"));
//        sidebar.add(makeMenuButton("Projects"));
//        sidebar.add(makeMenuButton("Teams"));
//        sidebar.add(makeMenuButton("Clients"));
//        sidebar.add(makeMenuButton("Tasks"));
//        sidebar.add(makeMenuButton("Reports"));
//        sidebar.add(makeMenuButton("Employees"));
//        sidebar.add(makeMenuButton("Settings"));
//        sidebar.add(Box.createVerticalGlue());
//
//        background.add(sidebar, BorderLayout.WEST);
//
//
//
//        // ================= TOP BAR =================
//        JPanel topBar = new JPanel(new BorderLayout());
//        topBar.setPreferredSize(new Dimension(0, 90));
//        topBar.setBackground(new Color(255, 255, 255));
//
//        JLabel title = new JLabel("WELCOME, " + loggedUser.User_Username.toUpperCase());
//        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
//        title.setForeground(new Color(40, 40, 40));
//        title.setBorder(BorderFactory.createEmptyBorder(25, 35, 10, 10));
//
//        topBar.add(title, BorderLayout.WEST);
//
//        background.add(topBar, BorderLayout.NORTH);
//
//
//
//        // ================= MAIN CONTENT =================
//        JPanel content = new JPanel();
//        content.setOpaque(false);
//        content.setLayout(new BorderLayout());
//
//        JPanel cards = new JPanel();
//        cards.setLayout(new GridLayout(2, 3, 25, 25));
//        cards.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
//        cards.setOpaque(false);
//
//        cards.add(makeInfoCard("Total Projects", "18"));
//        cards.add(makeInfoCard("Active Teams", "7"));
//        cards.add(makeInfoCard("Clients", "12"));
//        cards.add(makeInfoCard("Pending Tasks", "33"));
//        cards.add(makeInfoCard("Issues Reported", "4"));
//        cards.add(makeInfoCard("Employees", "26"));
//
//        content.add(cards, BorderLayout.CENTER);
//
//        background.add(content, BorderLayout.CENTER);
//    }
//
//
//
//    //====================== MAKE INFO CARD ======================
//    private JPanel makeInfoCard(String title, String number) {
//
//        JPanel card = new JPanel();
//        card.setBackground(Color.WHITE);
//        card.setLayout(new BorderLayout());
//        card.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(210, 210, 225)),
//                BorderFactory.createEmptyBorder(25, 25, 25, 25)
//        ));
//
//        JLabel t = new JLabel(title);
//        t.setFont(new Font("Segoe UI", Font.PLAIN, 17));
//        t.setForeground(new Color(90, 95, 110));
//
//        JLabel num = new JLabel(number);
//        num.setFont(new Font("Segoe UI", Font.BOLD, 36));
//        num.setForeground(new Color(40, 40, 40));
//
//        card.add(t, BorderLayout.NORTH);
//        card.add(num, BorderLayout.CENTER);
//
//        return card;
//    }
//
//
//
//    //====================== MENU BUTTONS ======================
//    private JButton makeMenuButton(String text) {
//
//        JButton btn = new JButton(text);
//        btn.setMaximumSize(new Dimension(260, 45));
//        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
//
//        btn.setBackground(new Color(40, 44, 55));
//        btn.setForeground(Color.WHITE);
//        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//        btn.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 20));
//        btn.setFocusPainted(false);
//        btn.setHorizontalAlignment(SwingConstants.LEFT);
//
//        // Hover effect
//        btn.addMouseListener(new java.awt.event.MouseAdapter() {
//
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                btn.setBackground(new Color(65, 70, 85));
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                btn.setBackground(new Color(40, 44, 55));
//            }
//        });
//
//        return btn;
//    }
//}
