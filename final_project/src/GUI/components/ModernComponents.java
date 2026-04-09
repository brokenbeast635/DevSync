package GUI.components;

import GUI.themes.ModernTheme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModernComponents {

    // Modern Button with gradient
    public static class ModernButton extends JButton {
        private Color startColor;
        private Color endColor;
        private boolean hover;

        public ModernButton(String text, Color color) {
            super(text);
            this.startColor = color;
            this.endColor = darker(color, 30);
            this.hover = false;

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setForeground(Color.WHITE);
            setFont(ModernTheme.BOLD_FONT);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    repaint();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw gradient
            GradientPaint gradient;
            if (hover) {
                gradient = new GradientPaint(0, 0, brighter(startColor, 20),
                        0, getHeight(), brighter(endColor, 20));
            } else {
                gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
            }

            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

            // Draw text
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(getText(), x, y);

            g2.dispose();
        }

        private Color darker(Color color, int factor) {
            return new Color(
                    Math.max(color.getRed() - factor, 0),
                    Math.max(color.getGreen() - factor, 0),
                    Math.max(color.getBlue() - factor, 0)
            );
        }

        private Color brighter(Color color, int factor) {
            return new Color(
                    Math.min(color.getRed() + factor, 255),
                    Math.min(color.getGreen() + factor, 255),
                    Math.min(color.getBlue() + factor, 255)
            );
        }
    }

    // Modern Text Field
    public static class ModernTextField extends JTextField {
        public ModernTextField(String placeholder) {
            setForeground(ModernTheme.DARK_TEXT);
            setBackground(new Color(40, 51, 69));
            setCaretColor(ModernTheme.DARK_TEXT);
            setFont(ModernTheme.NORMAL_FONT);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 2, 0, ModernTheme.BORDER_COLOR),
                    new EmptyBorder(12, 15, 12, 15)
            ));
            setSelectionColor(new Color(59, 130, 246, 100));
            setSelectedTextColor(Color.WHITE);
        }
    }

    // Modern Password Field
    public static class ModernPasswordField extends JPasswordField {
        public ModernPasswordField() {
            setForeground(ModernTheme.DARK_TEXT);
            setBackground(new Color(40, 51, 69));
            setCaretColor(ModernTheme.DARK_TEXT);
            setFont(ModernTheme.NORMAL_FONT);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 2, 0, ModernTheme.BORDER_COLOR),
                    new EmptyBorder(12, 15, 12, 15)
            ));
            setSelectionColor(new Color(59, 130, 246, 100));
            setSelectedTextColor(Color.WHITE);
        }
    }

    // Modern Card Panel
    public static class ModernCard extends JPanel {
        private Color backgroundColor;
        private int borderRadius;

        public ModernCard(Color bgColor, int radius) {
            this.backgroundColor = bgColor;
            this.borderRadius = radius;
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw shadow effect
            g2.setColor(new Color(0, 0, 0, 30));
            for (int i = 0; i < 3; i++) {
                g2.fillRoundRect(i, i, getWidth()-2*i, getHeight()-2*i,
                        borderRadius, borderRadius);
            }

            // Draw main card
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

            // Draw border
            g2.setColor(new Color(255, 255, 255, 20));
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // Stat Card Component
    public static class StatCard extends ModernCard {
        public StatCard(String title, String value, String icon, Color color) {
            super(new Color(color.getRed(), color.getGreen(), color.getBlue(), 20), 15);
            setLayout(new BorderLayout());

            JLabel iconLabel = new JLabel(icon);
            iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 32));
            iconLabel.setForeground(color);
            iconLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

            JLabel valueLabel = new JLabel(value);
            valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
            valueLabel.setForeground(ModernTheme.DARK_TEXT);

            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(ModernTheme.NORMAL_FONT);
            titleLabel.setForeground(ModernTheme.DARK_TEXT_SECONDARY);

            JPanel content = new JPanel();
            content.setOpaque(false);
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.add(valueLabel);
            content.add(Box.createRigidArea(new Dimension(0, 5)));
            content.add(titleLabel);

            add(iconLabel, BorderLayout.NORTH);
            add(content, BorderLayout.CENTER);
        }
    }
}