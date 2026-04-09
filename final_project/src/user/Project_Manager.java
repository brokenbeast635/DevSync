package user;

import java.util.ArrayList;
public class Project_Manager extends user {
    public ArrayList<String> Managed_Projects = new ArrayList<>();
    Project_Manager() {

    }
    public Project_Manager(String User_Id, String User_Username, String User_Password, String User_Email) {
        super(User_Id, User_Username, User_Password, User_Email);
    }

    public Project_Manager(String username) {
        this.User_Username = username;
    }

    public void Add_Managed_Project(String Project_Id) {
        Managed_Projects.add(Project_Id);
    }
    public void View_Managed_Projects() {
        System.out.println("Projects managed by: " + User_Username);
        for (String project : Managed_Projects) {
            System.out.println(" - " + project);
        }
    }
}

