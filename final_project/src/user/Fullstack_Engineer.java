package user;

import java.util.ArrayList;
public class Fullstack_Engineer extends user {
    public ArrayList<String> Fullstack_Technologies = new ArrayList<>();

    Fullstack_Engineer() {

    }
    public Fullstack_Engineer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Fullstack_Engineer(String username) {
        this.User_Username = username;
    }

    public void Add_Fullstack_Technology(String Technology) {
        Fullstack_Technologies.add(Technology);
    }
    public void Display_Fullstack_Skills() {
        System.out.println("Fullstack technologies for: " + User_Username);
        for (String tech : Fullstack_Technologies) {
            System.out.println(" - " + tech);
        }
    }
}
