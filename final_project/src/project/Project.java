package project;


import user.Admin;
import user.Project_Manager;
import user.user;

import java.util.ArrayList;
public class Project {
    public String Project_Id;
    public String Project_Name;
    public String Project_Description;
    public String Project_Start_Date;
    public String Project_End_Date;
    Admin Project_Created_By;
    public Project_Manager Project_Manager;
    public ArrayList<user> Project_Team = new ArrayList<>();
    public ArrayList<Task> Project_Tasks = new ArrayList<>();
    ArrayList<String> Project_Gantt_Charts = new ArrayList<>();
    ArrayList<String> Project_Resources = new ArrayList<>();
    ArrayList<String> Project_Requirements = new ArrayList<>();
    ArrayList<String> Project_Task_Templates = new ArrayList<>();
    public String Project_Status;
    public double Project_Budget;
    public String Project_Client;

    public Project(String Project_Id, String Project_Name, String Project_Description, String Project_Start_Date, String Project_End_Date, Admin Project_Created_By) {
        this.Project_Id = Project_Id;
        this.Project_Name = Project_Name;
        this.Project_Description = Project_Description;
        this.Project_Start_Date = Project_Start_Date;
        this.Project_End_Date = Project_End_Date;
        this.Project_Created_By = Project_Created_By;
        this.Project_Status = "Planning";
        this.Project_Budget = 0.0;
        this.Project_Client = "Not Assigned";
        Initialize_Project_Task_Templates();
    }

