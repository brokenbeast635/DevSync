package core;

import project.Message;

import java.util.ArrayList;

public class Communication_Module {
    public ArrayList<Message> All_Messages = new ArrayList<>();
    Activity_Logger Communication_Logger;

    Communication_Module(Activity_Logger Logger_Instance) {
        this.Communication_Logger = Logger_Instance;
    }

    public void Send_Message(String Sender_Username, String Receiver_Username, String Message_Content) {
        Message new_message = new Message(Sender_Username, Receiver_Username, Message_Content);
        All_Messages.add(new_message);
        Communication_Logger.Log_Activity("Message sent from " + Sender_Username + " to " + Receiver_Username + ": " + Message_Content);
        System.out.println("Message Sent Successfully!");
    }

    void View_Messages_For_User(String Username) {
        System.out.println("\n=== Messages for " + Username + " ===");
        boolean found_messages = false;

        for (Message message : All_Messages) {
            if (message.Message_Receiver.equals(Username)) {
                message.Show_Message();
                found_messages = true;
            }
        }

        if (!found_messages) {
            System.out.println("No messages found for " + Username);
        }
        System.out.println("=============================");
    }
    void View_Sent_Messages_For_User(String Username) {
        System.out.println("\n=== Messages sent by " + Username + " ===");
        boolean found_messages = false;

        for (Message message : All_Messages) {
            if (message.Message_Sender.equals(Username)) {
                message.Show_Message();
                found_messages = true;
            }
        }

        if (!found_messages) {
            System.out.println("No sent messages found for " + Username);
        }
        System.out.println("=================================");
    }
    void Mark_Message_As_Read(String Sender, String Receiver, String Content) {
        for (Message message : All_Messages) {
            if (message.Message_Sender.equals(Sender) && message.Message_Receiver.equals(Receiver) && message.Message_Content.equals(Content)) {
                message.Mark_As_Read();
                System.out.println("Message marked as read.");
                return;
            }
        }
        System.out.println("Message not found.");
    }
    void Show_All_Messages() {
        System.out.println("\n=== All System Messages ===");
        for (Message message : All_Messages) {
            message.Show_Message();
        }
        System.out.println("===========================");
    }

    void Clear_All_Messages() {
        All_Messages.clear();
        System.out.println("All messages cleared.");
    }
}



