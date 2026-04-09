package user;

import java.util.ArrayList;
public class Coder extends user {
    public ArrayList<String> Technical_Skills = new ArrayList<>();
    Coder() {

    }
    public Coder(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Coder(String username) {
        this.User_Username = username;
    }

    public void Add_Technical_Skill(String Skill) {
        Technical_Skills.add(Skill);
    }
    public void Display_Skills() {
        System.out.println("Technical skills for: " + User_Username);
        for (String skill : Technical_Skills) {
            System.out.println(" - " + skill);
        }
    }
}

