package user;

import java.util.ArrayList;

public class Software_Architect extends user {
    public ArrayList<String> Architecture_Specializations = new ArrayList<>();
    Software_Architect() {

    }
    public Software_Architect(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Software_Architect(String username) {
        this.User_Username = username;
    }

    public void Add_Specialization(String Specialization) {
        Architecture_Specializations.add(Specialization);
    }
    public void View_Specializations() {
        System.out.println("Architecture specializations for: " + User_Username);
        for (String spec : Architecture_Specializations) {
            System.out.println(" - " + spec);
        }
    }
}

