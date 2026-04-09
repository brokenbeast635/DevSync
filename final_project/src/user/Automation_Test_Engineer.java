package user;
import java.util.ArrayList;
public class Automation_Test_Engineer extends user {
    public ArrayList<String> Automation_Tools = new ArrayList<>();
    Automation_Test_Engineer() {

    }
    public Automation_Test_Engineer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Automation_Test_Engineer(String username) {
        this.User_Username = username;
    }

    public void Add_Automation_Tool(String Tool) {
        Automation_Tools.add(Tool);
    }
    public void Display_Automation_Tools() {
        System.out.println("Automation tools for: " + User_Username);
        for (String tool : Automation_Tools) {
            System.out.println(" - " + tool);
        }
    }
}

