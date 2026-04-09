package user;

import java.util.ArrayList;

public class UIUX_Designer extends user {

    // BUG FIX: Original had duplicate field declarations (User_ID, User_Username, User_Password)
    // that shadowed the parent class fields — parent fields were never set, causing NPE everywhere.
    // Also the 3-arg constructor never called super(), so login data was lost.

    private ArrayList<String> tools = new ArrayList<>();

    public UIUX_Designer() {}

    public UIUX_Designer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public UIUX_Designer(String username) {
        this.User_Username = username;
    }

    public void Add_Design_Tool(String tool) {
        tools.add(tool);
    }

    public ArrayList<String> getTools() {
        return tools;
    }

    public void Display_Design_Tools() {
        System.out.println("=== My Tools ===");
        for (String t : tools) {
            System.out.println("- " + t);
        }
    }
}
