package user;

import java.util.ArrayList;
public class Backend_Engineer extends user {
    public ArrayList<String> Backend_Technologies = new ArrayList<>();
    Backend_Engineer() {

    }
    public Backend_Engineer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Backend_Engineer(String username) {
        this.User_Username = username;
    }

    public void Add_Backend_Technology(String Technology) {
        Backend_Technologies.add(Technology);
    }
    public void Display_Backend_Skills() {
        System.out.println("Backend technologies for: " + User_Username);
        for (String tech : Backend_Technologies) {
            System.out.println(" - " + tech);
        }
    }
}

