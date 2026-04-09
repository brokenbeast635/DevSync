package user;

import java.util.ArrayList;
public class Data_Engineer extends user {
    public ArrayList<String> Data_Technologies = new ArrayList<>();
    Data_Engineer() {

    }
    public Data_Engineer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Data_Engineer(String username) {
        this.User_Username = username;
    }

    public void Add_Data_Technology(String Technology) {
        Data_Technologies.add(Technology);
    }
    public void Display_Data_Technologies() {
        System.out.println("Data technologies for: " + User_Username);
        for (String tech : Data_Technologies) {
            System.out.println(" - " + tech);
        }
    }
}
