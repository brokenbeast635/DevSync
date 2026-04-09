package project;

public class Message {
    public String Message_Sender;
    public String Message_Receiver;
    public String Message_Content;
    public String Message_Timestamp;
    public boolean Message_Read;
    public Message(String Sender, String Receiver, String Content) {
        this.Message_Sender = Sender;
        this.Message_Receiver = Receiver;
        this.Message_Content = Content;
        this.Message_Timestamp = "Now";
        this.Message_Read = false;
    }
    public void Mark_As_Read() {
        this.Message_Read = true;
    }
    public void Show_Message() {
        String read_status = Message_Read ? "Read" : "Unread";
        System.out.println("[" + Message_Timestamp + "] " + Message_Sender + " -> " + Message_Receiver + " : " + Message_Content + " [" + read_status + "]");
    }
    void Show_Message_Detailed() {
        System.out.println("=== Message Details ===");
        System.out.println("From: " + Message_Sender);
        System.out.println("To: " + Message_Receiver);
        System.out.println("Time: " + Message_Timestamp);
        System.out.println("Content: " + Message_Content);
        System.out.println("Status: " + (Message_Read ? "Read" : "Unread"));
        System.out.println("======================");
    }
}

