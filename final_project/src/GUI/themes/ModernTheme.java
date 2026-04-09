package GUI.themes;

import java.awt.*;

public class ModernTheme {
    // Dark theme colors
    public static final Color DARK_BG = new Color(15, 23, 42);
    public static final Color DARK_CARD = new Color(30, 41, 59);
    public static final Color DARK_SIDEBAR = new Color(21, 32, 50);
    public static final Color DARK_TEXT = new Color(226, 232, 240);
    public static final Color DARK_TEXT_SECONDARY = new Color(148, 163, 184);
    public static final Color BORDER_COLOR = new Color(51, 65, 85);

    // Role-specific colors for ALL 17 roles
    public static final Color CLIENT_COLOR = new Color(139, 92, 246);     // Purple
    public static final Color CODER_COLOR = new Color(14, 165, 233);      // Cyan
    public static final Color PM_COLOR = new Color(34, 197, 94);          // Green
    public static final Color ADMIN_COLOR = new Color(239, 68, 68);       // Red
    public static final Color UIUX_COLOR = new Color(245, 158, 11);       // Amber
    public static final Color BACKEND_COLOR = new Color(59, 130, 246);    // Blue
    public static final Color FRONTEND_COLOR = new Color(236, 72, 153);   // Pink
    public static final Color FULLSTACK_COLOR = new Color(168, 85, 247);  // Violet
    public static final Color QA_COLOR = new Color(20, 184, 166);         // Teal
    public static final Color DATA_ENGINEER_COLOR = new Color(249, 115, 22); // Orange
    public static final Color DATA_SCIENTIST_COLOR = new Color(217, 70, 239); // Fuchsia
    public static final Color ML_ENGINEER_COLOR = new Color(6, 182, 212); // Cyan-500
    public static final Color MOBILE_COLOR = new Color(16, 185, 129);     // Emerald
    public static final Color AUTOMATION_COLOR = new Color(139, 92, 246); // Purple-500
    public static final Color SRE_COLOR = new Color(59, 130, 246);        // Blue-500
    public static final Color ARCHITECT_COLOR = new Color(245, 158, 11);  // Amber-500
    public static final Color PRODUCT_MANAGER_COLOR = new Color(239, 68, 68); // Red-500

    // Status colors
    public static final Color SUCCESS = new Color(34, 197, 94);
    public static final Color WARNING = new Color(245, 158, 11);
    public static final Color ERROR = new Color(239, 68, 68);
    public static final Color INFO = new Color(59, 130, 246);

    // Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 32);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BOLD_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font MONO_FONT = new Font("JetBrains Mono", Font.PLAIN, 13);
    public static Color DARK_BORDER;

    // Get role color by role ID
    public static Color getRoleColor(int roleId) {
        return switch(roleId) {
            case 5 -> CLIENT_COLOR;               // Client
            case 6 -> CODER_COLOR;                // Coder
            case 14 -> PM_COLOR;                  // Project Manager
            case 1 -> ADMIN_COLOR;                // Admin
            case 2 -> UIUX_COLOR;                 // UIUX Designer
            case 3 -> BACKEND_COLOR;              // Backend Engineer
            case 4 -> FRONTEND_COLOR;             // Frontend Engineer
            case 7 -> FULLSTACK_COLOR;            // Fullstack Engineer
            case 8 -> QA_COLOR;                   // QA Engineer
            case 9 -> DATA_ENGINEER_COLOR;        // Data Engineer
            case 10 -> DATA_SCIENTIST_COLOR;      // Data Scientist
            case 11 -> ML_ENGINEER_COLOR;         // ML Engineer
            case 12 -> MOBILE_COLOR;              // Mobile Developer
            case 13 -> AUTOMATION_COLOR;          // Automation Test Engineer
            case 15 -> SRE_COLOR;                 // Site Reliability Engineer
            case 16 -> ARCHITECT_COLOR;           // Software Architect
            case 17 -> PRODUCT_MANAGER_COLOR;     // Product Manager
            default -> INFO;
        };
    }

    // Get role name by role ID
    public static String getRoleName(int roleId) {
        return switch(roleId) {
            case 5 -> "Client";
            case 6 -> "Coder/Developer";
            case 14 -> "Project Manager";
            case 1 -> "Admin";
            case 2 -> "UI/UX Designer";
            case 3 -> "Backend Engineer";
            case 4 -> "Frontend Engineer";
            case 7 -> "Fullstack Engineer";
            case 8 -> "QA Engineer";
            case 9 -> "Data Engineer";
            case 10 -> "Data Scientist";
            case 11 -> "ML Engineer";
            case 12 -> "Mobile Developer";
            case 13 -> "Automation Test Engineer";
            case 15 -> "Site Reliability Engineer";
            case 16 -> "Software Architect";
            case 17 -> "Product Manager";
            default -> "User";
        };
    }

    // Get role icon
    public static String getRoleIcon(int roleId) {
        return switch(roleId) {
            case 5 -> "👔";
            case 6 -> "💻";
            case 14 -> "👨‍💼";
            case 1 -> "👑";
            case 2 -> "🎨";
            case 3 -> "⚙";
            case 4 -> "🖥";
            case 7 -> "🔧";
            case 8 -> "🔍";
            case 9 -> "📊";
            case 10 -> "🧠";
            case 11 -> "🤖";
            case 12 -> "📱";
            case 13 -> "🤖";
            case 15 -> "🛠";
            case 16 -> "🏗";
            case 17 -> "📋";
            default -> "👤";
        };
    }
}