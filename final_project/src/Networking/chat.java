package Networking;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class chat {
    private Socket socket;
    private String username;

    // Encryption key - MUST match Python server
    private static final String SECRET_KEY = "DevSync";

    public static void main(String[] args) {
        chat client = new chat();
        client.start();
    }

    /**
     * XOR encryption - same as Python server
     */
    private byte[] xorCipher(byte[] text, byte[] key) {
        byte[] extendedKey = key;

        // Extend key if needed
        if (key.length < text.length) {
            int repetitions = text.length / key.length + 1;
            extendedKey = new byte[key.length * repetitions];
            for (int i = 0; i < repetitions; i++) {
                System.arraycopy(key, 0, extendedKey, i * key.length, key.length);
            }
            // Trim to exact length
            byte[] trimmed = new byte[text.length];
            System.arraycopy(extendedKey, 0, trimmed, 0, text.length);
            extendedKey = trimmed;
        }

        // XOR each byte
        byte[] result = new byte[text.length];
        for (int i = 0; i < text.length; i++) {
            result[i] = (byte) (text[i] ^ extendedKey[i]);
        }
        return result;
    }

    /**
     * Encrypt string message
     */
    private byte[] encrypt(String message) {
        try {
            byte[] messageBytes = message.getBytes("UTF-8");
            byte[] keyBytes = SECRET_KEY.getBytes("UTF-8");
            return xorCipher(messageBytes, keyBytes);
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Decrypt byte array to string
     */
    private String decrypt(byte[] encrypted) {
        try {
            byte[] keyBytes = SECRET_KEY.getBytes("UTF-8");
            byte[] decrypted = xorCipher(encrypted, keyBytes);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            System.err.println("Decryption error: " + e.getMessage());
            return null;
        }
    }

    public void start() {
        Scanner scanner = null;
        try {
            System.out.println("Connecting to server at 127.0.0.1:9000...");
            socket = new Socket("127.0.0.1", 9000);

            // Use raw byte streams for encryption
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            System.out.println("✓ Connected to server!");
            System.out.println("🔒 Encryption active");

            // Read encrypted prompt
            ByteArrayOutputStream promptBuffer = new ByteArrayOutputStream();
            int b;
            while ((b = inputStream.read()) != -1 && b != '\n') {
                promptBuffer.write(b);
            }
            byte[] encryptedPrompt = promptBuffer.toByteArray();
            String prompt = decrypt(encryptedPrompt);
            System.out.println("Server: " + prompt);

            scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            username = scanner.nextLine().trim();

            if (username.isEmpty()) {
                username = "User" + System.currentTimeMillis() % 1000;
            }

            // Send encrypted username
            byte[] encryptedUsername = encrypt(username);
            outputStream.write(encryptedUsername);
            outputStream.write('\n');
            outputStream.flush();
            System.out.println("✓ Name sent to server (encrypted)");

            // Start receiver thread
            Thread receiver = new Thread(() -> receiveMessages(inputStream));
            receiver.setDaemon(true);
            receiver.start();

            System.out.println("\n" + "=".repeat(50));
            System.out.println("          CHAT STARTED (ENCRYPTED)");
            System.out.println("=".repeat(50));
            System.out.println("Commands: /quit - exit");
            System.out.println("🔒 All messages are encrypted");
            System.out.println("=".repeat(50) + "\n");

            // Send messages
            while (true) {
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("/quit") || message.equalsIgnoreCase("/exit")) {
                    System.out.println("\nExiting chat...");
                    break;
                }

                if (message.trim().isEmpty()) {
                    continue;
                }

                // Encrypt and send (no echo)
                byte[] encryptedMessage = encrypt(message);
                if (encryptedMessage != null) {
                    outputStream.write(encryptedMessage);
                    outputStream.write('\n');
                    outputStream.flush();
                }
            }

        } catch (Exception e) {
            System.err.println("\n✗ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (scanner != null) scanner.close();
                if (socket != null && !socket.isClosed()) socket.close();
                System.out.println("\n✓ Disconnected from server");
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    private void receiveMessages(InputStream inputStream) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int b;

            while ((b = inputStream.read()) != -1) {
                if (b == '\n') {
                    // Complete message received
                    byte[] encryptedMessage = buffer.toByteArray();

                    if (encryptedMessage.length > 0) {
                        String message = decrypt(encryptedMessage);
                        if (message != null && !message.trim().isEmpty()) {
                            System.out.println(message);
                        }
                    }

                    buffer.reset();
                } else {
                    buffer.write(b);
                }
            }

            System.out.println("\n✗ Server closed connection");
        } catch (Exception e) {
            System.out.println("\n✗ Connection to server lost");
        }
    }
}