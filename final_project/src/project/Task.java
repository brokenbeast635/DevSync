package project;

import core.Activity_Logger;
import user.*;
import java.util.ArrayList;

public class Task {
    public String Task_Id;
    public String Task_Title;
    public String Task_Description;
    public user Task_Assigned_To;
    public String Task_Status;
    public int Task_Progress;
    ArrayList<String> Task_Uploads;
    ArrayList<user> Task_Assignment_History;
    public String  Task_Required_Role;
    String Task_Priority;
    String Task_Deadline;
    String Task_Created_Date;
    String Task_Last_Updated;

    public Task(String Task_Id, String Task_Title, String Task_Description, String Task_Required_Role) {
        this.Task_Id = Task_Id;
        this.Task_Title = Task_Title;
        this.Task_Description = Task_Description;
        this.Task_Required_Role = Task_Required_Role;
        this.Task_Assigned_To = null;
        this.Task_Status = "Pending";
        this.Task_Progress = 0;
        this.Task_Uploads = new ArrayList<>();
        this.Task_Assignment_History = new ArrayList<>();
        this.Task_Priority = "Medium";
        this.Task_Deadline = "Not Set";
        this.Task_Created_Date = "Today";
        this.Task_Last_Updated = "Today";
    }
    public void Assign_To(user User_To_Assign, Activity_Logger Logger_Instance) {
        if (User_To_Assign == null) {
            System.out.println("Cannot assign task to null user.");
            return;
        }
        this.Task_Assigned_To = User_To_Assign;
        this.Task_Assignment_History.add(User_To_Assign);

//        if (User_To_Assign instanceof UIUX_Designer || User_To_Assign instanceof Product_Designer) {
//            this.Task_Status = "In_Design";
//        } else if (User_To_Assign instanceof Business_Analyst) {
//            this.Task_Status = "In_Analysis";
//        } else if (User_To_Assign instanceof Software_Architect) {
//            this.Task_Status = "In_Architecture";
//        } else if (User_To_Assign instanceof Data_Engineer || User_To_Assign instanceof Data_Scientist || User_To_Assign instanceof Machine_Learning_Engineer) {
//            this.Task_Status = "In_Data_Work";
//        } else if (User_To_Assign instanceof QA_Engineer || User_To_Assign instanceof Automation_Test_Engineer) {
//            this.Task_Status = "In_Test";
//        } else if (User_To_Assign instanceof Project_Manager || User_To_Assign instanceof Product_Manager || User_To_Assign instanceof Scrum_Master) {
//            this.Task_Status = "In_Management";
//        } else {
//            this.Task_Status = "In_Progress";
//        }
        Logger_Instance.Log_Activity("Task " + Task_Id + " (" + Task_Title + ") assigned to " + User_To_Assign.User_Username + " (" + User_To_Assign.getClass().getSimpleName() + ")");
    }
    public void Upload_Work(String Upload_Note, Activity_Logger Logger_Instance) {
        Task_Uploads.add(Upload_Note);
        Logger_Instance.Log_Activity((Task_Assigned_To != null ? Task_Assigned_To.User_Username : "Unknown") + " uploaded work for Task " + Task_Id + ": " + Upload_Note);
    }
    public void Mark_Completed(Activity_Logger Logger_Instance) {
        this.Task_Status = "Completed";
        this.Task_Progress = 100;
        Logger_Instance.Log_Activity("Task " + Task_Id + " marked Completed by " + (Task_Assigned_To != null ? Task_Assigned_To.User_Username : "Unknown"));
    }
    public void Set_Status(String New_Status, int New_Progress, Activity_Logger Logger_Instance) {
        this.Task_Status = New_Status;
        this.Task_Progress = New_Progress;
        Logger_Instance.Log_Activity("Task " + Task_Id + " status updated to " + New_Status + " (" + New_Progress + "%)");
    }
    void Set_Priority(String New_Priority) {
        this.Task_Priority = New_Priority;
        System.out.println("Task " + Task_Id + " priority set to: " + New_Priority);
    }
    void Set_Deadline(String New_Deadline) {
        this.Task_Deadline = New_Deadline;
        System.out.println("Task " + Task_Id + " deadline set to: " + New_Deadline);
    }
    public void Show_Task_Basic() {
        System.out.println("Task_ID: " + Task_Id + " | Title: " + Task_Title + " | Status: " + Task_Status + " | Progress: " + Task_Progress + "% | Assigned_To: " + (Task_Assigned_To != null ? Task_Assigned_To.User_Username : "None") + " | Required_Role: " + Task_Required_Role);
    }
    void Show_Task_Detailed() {
        System.out.println("=== Detailed Task Information ===");
        System.out.println("Task_ID: " + Task_Id);
        System.out.println("Title: " + Task_Title);
        System.out.println("Description: " + Task_Description);
        System.out.println("Required_Role: " + Task_Required_Role);
        System.out.println("Status: " + Task_Status);
        System.out.println("Progress: " + Task_Progress + "%");
        System.out.println("Priority: " + Task_Priority);
        System.out.println("Deadline: " + Task_Deadline);
        System.out.println("Created_Date: " + Task_Created_Date);
        System.out.println("Last_Updated: " + Task_Last_Updated);
        System.out.println("Currently_Assigned_To: " + (Task_Assigned_To != null ? Task_Assigned_To.User_Username + " (" + Task_Assigned_To.getClass().getSimpleName() + ")" : "None"));
        System.out.println("Assignment_History:");
        for (user user : Task_Assignment_History) {
            System.out.println("  - " + user.User_Username + " (" + user.getClass().getSimpleName() + ")");
        }
        System.out.println("Uploads: " + Task_Uploads);
        System.out.println("=== End of Task Information ===");
    }

    void Show_Task_Progress() {
        System.out.println("Task: " + Task_Title + " | Progress: " + Task_Progress + "% | Status: " + Task_Status);
    }

    public void Update_Progress(int New_Progress, Activity_Logger Logger_Instance) {
        this.Task_Progress = New_Progress;
        if (New_Progress >= 100) {
            this.Task_Status = "Completed";
        } else if (New_Progress > 0) {
            this.Task_Status = "In_Progress";
        }
        Logger_Instance.Log_Activity("Task " + Task_Id + " progress updated to " + New_Progress + "%");
    }
}




