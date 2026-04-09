//package GUI;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class RoundedPanel extends JPanel {
//    private final int cornerRadius;
//    private final Color backgroundColor;
//
//    public RoundedPanel(int radius, Color bg) {
//        super();
//        cornerRadius = radius;
//        backgroundColor = bg;
//        setOpaque(false);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
//        Graphics2D g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        // Draws the rounded panel with background color
//        g2.setColor(backgroundColor);
//        g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, arcs.width, arcs.height);
//
//        g2.setColor(new Color(220,220,225)); // subtle border
//        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcs.width, arcs.height);
//
//        g2.dispose();
//    }
//}
//
//
//
//
//
