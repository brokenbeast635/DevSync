package user;

import java.util.ArrayList;

public class Client extends user {
    public ArrayList<String> Client_Projects = new ArrayList<>();
    Client() {

    }
    public Client(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Client(String username) {
        this.User_Username = username;
    }

    public void Add_Project(String Project_Id) {
        Client_Projects.add(Project_Id);
    }
    public void View_Client_Projects() {
        System.out.println("Projects for Client: " + User_Username);
        for (String project : Client_Projects) {
            System.out.println(" - " + project);
        }
    }
}

