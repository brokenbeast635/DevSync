package Chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FloatChatbot {

    private static JFrame chatFrame;
    private static ChatGPTBot bot;
    private static JButton chatBtn;

    // Initialize the bot with API key only
    public static void initialize(String apiKey) {
        bot = new ChatGPTBot(apiKey);
    }

    // Attach floating chat button to any parent frame
    public static void attachChatButton(JFrame parent) {
        if (chatBtn != null) return; // already added

        chatBtn = new JButton("Chat with AI");
        chatBtn.setBounds(20, 20, 140, 50); // position
        chatBtn.setFocusPainted(false);
        chatBtn.setForeground(Color.BLUE); // dark text for visibility
        chatBtn.setBackground(new Color(0, 130, 180)); // blue button
        chatBtn.setFont(new Font("Arial", Font.BOLD, 14));
        chatBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        chatBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                chatBtn.setBackground(new Color(100, 149, 237)); // lighter blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                chatBtn.setBackground(new Color(200, 130, 180));
            }
        });

        // Click opens chat window
        chatBtn.addActionListener(e -> openChatWindow(parent));

        // Add button to parent frame layered pane
        parent.getLayeredPane().add(chatBtn, JLayeredPane.PALETTE_LAYER);
        parent.getLayeredPane().revalidate();
        parent.getLayeredPane().repaint();
    }

    public static void openChatWindow(JFrame parent) {
        if (chatFrame != null && chatFrame.isVisible()) {
            chatFrame.toFront();
            return;
        }

        chatFrame = new JFrame("Chat with AI");
        chatFrame.setSize(400, 500);
        chatFrame.setLayout(new BorderLayout());
        chatFrame.setLocationRelativeTo(parent);

        // Blue-themed chat area
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(new Color(70, 130, 180)); // blue background
        chatArea.setForeground(Color.WHITE); // white text for contrast

        JScrollPane scrollPane = new JScrollPane(chatArea);
        chatFrame.add(scrollPane, BorderLayout.CENTER);

        // Blue-themed input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(65, 105, 225)); // darker blue

        JTextField userField = new JTextField();
        userField.setBackground(new Color(135, 206, 250)); // light blue
        userField.setForeground(Color.BLACK); // dark text
        userField.setCaretColor(Color.BLACK);

        JButton sendBtn = new JButton("Send");
        sendBtn.setBackground(new Color(100, 149, 237)); // button blue
        sendBtn.setForeground(Color.BLACK); // dark text
        sendBtn.setFocusPainted(false);

        // Hover effect on send button
        sendBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sendBtn.setBackground(new Color(135, 206, 250)); // lighter blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sendBtn.setBackground(new Color(100, 149, 237));
            }
        });

        inputPanel.add(userField, BorderLayout.CENTER);
        inputPanel.add(sendBtn, BorderLayout.EAST);

        chatFrame.add(inputPanel, BorderLayout.SOUTH);

        sendBtn.addActionListener(e -> sendMessage(chatArea, userField));
        userField.addActionListener(e -> sendMessage(chatArea, userField));

        chatFrame.setVisible(true);
    }

    private static void sendMessage(JTextArea chatArea, JTextField userField) {
        String message = userField.getText().trim();
        if (message.isEmpty()) return;

        chatArea.append("You: " + message + "\n");
        userField.setText("");

        // Send to API in a separate thread
        new Thread(() -> {
            String response = bot.getResponse(message);
            SwingUtilities.invokeLater(() -> chatArea.append("AI: " + response + "\n"));
        }).start();
    }
}
