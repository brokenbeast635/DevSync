package user;

import java.util.ArrayList;
public class Site_Reliability_Engineer extends user {
    public ArrayList<String> SRE_Tools = new ArrayList<>();
    Site_Reliability_Engineer() {

    }
    public Site_Reliability_Engineer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Site_Reliability_Engineer(String username) {
        this.User_Username = username;
    }

    public void Add_SRE_Tool(String Tool) {
        SRE_Tools.add(Tool);
    }
    public void Display_SRE_Tools() {
        System.out.println("SRE tools for: " + User_Username);
        for (String tool : SRE_Tools) {
            System.out.println(" - " + tool);
        }
    }
}
