package user;

import java.util.ArrayList;

public class QA_Engineer extends user {
    public ArrayList<String> Testing_Methodologies = new ArrayList<>();

    QA_Engineer() {

    }

    public QA_Engineer(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public QA_Engineer(String username) {
        this.User_Username = username;
    }

    public void Add_Testing_Methodology(String Methodology) {
        Testing_Methodologies.add(Methodology);
    }

    public void View_Testing_Methodologies() {
        System.out.println("Testing methodologies for: " + User_Username);
        for (String methodology : Testing_Methodologies) {
            System.out.println(" - " + methodology);
        }
    }
}

