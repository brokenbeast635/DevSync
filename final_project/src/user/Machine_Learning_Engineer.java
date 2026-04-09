package user;


import java.util.ArrayList;
public class Machine_Learning_Engineer extends user {
    public ArrayList<String> ML_Frameworks = new ArrayList<>();
    Machine_Learning_Engineer() {

    }
    public Machine_Learning_Engineer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Machine_Learning_Engineer(String username) {
        this.User_Username = username;
    }

    public void Add_ML_Framework(String Framework) {
        ML_Frameworks.add(Framework);
    }
    public void Display_ML_Frameworks() {
        System.out.println("ML frameworks for: " + User_Username);
        for (String framework : ML_Frameworks) {
            System.out.println(" - " + framework);
        }
    }
}

