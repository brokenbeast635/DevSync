package user;

import java.util.ArrayList;
public class Mobile_Developer extends user {
    public ArrayList<String> Mobile_Platforms = new ArrayList<>();
    Mobile_Developer() {

    }
    public Mobile_Developer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }
    public void Add_Mobile_Platform(String Platform) {
        Mobile_Platforms.add(Platform);
    }
    public void View_Mobile_Platforms() {
        System.out.println("Mobile platforms for: " + User_Username);
        for (String platform : Mobile_Platforms) {
            System.out.println(" - " + platform);
        }
    }
}