    void Initialize_Project_Task_Templates() {
        Project_Task_Templates.add("UIUX_Designer:Design user interface mockups");
        Project_Task_Templates.add("Product_Designer:Create product design specifications");
        Project_Task_Templates.add("Business_Analyst:Analyze business requirements");
        Project_Task_Templates.add("Software_Architect:Design system architecture");
        Project_Task_Templates.add("Data_Engineer:Build data pipelines");
        Project_Task_Templates.add("Data_Scientist:Develop machine learning models");
        Project_Task_Templates.add("Machine_Learning_Engineer:Implement ML algorithms");
        Project_Task_Templates.add("Backend_Engineer:Develop backend APIs");
        Project_Task_Templates.add("Frontend_Engineer:Build user interface components");
        Project_Task_Templates.add("Fullstack_Engineer:Develop full-stack features");
        Project_Task_Templates.add("Mobile_Developer:Build mobile application");
        Project_Task_Templates.add("Coder:Write and review code");
        Project_Task_Templates.add("QA_Engineer:Perform manual testing");
        Project_Task_Templates.add("Automation_Test_Engineer:Create automated test scripts");
        Project_Task_Templates.add("Site_Reliability_Engineer:Setup monitoring and deployment");
        Project_Task_Templates.add("Product_Manager:Define product roadmap");
        Project_Task_Templates.add("Project_Manager:Manage project timeline and resources");
        Project_Task_Templates.add("Scrum_Master:Facilitate agile ceremonies");
    }
    public void Add_Team_Member(user User_To_Add) {
        if (User_To_Add != null) {
            Project_Team.add(User_To_Add);
            System.out.println("Added " + User_To_Add.User_Username + " to project team.");
        }
    }
    void Remove_Team_Member(user User_To_Remove) {
        if (Project_Team.remove(User_To_Remove)) {
            System.out.println("Removed " + User_To_Remove.User_Username + " from project team.");
        } else {
            System.out.println("User not found in project team.");
        }
    }
    public void Add_Task(Task Task_To_Add) {
        if (Task_To_Add != null) {
            Project_Tasks.add(Task_To_Add);
            System.out.println("Added task: " + Task_To_Add.Task_Title + " to project.");
        }
    }
    void Remove_Task(Task Task_To_Remove) {
        if (Project_Tasks.remove(Task_To_Remove)) {
            System.out.println("Removed task: " + Task_To_Remove.Task_Title + " from project.");
        } else {
            System.out.println("Task not found in project.");
        }
    }
    void Add_Gantt_Chart(String Gantt_Chart_Path) {
        Project_Gantt_Charts.add(Gantt_Chart_Path);
        System.out.println("Added Gantt chart: " + Gantt_Chart_Path);
    }
    void Add_Resource(String Resource_To_Add) {
        Project_Resources.add(Resource_To_Add);
        System.out.println("Added resource: " + Resource_To_Add);
    }
    void Add_Requirement(String Requirement_To_Add) {
        Project_Requirements.add(Requirement_To_Add);
        System.out.println("Added requirement: " + Requirement_To_Add);
    }
    public void Set_Project_Status(String New_Status) {
        this.Project_Status = New_Status;
        System.out.println("Project status updated to: " + New_Status);
    }
    void Set_Project_Budget(double New_Budget) {
        this.Project_Budget = New_Budget;
        System.out.println("Project budget set to: $" + New_Budget);
    }
    void Set_Project_Client(String Client_Name) {
        this.Project_Client = Client_Name;
        System.out.println("Project client set to: " + Client_Name);
    }
    public void Show_Project_Basic() {
        System.out.println("\nProject: " + Project_Id + " | " + Project_Name + " | Status: " + Project_Status);
        System.out.println("Description: " + Project_Description);
        System.out.println("Timeline: " + Project_Start_Date + " to " + Project_End_Date);
        System.out.println("Budget: $" + Project_Budget + " | Client: " + Project_Client);
    }
    public void Show_Project_Detailed() {
        System.out.println("\n=== Detailed Project Information ===");
        System.out.println("Project_ID: " + Project_Id);
        System.out.println("Project_Name: " + Project_Name);
        System.out.println("Description: " + Project_Description);
        System.out.println("Start_Date: " + Project_Start_Date);
        System.out.println("End_Date: " + Project_End_Date);
        System.out.println("Status: " + Project_Status);
        System.out.println("Budget: $" + Project_Budget);
        System.out.println("Client: " + Project_Client);
        System.out.println("Created_By: " + (Project_Created_By != null ? Project_Created_By.User_Username : "Unknown"));
        System.out.println("Manager: " + (Project_Manager != null ? Project_Manager.User_Username : "None"));

        System.out.println("Team_Members (" + Project_Team.size() + "):");
        for (user user : Project_Team) {
            System.out.println("  - " + user.User_Username + " (" + user.getClass().getSimpleName() + ")");
        }

        System.out.println("Tasks (" + Project_Tasks.size() + "):");
        for (Task task : Project_Tasks) {
            task.Show_Task_Basic();
        }

        System.out.println("Gantt_Charts: " + Project_Gantt_Charts);
        System.out.println("Resources: " + Project_Resources);
        System.out.println("Requirements: " + Project_Requirements);
        System.out.println("=== End of Project Information ===");
    }
    public void Show_Project_Progress() {
        int completed_tasks = 0;
        int total_tasks = Project_Tasks.size();
        double total_progress = 0;

        for (Task task : Project_Tasks) {
            total_progress += task.Task_Progress;
            if (task.Task_Status.equals("Completed")) {
                completed_tasks++;
            }
        }

        double overall_progress = total_tasks > 0 ? total_progress / total_tasks : 0;

        System.out.println("\n=== Project Progress Report ===");
        System.out.println("Project: " + Project_Name);
        System.out.println("Overall Progress: " + String.format("%.2f", overall_progress) + "%");
        System.out.println("Completed Tasks: " + completed_tasks + "/" + total_tasks);
        System.out.println("Remaining Tasks: " + (total_tasks - completed_tasks));
        System.out.println("Project Status: " + Project_Status);
        System.out.println("=== End of Progress Report ===");
    }
    public void Show_Team_Performance() {
        System.out.println("\n=== Team Performance Report ===");
        for (user member : Project_Team) {
            int member_tasks = 0;
            int completed_tasks = 0;
            double total_progress = 0;

            for (Task task : Project_Tasks) {
                if (task.Task_Assigned_To == member) {
                    member_tasks++;
                    total_progress += task.Task_Progress;
                    if (task.Task_Status.equals("Completed")) {
                        completed_tasks++;
                    }
                }
            }

            double avg_progress = member_tasks > 0 ? total_progress / member_tasks : 0;
            System.out.println(member.User_Username + " (" + member.getClass().getSimpleName() + "): " + completed_tasks + "/" + member_tasks + " tasks completed | Avg Progress: " + String.format("%.2f", avg_progress) + "%");
        }
        System.out.println("=== End of Performance Report ===");
    }
}















