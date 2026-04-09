package graphicaluserinterface;

import java.awt.*;

public class AppTheme {
    // Background & cards
    public static final Color BG = new Color(245, 247, 250);
    public static final Color CARD = Color.white;

    // Sidebar
    public static final Color SIDEBAR = new Color(30, 32, 40);
    public static final Color SIDEBAR_HOVER = new Color(45, 48, 60);
    public static final Color SIDEBAR_TEXT = new Color(220, 225, 245);

    // Accent
    public static final Color ACCENT = new Color(88, 132, 255);
    public static final Color ACCENT_LIGHT = new Color(120, 155, 255);

    // Status
    public static final Color SUCCESS = new Color(76, 175, 80);
    public static final Color ERROR = new Color(244, 67, 54);

    // Text
    public static final Color TEXT_PRIMARY = new Color(30, 30, 35);
    public static final Color TEXT_SECONDARY = new Color(100, 100, 120);

    // Fonts (fallback to default if not available)
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 15);
}
