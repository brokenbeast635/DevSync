package user;

import java.util.ArrayList;
public class Frontend_Engineer extends user {
    public ArrayList<String> Frontend_Frameworks = new ArrayList<>();
    Frontend_Engineer() {

    }
    public Frontend_Engineer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Frontend_Engineer(String username) {
        this.User_Username = username;
    }

    public void Add_Frontend_Framework(String Framework) {
        Frontend_Frameworks.add(Framework);
    }
    public void View_Frontend_Skills() {
        System.out.println("Frontend frameworks for: " + User_Username);
        for (String framework : Frontend_Frameworks) {
            System.out.println(" - " + framework);
        }
    }
}

