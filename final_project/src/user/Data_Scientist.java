package user;

import java.util.ArrayList;
public class Data_Scientist extends user {
    public ArrayList<String> ML_Algorithms = new ArrayList<>();
    Data_Scientist() {

    }
    public Data_Scientist(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Data_Scientist(String username) {
        this.User_Username = username;
    }

    public void Add_ML_Algorithm(String Algorithm) {
        ML_Algorithms.add(Algorithm);
    }
    public void View_ML_Algorithms() {
        System.out.println("Machine Learning algorithms for: " + User_Username);
        for (String algorithm : ML_Algorithms) {
            System.out.println(" - " + algorithm);
        }
    }
}

