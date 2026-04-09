package user;

import java.util.ArrayList;

public class Admin extends user {

    public ArrayList<String> System_Privileges = new ArrayList<>();

    public Admin() {}

    public Admin(String username) {
        this.User_Username = username;
    }

    public Admin(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    // Add privilege to admin
    public void Add_System_Privilege(String privilege) {
        System_Privileges.add(privilege);
    }

    // View privileges in console
    public void View_System_Privileges() {
        System.out.println("Privileges of Admin: " + User_Username);
        for (String p : System_Privileges) {
            System.out.println(" - " + p);
        }
    }
}
