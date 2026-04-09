package core;

import Database.DBconn;
import project.Project;
import project.Task;
import user.user;
import user.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static Database.DBconn.DatabaseConnection.getConnection;

public class Portal {
    public static ArrayList<Client> All_Clients = new ArrayList<>();
    public static ArrayList<user> All_Employees = new ArrayList<>();
    static ArrayList<String> Pre_Registered_Ids = new ArrayList<>();
    ArrayList<Project> All_Projects = new ArrayList<>();
    static Activity_Logger System_Logger = new Activity_Logger();
    Communication_Module System_Communication;
    Admin System_Administrator;
    public static Scanner User_Input = new Scanner(System.in);

    public Portal() {
        System_Communication = new Communication_Module(System_Logger);
        Initialize_Pre_Registered_Ids();
        Initialize_System_Administrator();
        Add_Demo_Data();
    }

    void Initialize_System_Administrator() {
        System_Administrator = new Admin("ADMIN001", "faizan", "faizan123", "admin@devsync.com");
        All_Employees.add(System_Administrator);
        System_Logger.Log_System("System Administrator pre-registered");
    }

    void Initialize_Pre_Registered_Ids() {
        // Project Managers - 5 IDs
        Pre_Registered_Ids.add("PM001");
        Pre_Registered_Ids.add("PM002");
        Pre_Registered_Ids.add("PM003");
        Pre_Registered_Ids.add("PM004");
        Pre_Registered_Ids.add("PM005");

        // Coders - 5 IDs
        Pre_Registered_Ids.add("CDR001");
        Pre_Registered_Ids.add("CDR002");
        Pre_Registered_Ids.add("CDR003");
        Pre_Registered_Ids.add("CDR004");
        Pre_Registered_Ids.add("CDR005");

        // Software Architects - 5 IDs
        Pre_Registered_Ids.add("SA001");
        Pre_Registered_Ids.add("SA002");
        Pre_Registered_Ids.add("SA003");
        Pre_Registered_Ids.add("SA004");
        Pre_Registered_Ids.add("SA005");

        // Backend Engineers - 5 IDs
        Pre_Registered_Ids.add("BE001");
        Pre_Registered_Ids.add("BE002");
        Pre_Registered_Ids.add("BE003");
        Pre_Registered_Ids.add("BE004");
        Pre_Registered_Ids.add("BE005");

        // Frontend Engineers - 5 IDs
        Pre_Registered_Ids.add("FE001");
        Pre_Registered_Ids.add("FE002");
        Pre_Registered_Ids.add("FE003");
        Pre_Registered_Ids.add("FE004");
        Pre_Registered_Ids.add("FE005");

        // Fullstack Engineers - 5 IDs
        Pre_Registered_Ids.add("FSE001");
        Pre_Registered_Ids.add("FSE002");
        Pre_Registered_Ids.add("FSE003");
        Pre_Registered_Ids.add("FSE004");
        Pre_Registered_Ids.add("FSE005");

        // Mobile Developers - 5 IDs
        Pre_Registered_Ids.add("MD001");
        Pre_Registered_Ids.add("MD002");
        Pre_Registered_Ids.add("MD003");
        Pre_Registered_Ids.add("MD004");
        Pre_Registered_Ids.add("MD005");

        // Site Reliability Engineers - 5 IDs
        Pre_Registered_Ids.add("SRE001");
        Pre_Registered_Ids.add("SRE002");
        Pre_Registered_Ids.add("SRE003");
        Pre_Registered_Ids.add("SRE004");
        Pre_Registered_Ids.add("SRE005");

        // QA Engineers - 5 IDs
        Pre_Registered_Ids.add("QA001");
        Pre_Registered_Ids.add("QA002");
        Pre_Registered_Ids.add("QA003");
        Pre_Registered_Ids.add("QA004");
        Pre_Registered_Ids.add("QA005");

        // Automation Test Engineers - 5 IDs
        Pre_Registered_Ids.add("ATE001");
        Pre_Registered_Ids.add("ATE002");
        Pre_Registered_Ids.add("ATE003");
        Pre_Registered_Ids.add("ATE004");
        Pre_Registered_Ids.add("ATE005");

        // Product Managers - 5 IDs
        Pre_Registered_Ids.add("PDM001");
        Pre_Registered_Ids.add("PDM002");
        Pre_Registered_Ids.add("PDM003");
        Pre_Registered_Ids.add("PDM004");
        Pre_Registered_Ids.add("PDM005");

        // Business Analysts - 5 IDs
        Pre_Registered_Ids.add("BA001");
        Pre_Registered_Ids.add("BA002");
        Pre_Registered_Ids.add("BA003");
        Pre_Registered_Ids.add("BA004");
        Pre_Registered_Ids.add("BA005");

        // Scrum Masters - 5 IDs
        Pre_Registered_Ids.add("SM001");
        Pre_Registered_Ids.add("SM002");
        Pre_Registered_Ids.add("SM003");
        Pre_Registered_Ids.add("SM004");
        Pre_Registered_Ids.add("SM005");

        // UI/UX Designers - 5 IDs
        Pre_Registered_Ids.add("UIUX001");
        Pre_Registered_Ids.add("UIUX002");
        Pre_Registered_Ids.add("UIUX003");
        Pre_Registered_Ids.add("UIUX004");
        Pre_Registered_Ids.add("UIUX005");

        // Product Designers - 5 IDs
        Pre_Registered_Ids.add("PD001");
        Pre_Registered_Ids.add("PD002");
        Pre_Registered_Ids.add("PD003");
        Pre_Registered_Ids.add("PD004");
        Pre_Registered_Ids.add("PD005");

        // Data Engineers - 5 IDs
        Pre_Registered_Ids.add("DE001");
        Pre_Registered_Ids.add("DE002");
        Pre_Registered_Ids.add("DE003");
        Pre_Registered_Ids.add("DE004");
        Pre_Registered_Ids.add("DE005");

        // Data Scientists - 5 IDs
        Pre_Registered_Ids.add("DS001");
        Pre_Registered_Ids.add("DS002");
        Pre_Registered_Ids.add("DS003");
        Pre_Registered_Ids.add("DS004");
        Pre_Registered_Ids.add("DS005");

        // Machine Learning Engineers - 5 IDs
        Pre_Registered_Ids.add("MLE001");
        Pre_Registered_Ids.add("MLE002");
        Pre_Registered_Ids.add("MLE003");
        Pre_Registered_Ids.add("MLE004");
        Pre_Registered_Ids.add("MLE005");

        System_Logger.Log_System("Pre-registered IDs initialized with " + Pre_Registered_Ids.size() + " employee slots");
    }

    void Add_Demo_Data() {
        // Add some demo projects
        Project demoProject1 = new Project("PROJ001", "E-Commerce Website", "Build online shopping platform", "2024-01-01", "2024-06-30", System_Administrator);
        Project demoProject2 = new Project("PROJ002", "Mobile Banking App", "Develop secure banking application", "2024-02-01", "2024-08-31", System_Administrator);

        All_Projects.add(demoProject1);
        All_Projects.add(demoProject2);

        // Add some demo tasks
        Task task1 = new Task("TASK001", "Design Homepage", "Create homepage UI design", "UIUX_Designer");
        Task task2 = new Task("TASK002", "Develop Login API", "Implement user authentication", "Backend_Engineer");

        demoProject1.Add_Task(task1);
        demoProject1.Add_Task(task2);

        System_Logger.Log_System("Demo data added with 2 projects and 2 tasks");
    }

    public static boolean Is_Pre_Registered_Employee(String Employee_Id) {
        for (String id : Pre_Registered_Ids) {
            if (id.equals(Employee_Id)) {
                return true;
            }
        }
        return false;
    }

    static boolean Is_User_Id_Already_Taken(String User_Id) {
        for (Client client : All_Clients) {
            if (client.User_Id.equals(User_Id)) {
                return true;
            }
        }
        for (user employee : All_Employees) {
            if (employee.User_Id.equals(User_Id)) {
                return true;
            }
        }
        return false;
    }


    user Find_User_By_Id(String User_Id_To_Find) {
        for (Client client : All_Clients) {
            if (client.User_Id.equals(User_Id_To_Find)) {
                return client;
            }
        }
        for (user employee : All_Employees) {
            if (employee.User_Id.equals(User_Id_To_Find)) {
                return employee;
            }
        }
        return null;
    }

    public user Find_User_By_Username(String Username_To_Find) {
        for (Client client : All_Clients) {
            if (client.User_Username.equals(Username_To_Find)) {
                return client;
            }
        }
        for (user employee : All_Employees) {
            if (employee.User_Username.equals(Username_To_Find)) {
                return employee;
            }
        }
        return null;
    }

//    void User_Login() {
//        try {
//            System.out.println("\n=== User Login ===");
//            System.out.print("Enter Your Username: ");
//            String Input_Username = User_Input.nextLine();
//            System.out.print("Enter Your Password: ");
//            String Input_Password = User_Input.nextLine();
//
//            boolean Login_Successful = false;
//            user Logged_In_User = null;
//
//            // Check clients
//            for (Client client : All_Clients) {
//                if (client.User_Username.equals(Input_Username) && client.User_Password.equals(Input_Password)) {
//                    Login_Successful = true;
//                    Logged_In_User = client;
//                    System_Logger.Log_Activity("Client Logged In: " + client.User_Username);
//                    break;
//                }
//            }
//
//            // Check employees if not found in clients
//            if (!Login_Successful) {
//                for (user employee : All_Employees) {
//                    if (employee.User_Username.equals(Input_Username) && employee.User_Password.equals(Input_Password)) {
//                        Login_Successful = true;
//                        Logged_In_User = employee;
//                        System_Logger.Log_Activity(employee.getClass().getSimpleName() + " Logged In: " + employee.User_Username);
//                        break;
//                    }
//                }
//            }
//
//            if (Login_Successful && Logged_In_User != null) {
//                Route_To_User_Dashboard(Logged_In_User);
//            } else {
//                System.out.println("Login Failed! Incorrect Username Or Password.");
//                System_Logger.Log_Error("Failed login attempt for username: " + Input_Username);
//            }
//        } catch (Exception error) {
//            System.out.println("Error During Login Process: " + error.getMessage());
//        }
//    }
   DBconn.DatabaseConnection DB = new DBconn.DatabaseConnection();

    public boolean User_Signup(String username, String password, int roleId) {

        // LOGIN table ka SQL
        String insertSql = "INSERT INTO LOGIN (UserId, Username, Password, User_Role) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection()) {

            // New User ID generate karega (e.g., BE001 / QA001 / etc)
            String newUserId = generateNewUserId(conn);

            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {

                ps.setString(1, newUserId);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.setInt(4, roleId);

                int rowsAffected = ps.executeUpdate();

                return rowsAffected > 0; // True if insertion successful
            }

        } catch (SQLException e) {
            System.err.println("Database Error during Signup: " + e.getMessage());
            return false;
        }
    }


    private String generateNewUserId(Connection conn) throws SQLException {

        String lastId = null;

        String query = "SELECT TOP 1 UserId FROM LOGIN ORDER BY UserId DESC";

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                lastId = rs.getString("UserId");
            }
        }

        // If table empty → first user
        if (lastId == null) {
            return "U001";
        }

        // Extract numeric part (e.g., from IUX001 → 001)
        String numberPart = lastId.replaceAll("\\D+", ""); // Keep only digits

        // Convert to int
        int idNum = Integer.parseInt(numberPart);
        idNum++;

        // Extract prefix (letters only)
        String prefix = lastId.replaceAll("\\d+", ""); // Keep only letters

        // Return next ID
        return prefix + String.format("%03d", idNum);
    }

    public boolean User_Login(String username, String password, int expectedRoleId) {

        String loginSql = "SELECT Password, User_Role FROM LOGIN WHERE Username = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(loginSql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String dbPassword = rs.getString("Password");
                    int actualUserRole = rs.getInt("User_Role");

                    // Password match
                    if (!password.equals(dbPassword)) {
                        System.out.println("Login failed: Incorrect password.");
                        return false;
                    }

                    // Role match
                    if (expectedRoleId != actualUserRole) {
                        System.out.println("Login failed: Role ID does not match actual user role (" + actualUserRole + ").");
                        return false;
                    }

                    System.out.println("Login successful for user: " + username + " with Role: " + actualUserRole);
                    return true;

                } else {
                    System.out.println("Login failed: Username not found.");
                    return false;
                }
            }

        } catch (SQLException e) {
            System.err.println("Database Error during Login: " + e.getMessage());
            return false;
        }
    }



    void User_Password_Reset() {
        try {
            System.out.println("\n=== Password Reset ===");
            System.out.print("Enter Your Username: ");
            String Input_Username = User_Input.nextLine();
            System.out.print("Enter Your User ID: ");
            String Input_User_Id = User_Input.nextLine();

            boolean User_Found = false;

            // Check clients
            for (Client client : All_Clients) {
                if (client.User_Username.equals(Input_Username) && client.User_Id.equals(Input_User_Id)) {
                    System.out.print("Enter Your New Password: ");
                    String New_Password = User_Input.nextLine();
                    client.Set_Password(New_Password);
                    System_Logger.Log_Activity("Client Password Changed: " + client.User_Username);
                    System.out.println("Password Changed Successfully!");
                    User_Found = true;
                    break;
                }
            }

            // Check employees if not found in clients
            if (!User_Found) {
                for (user employee : All_Employees) {
                    if (employee.User_Username.equals(Input_Username) && employee.User_Id.equals(Input_User_Id)) {
                        System.out.print("Enter Your New Password: ");
                        String New_Password = User_Input.nextLine();
                        employee.Set_Password(New_Password);
                        System_Logger.Log_Activity(employee.getClass().getSimpleName() + " Password Changed: " + employee.User_Username);
                        System.out.println("Password Changed Successfully!");
                        User_Found = true;
                        break;
                    }
                }
            }

            if (!User_Found) {
                System.out.println("User Not Found! Please Check Your Username And ID.");
            }
        } catch (Exception error) {
            System.out.println("Error During Password Reset: " + error.getMessage());
        }
    }

    public void password_reset(){
        System.out.println("Enter your username");
        String usrname = User_Input.nextLine();
        System.out.println("Enter new password");
        String password = User_Input.nextLine();
        DB.Reset_Password(usrname , password);
    }



    public void Route_To_User_Dashboard(user userToRoute) {
        if (userToRoute == null) {
            System.out.println("Invalid user.");
            return;
        }

        String userRole = userToRoute.getClass().getSimpleName();

        switch (userRole) {
            case "Admin":
                Admin_Dashboard_Menu((Admin) userToRoute);
                break;
            case "Project_Manager":
                Project_Manager_Dashboard_Menu((Project_Manager) userToRoute);
                break;
            case "Coder":
                Coder_Dashboard_Menu((Coder) userToRoute);
                break;
            case "Software_Architect":
                Software_Architect_Dashboard_Menu((Software_Architect) userToRoute);
                break;
            case "Backend_Engineer":
                Backend_Engineer_Dashboard_Menu((Backend_Engineer) userToRoute);
                break;
            case "Frontend_Engineer":
                Frontend_Engineer_Dashboard_Menu((Frontend_Engineer) userToRoute);
                break;
            case "Fullstack_Engineer":
                Fullstack_Engineer_Dashboard_Menu((Fullstack_Engineer) userToRoute);
                break;
            case "Mobile_Developer":
                Mobile_Developer_Dashboard_Menu((Mobile_Developer) userToRoute);
                break;
            case "Site_Reliability_Engineer":
                Site_Reliability_Engineer_Dashboard_Menu((Site_Reliability_Engineer) userToRoute);
                break;
            case "QA_Engineer":
                QA_Engineer_Dashboard_Menu((QA_Engineer) userToRoute);
                break;
            case "Automation_Test_Engineer":
                Automation_Test_Engineer_Dashboard_Menu((Automation_Test_Engineer) userToRoute);
                break;
            case "UIUX_Designer":
                UIUX_Designer_Dashboard_Menu((UIUX_Designer) userToRoute);
                break;
            case "Data_Engineer":
                Data_Engineer_Dashboard_Menu((Data_Engineer) userToRoute);
                break;
            case "Data_Scientist":
                Data_Scientist_Dashboard_Menu((Data_Scientist) userToRoute);
                break;
            case "Machine_Learning_Engineer":
                Machine_Learning_Engineer_Dashboard_Menu((Machine_Learning_Engineer) userToRoute);
                break;
            case "Client":
                Client_Dashboard_Menu((Client) userToRoute);
                break;
            default:
                System.out.println("Dashboard Not Available For This Role: " + userRole);
                break;
        }
    }

    void Display_All_Projects() {
        System.out.println("\n=== All Projects ===");
        if (All_Projects.isEmpty()) {
            System.out.println("No projects available.");
        } else {
//            for (Project project : All_Projects) {
//                project.Show_Project_Basic();
//
//            }
            DB.View_All_Projects();

        }
    }
    void Display_client_Projects1() {
        System.out.println("\n=== All Projects ===");
        System.out.println("Enter your username");
        String username = User_Input.nextLine();

        DB.View_client_Projects_Data1(username);
    }



    public void Client_Dashboard_Menu(Client Client_user) {
        while (true) {
            System.out.println("\n=== Client Dashboard ===");
            System.out.println("Welcome: " + Client_user.User_Username);
            System.out.println("1. View Project Details");
            System.out.println("2. Send Message to Project Manager");
            System.out.println("3. View My Messages");
            System.out.println("4. Update Password");
            System.out.println("5. Logout");
            System.out.print("Please Choose An Option: ");

            int Client_Choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (Client_Choice) {

                case 1: // View all projects
                    DB.Display_All_Projects();
                    break;

                case 2: // View project details by id
                    System.out.print("Enter Project ID: ");
                    String Project_Id = User_Input.nextLine();

                    DB.Display_Project_By_ID(Project_Id); // UPDATED METHOD
                    break;

                case 3: // Send Message
                    System.out.print("Enter Project Manager Username: ");
                    String PM_Username = User_Input.nextLine();

                    System.out.print("Enter Your Message: ");
                    String Message_Content = User_Input.nextLine();

                    DB.Send_Message(Client_user.User_Username, PM_Username, Message_Content);
                    break;

                case 4: // Update Password
                    System.out.print("Enter New Password: ");
                    String newPass = User_Input.nextLine();
                    DB.Reset_Password(Client_user.User_Username, newPass);
                    System.out.println("Password Updated Successfully!");
                    break;

                case 5: // Logout
                    DB.Log_System("Client Logged Out: " + Client_user.User_Username);
                    return;

                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }



    // Admin Dashboard
    public void Admin_Dashboard_Menu(Admin Admin_User) {
        while (true) {
            System.out.println("\n=== Administrator Dashboard ===");
            System.out.println("Welcome: " + Admin_User.User_Username);
            System.out.println("1. Create New Project");
            System.out.println("2. Assign Project Manager");
            System.out.println("3. Assign Team to Project");
            System.out.println("4. View All Projects");
            System.out.println("5. View All Users");
            System.out.println("6. System Statistics");
            System.out.println("7. Reset Password");
            System.out.println("8. Send System Announcement");
            System.out.println("9. Logout");
            System.out.print("Please Choose An Option: ");

            int choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (choice) {

                case 1:
                    DB.Create_New_Project_DB();
                    break;

                case 2:
                    DB.Assign_Project_Manager_DB();
                    break;

                case 3:
                    DB.Assign_Team_To_Project_DB();
                    break;

                case 4:
                    DB.Display_All_Projects();
                    break;

                case 5:
                    DB.Display_All_Users();
                    break;

                case 6:
                    DB.Display_System_Statistics();
                    break;

                case 7:
                    password_reset1();
                    break;

                case 8:
                    DB.Send_System_Announcement_DB(Admin_User.User_Username);
                    break;

                case 9:
                    DB.Log_System("Administrator Logged Out: " + Admin_User.User_Username);
                    return;

                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }


    public void password_reset1(){
        System.out.println("Enter your username");
        String usrname = User_Input.nextLine();
        System.out.println("Enter new password");
        String password = User_Input.nextLine();
        DB.Reset_Password(usrname , password);
    }

    // Project Manager Dashboard
    public void Project_Manager_Dashboard_Menu(Project_Manager PM_User) {
        while (true) {
            System.out.println("\n=== Project Manager Dashboard ===");
            System.out.println("Welcome: " + PM_User.User_Username);
            System.out.println("1. Divide Project Into Tasks");
            System.out.println("2. Assign Tasks to Team");
            System.out.println("3. Reassign Tasks");
            System.out.println("4. View My Projects");
            System.out.println("5. View Project Progress");
            System.out.println("6. View Team Performance");
            System.out.println("7. Send Weekly Report");
            System.out.println("8. Communicate with Team");
            System.out.println("9. Update Project Status");
            System.out.println("10. Logout");
            System.out.print("Please Choose An Option: ");

            int choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (choice) {

                case 1:
                    DB.Create_Task_DB(PM_User.User_Username);
                    break;

                case 2:
                    DB.Assign_Task_DB(PM_User.User_Username);
                    break;

                case 3:
                    DB.Reassign_Task_DB(PM_User.User_Username);
                    break;

                case 4:
                    DB.Display_Projects_For_Manager_DB(PM_User.User_Username);
                    break;

                case 5:
                    DB.View_Project_Progress_DB(PM_User.User_Username);
                    break;

                case 6:
                    DB.View_Team_Performance_DB(PM_User.User_Username);
                    break;

                case 7:
                    DB.Send_Weekly_Report_DB(PM_User.User_Username);
                    break;

                case 8:
                    DB.Communicate_With_Team_DB(PM_User.User_Username);
                    break;

                case 9:
                    DB.Update_Project_Status_DB(PM_User.User_Username);
                    break;

                case 10:
                    DB.Log_System("Project Manager Logged Out: " + PM_User.User_Username);
                    return;

                default:
                    System.out.println("Invalid Option! Try Again.");
                    break;
            }
        }
    }



    // Coder Dashboard
    // Coder Dashboard
    public void Coder_Dashboard_Menu(Coder Coder_User) {

        while (true) {
            System.out.println("\n=== Coder Dashboard ===");
            System.out.println("Welcome: " + Coder_User.User_Username);
            System.out.println("1. View My Tasks");
            System.out.println("2. Upload Code Work");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. View Project Details");
            System.out.println("5. Update Task Progress");
            System.out.println("6. View My Skills");
            System.out.println("7. Add Technical Skill");
            System.out.println("8. Logout");
            System.out.print("Please Choose an Option: ");

            int Coder_Choice = User_Input.nextInt();
            User_Input.nextLine(); // clear buffer

            switch (Coder_Choice) {

                case 1:
                    // Show tasks assigned to this coder
                    Display_Tasks_For_User(Coder_User);
                    break;

                case 2:
                    // Upload code or file submission
                    Upload_Code_Work(Coder_User);
                    break;

                case 3:
                    // Mark a task as completed
                    System.out.print("Enter Project ID to mark task completed: ");
                    String P_ID = User_Input.nextLine();
                    DB.mark_as_completed_by_coder(P_ID);
                    break;

                case 4:
                    // View projects coder is assigned to
                    View_Assigned_Projects(Coder_User);

                    System.out.print("Enter your Coder ID to view project details: ");
                    String C_ID = User_Input.nextLine();
                    DB.View_project_by_coder(C_ID);
                    break;

                case 5:
                    // Update progress (like 20%, 50%, 100%)
                    Update_Task_Progress(Coder_User);
                    break;

                case 6:
                    // View coder skills
                    System.out.print("Enter coder username: ");
                    String username = User_Input.nextLine();
                    DB.view_coder_skill(username);
                    break;

                case 7:
                    // Add new skill
                    System.out.print("Enter coder username: ");
                    String usrname = User_Input.nextLine();

                    System.out.print("Enter New Technical Skill: ");
                    String New_Skill = User_Input.nextLine();

                    DB.add_coder_skill(New_Skill, usrname);
                    break;

                case 8:
                    System_Logger.Log_Activity("Coder Logged Out: " + Coder_User.User_Username);
                    return;

                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }


    // QA Engineer Dashboard
    public void QA_Engineer_Dashboard_Menu(QA_Engineer QA_User) {
        while (true) {
            System.out.println("\n=== QA Engineer Dashboard ===");
            System.out.println("Welcome: " + QA_User.User_Username);
            System.out.println("1. View My Testing Tasks");
            System.out.println("2. Report Bugs");
            System.out.println("3. Mark Task as Tested");
            System.out.println("4. View Test Reports");
            System.out.println("5. Update Testing Methodologies");
            System.out.println("6. Logout");
            System.out.print("Please Choose An Option: ");

            int QA_Choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (QA_Choice) {
                case 1:
//                    Display_Tasks_For_User(QA_User);
                    System.out.println("Enter your username ");
                    String usrname = User_Input.nextLine();
                    DB.View_My_Testing_Tasks(usrname);
                    break;
                case 2:
                    Report_Bugs(QA_User);

                    System.out.println("Enter your username");
                    String b = User_Input.nextLine();
                    System.out.println("Enter project id");
                    String a = User_Input.nextLine();
                    System.out.println("Enter bug report");
                    String d = User_Input.nextLine();
                    DB.Report_Bugs(b,a,d);
                    break;
                case 3:
                    Mark_Task_As_Tested(QA_User);
                    break;
                case 4:
                    View_Test_Reports(QA_User);
                    System.out.println("Enter your username");
                    String b2 = User_Input.nextLine();
                    System.out.println("Enter project id");
                    String a2 = User_Input.nextLine();
                    DB.View_Test_Reports(a2,b2);
                    break;
                case 5:
                    System.out.print("Enter New Testing Methodology: ");
                    String New_Methodology = User_Input.nextLine();
                    QA_User.Add_Testing_Methodology(New_Methodology);
                    System.out.println("Methodology added successfully!");
                    break;
                case 6:
                    System_Logger.Log_Activity("QA Engineer Logged Out: " + QA_User.User_Username);
                    return;
                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }

    // Software Architect Dashboard
    public void Software_Architect_Dashboard_Menu(Software_Architect SA_User) {
        while (true) {
            System.out.println("\n=== Software Architect Dashboard ===");
            System.out.println("Welcome: " + SA_User.User_Username);
            System.out.println("1. View Architecture Tasks");
            System.out.println("2. Design System Architecture");
            System.out.println("3. Review Technical Designs");
            System.out.println("4. Add Specialization");
            System.out.println("5. View My Specializations");
            System.out.println("6. Logout");
            System.out.print("Please Choose An Option: ");

            int SA_Choice = User_Input.nextInt();
            User_Input.nextLine(); // consume newline

            switch (SA_Choice) {

                case 1: // View tasks
                    DB.View_Architecture_Tasks(SA_User.User_Username);
                    break;

                case 2: // Save architecture design as a task
                    System.out.print("Enter Architecture Design Notes: ");
                    String design = User_Input.nextLine();
                    DB.Save_Architecture_Task(SA_User.User_Username, "Design: " + design);
                    System.out.println("Architecture design saved!");
                    break;

                case 3: // Save review notes as a task
                    System.out.print("Enter Design Review Notes: ");
                    String review = User_Input.nextLine();
                    DB.Save_Architecture_Task(SA_User.User_Username, "Review: " + review);
                    System.out.println("Design review saved!");
                    break;

                case 4: // Add specialization
                    System.out.print("Enter New Specialization: ");
                    String spec = User_Input.nextLine();
                    DB.Save_Specialization(SA_User.User_Username, spec);
                    break;

                case 5: // View specializations
                    DB.View_Specializations(SA_User.User_Username);
                    break;

                case 6: // Logout
                    System_Logger.Log_Activity("Software Architect Logged Out: " + SA_User.User_Username);
                    return;

                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }

    // Backend Engineer Dashboard
    public void Backend_Engineer_Dashboard_Menu(Backend_Engineer BE_User) {
        while (true) {
            System.out.println("\n=== Backend Engineer Dashboard ===");
            System.out.println("Welcome: " + BE_User.User_Username);
            System.out.println("1. View My Backend Tasks");
            System.out.println("2. Develop API");
            System.out.println("3. Database Optimization");
            System.out.println("4. Add Backend Technology");
            System.out.println("5. View My Skills");
            System.out.println("6. Logout");
            System.out.print("Please Choose An Option: ");

            int BE_Choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (BE_Choice) {
                case 1:
                    Display_Tasks_For_User(BE_User);
                    break;
                case 2:
                    System.out.print("Enter API Development Details: ");
                    String api = User_Input.nextLine();
                    DB.Save_Backend_Task(BE_User.User_Username, api, "API Development");
                    break;
                case 3:
                    System.out.print("Enter Database Optimization Notes: ");
                    String db = User_Input.nextLine();
                    DB.Save_Backend_Task(BE_User.User_Username, db, "Database Optimization");
                    break;
                case 4:
                    System.out.print("Enter New Backend Technology: ");
                    String tech = User_Input.nextLine();
                    DB.Save_Backend_Technology(BE_User.User_Username, tech);
                    break;
                case 5:
                    DB.View_Backend_Technologies(BE_User.User_Username);
                    break;
                case 6:
                    System_Logger.Log_Activity("Backend Engineer Logged Out: " + BE_User.User_Username);
                    return;
                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }


    // Frontend Engineer Dashboard
    public void Frontend_Engineer_Dashboard_Menu(Frontend_Engineer FE_User) {
        while (true) {
            System.out.println("\n=== Frontend Engineer Dashboard ===");
            System.out.println("Welcome: " + FE_User.User_Username);
            System.out.println("1. View My Frontend Tasks");
            System.out.println("2. Develop UI Components");
            System.out.println("3. Responsive Design");
            System.out.println("4. Add Frontend Framework");
            System.out.println("5. View My Skills");
            System.out.println("6. Logout");
            System.out.print("Please Choose An Option: ");

            int FE_Choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (FE_Choice) {
                case 1:
                    Display_Tasks_For_User(FE_User);
                    break;
                case 2:
                    System.out.print("Enter UI Component Details: ");
                    String ui = User_Input.nextLine();
                    DB.Save_Frontend_Task(FE_User.User_Username, ui, "UI Component");
                    break;
                case 3:
                    System.out.print("Enter Responsive Design Notes: ");
                    String design = User_Input.nextLine();
                    DB.Save_Frontend_Task(FE_User.User_Username, design, "Responsive Design");
                    break;
                case 4:
                    System.out.print("Enter New Frontend Framework: ");
                    String framework = User_Input.nextLine();
                    DB.Save_Frontend_Framework(FE_User.User_Username, framework);
                    break;
                case 5:
                    DB.View_Frontend_Frameworks(FE_User.User_Username);
                    break;
                case 6:
                    System_Logger.Log_Activity("Frontend Engineer Logged Out: " + FE_User.User_Username);
                    return;
                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }

    // Fullstack Engineer Dashboard
    public void Fullstack_Engineer_Dashboard_Menu(Fullstack_Engineer FSE_User) {
        while (true) {
            System.out.println("\n=== Fullstack Engineer Dashboard ===");
            System.out.println("Welcome: " + FSE_User.User_Username);
            System.out.println("1. View My Fullstack Tasks");
            System.out.println("2. End-to-End Feature Development");
            System.out.println("3. Code Integration");
            System.out.println("4. Add Fullstack Technology");
            System.out.println("5. View My Skills");
            System.out.println("6. Logout");
            System.out.print("Please Choose An Option: ");

            int FSE_Choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (FSE_Choice) {
                case 1:
                    Display_Tasks_For_User(FSE_User);
                    break;
                case 2:
                    System.out.print("Enter Feature Development Details: ");
                    String feature = User_Input.nextLine();
                    DB.Save_Fullstack_Task(FSE_User.User_Username, feature, "Feature Development");
                    break;
                case 3:
                    System.out.print("Enter Integration Notes: ");
                    String integration = User_Input.nextLine();
                    DB.Save_Fullstack_Task(FSE_User.User_Username, integration, "Code Integration");
                    break;
                case 4:
                    System.out.print("Enter New Fullstack Technology: ");
                    String tech = User_Input.nextLine();
                    DB.Save_Fullstack_Technology(FSE_User.User_Username, tech);
                    break;
                case 5:
                    DB.View_Fullstack_Technologies(FSE_User.User_Username);
                    break;
                case 6:
                    System_Logger.Log_Activity("Fullstack Engineer Logged Out: " + FSE_User.User_Username);
                    return;
                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }

    // Mobile Developer Dashboard
    void Mobile_Developer_Dashboard_Menu(Mobile_Developer MD_User) {
        while (true) {
            System.out.println("\n=== Mobile Developer Dashboard ===");
            System.out.println("Welcome: " + MD_User.User_Username);
            System.out.println("1. View My Mobile Tasks");
            System.out.println("2. Develop Mobile Features");
            System.out.println("3. App Performance Optimization");
            System.out.println("4. Add Mobile Platform");
            System.out.println("5. View My Platforms");
            System.out.println("6. Logout");
            System.out.print("Please Choose An Option: ");

            int MD_Choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (MD_Choice) {
                case 1:
                    DB.View_Mobile_Tasks(MD_User.User_Username);
                    break;
                case 2:
                    System.out.print("Enter feature details: ");
                    String feature = User_Input.nextLine();
                    DB.Save_Mobile_Feature(MD_User.User_Username, null, "New Feature", feature);
                    break;
                case 3:
                    System.out.print("Enter Performance Optimization Notes: ");
                    String performance = User_Input.nextLine();
                    System_Logger.Log_Activity(MD_User.User_Username + " optimized app performance: " + performance);
                    System.out.println("Performance optimization completed!");
                    break;
                case 4:
                    System.out.print("Enter platform name: ");
                    String platform = User_Input.nextLine();
                    DB.Save_Mobile_Platform(MD_User.User_Username, platform);
                    break;
                case 5:
                    DB.View_Mobile_Platforms(MD_User.User_Username);
                    break;
                case 6:
                    System_Logger.Log_Activity("Mobile Developer Logged Out: " + MD_User.User_Username);
                    return;
                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }

    // Site Reliability Engineer Dashboard
    public void Site_Reliability_Engineer_Dashboard_Menu(Site_Reliability_Engineer SRE_User) {
        while (true) {
            System.out.println("\n=== Site Reliability Engineer Dashboard ===");
            System.out.println("Welcome: " + SRE_User.User_Username);
            System.out.println("1. View My SRE Tasks");
            System.out.println("2. Monitor System Health");
            System.out.println("3. Setup Monitoring Tools");
            System.out.println("4. Add SRE Tool");
            System.out.println("5. View My Tools");
            System.out.println("6. Logout");
            System.out.print("Please Choose An Option: ");

            int SRE_Choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (SRE_Choice) {
                case 1:
                    Display_Tasks_For_User(SRE_User);
                    break;
                case 2:
                    System.out.print("Enter System Health Monitoring Notes: ");
                    String health = User_Input.nextLine();
                    System_Logger.Log_Activity(SRE_User.User_Username + " monitored system health: " + health);
                    System.out.println("System health monitoring completed!");
                    break;
                case 3:
                    System.out.print("Enter Monitoring Setup Details: ");
                    String setup = User_Input.nextLine();
                    System_Logger.Log_Activity(SRE_User.User_Username + " setup monitoring: " + setup);
                    System.out.println("Monitoring setup completed!");
                    break;
                case 4:
                    System.out.print("Enter New SRE Tool: ");
                    String tool = User_Input.nextLine();
                    SRE_User.Add_SRE_Tool(tool);
                    System.out.println("Tool added successfully!");
                    break;
                case 5:
                    DB.View_SRE_Tools(SRE_User.User_Username); // Uses the method above
                    break;
                case 6:
                    System_Logger.Log_Activity("Site Reliability Engineer Logged Out: " + SRE_User.User_Username);
                    return;
                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }

    // Automation Test Engineer Dashboard
    // Automation Test Engineer Dashboard
    public void Automation_Test_Engineer_Dashboard_Menu(Automation_Test_Engineer ATE_User) {
        while (true) {
            System.out.println("\n=== Automation Test Engineer Dashboard ===");
            System.out.println("Welcome: " + ATE_User.User_Username);
            System.out.println("1. View My Automation Tasks");
            System.out.println("2. Create Test Scripts");
            System.out.println("3. Run Automated Tests");
            System.out.println("4. Add Automation Tool");
            System.out.println("5. View My Tools");
            System.out.println("6. Logout");
            System.out.print("Please Choose An Option: ");

            int ATE_Choice = User_Input.nextInt();
            User_Input.nextLine(); // Consume newline

            switch (ATE_Choice) {
                case 1:
                    // Load all automation tasks assigned to this engineer
                    DB.Load_ATE_Tasks(ATE_User.User_Id);
                    break;

                case 2:
                    System.out.print("Enter Test Script Details: ");
                    String script = User_Input.nextLine();
                    DB.Save_Test_Script(ATE_User.User_Id, script);
                    break;

                case 3:
                    System.out.print("Enter Test Run Results: ");
                    String results = User_Input.nextLine();
                    DB.Save_Test_Results(ATE_User.User_Id, results);
                    break;

                case 4:
                    System.out.print("Enter New Automation Tool: ");
                    String tool = User_Input.nextLine();
                    DB.Save_Automation_Tool(ATE_User.User_Id, tool);
                    break;

                case 5:
                    DB.Display_Automation_Tools(ATE_User.User_Id);
                    break;

                case 6:
                    System_Logger.Log_Activity("Automation Test Engineer Logged Out: " + ATE_User.User_Username);
                    return;

                default:
                    System.out.println("Invalid Option! Please Try Again.");
                    break;
            }
        }
    }








    // UI/UX Designer Dashboard
    public void UIUX_Designer_Dashboard_Menu(UIUX_Designer user) {

        while (true) {
            System.out.println("\n=== UI/UX Designer Dashboard ===");
            System.out.println("Welcome: " + user.User_Username);
            System.out.println("1. View Design Tasks");
            System.out.println("2. Create Wireframe");
            System.out.println("3. Design User Interface");
            System.out.println("4. Add Design Tool");
            System.out.println("5. View My Tools");
            System.out.println("6. Logout");
            System.out.print("Choose Option: ");

            int choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (choice) {

                case 1:
                    DB.Load_UIUX_Tasks(user.User_ID);
                    break;

                case 2:
                    System.out.print("Enter Wireframe Details: ");
                    String wf = User_Input.nextLine();
                    DB.Save_Wireframe(user.User_ID, wf);
                    break;

                case 3:
                    System.out.print("Enter UI Design Details: ");
                    String ui = User_Input.nextLine();
                    DB.Save_Wireframe(user.User_ID, "UI Design: " + ui);
                    System.out.println("UI Design Saved!");
                    break;

                case 4:
                    System.out.print("Enter New Tool Name: ");
                    String tool = User_Input.nextLine();
                    DB.Save_Design_Tool(user.User_ID, tool);
                    break;

                case 5:
                    DB.Display_Design_Tools_DB(user.User_ID);
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Option!");
            }
        }
    }



    // Data Engineer Dashboard
    public void Data_Engineer_Dashboard_Menu(Data_Engineer DE_User) {
        while (true) {
            System.out.println("\n=== Data Engineer Dashboard ===");
            System.out.println("Welcome: " + DE_User.User_Username);
            System.out.println("1. View Data Tasks");
            System.out.println("2. Build Data Pipelines");
            System.out.println("3. Optimize Data Storage");
            System.out.println("4. Add Data Technology");
            System.out.println("5. View My Technologies");
            System.out.println("6. Logout");
            System.out.print("Please Choose An Option: ");

            int DE_Choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (DE_Choice) {
                case 1:
                    DB.loadTasks(DE_User.User_Username);
                    break;
                case 2:
                    System.out.print("Enter Wireframe Details: ");
                    String wf = User_Input.nextLine();
                    DB.saveWireframe(DE_User.User_Username, wf);
                    break;
                case 3:
                    System.out.print("Enter UI Design Details: ");
                    String ui = User_Input.nextLine();
                    DB.saveWireframe(DE_User.User_Username, "UI Design: " + ui);
                    break;
                case 4:
                    System.out.print("Enter New Tool Name: ");
                    String tool = User_Input.nextLine();
                    DB.saveDesignTool(DE_User.User_Username, tool);
                    break;
                case 5:
                    DB.displayDesignTools(DE_User.User_Username);
                    break;
                case 6:
                    System.out.println("Logged out!");
                    return;
                default:
                    System.out.println("Invalid Option!");
            }
        }
        }


    // Data Scientist Dashboard
    public void Data_Scientist_Dashboard_Menu(Data_Scientist user) {

        while (true) {
            System.out.println("\n=== Data Scientist Dashboard ===");
            System.out.println("Welcome: " + user.User_Username);
            System.out.println("1. View Data Science Tasks");
            System.out.println("2. Analyze Data Patterns");
            System.out.println("3. Build ML Models");
            System.out.println("4. Add ML Algorithm");
            System.out.println("5. View My Algorithms");
            System.out.println("6. Logout");
            System.out.print("Choose Option: ");

            int choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (choice) {

                case 1:
                    DB.Load_DS_Tasks(user.User_Id);
                    break;

                case 2:
                    System.out.print("Enter Data Analysis Details: ");
                    String analysis = User_Input.nextLine();
                    System.out.println("Data Analysis Saved: " + analysis);
                    break;

                case 3:
                    System.out.print("Enter ML Model Details: ");
                    String model = User_Input.nextLine();
                    System.out.println("Model Built Successfully: " + model);
                    break;

                case 4:
                    System.out.print("Enter New ML Algorithm: ");
                    String algo = User_Input.nextLine();
                    DB.Save_ML_Algorithm(user.User_Id, algo);
                    break;

                case 5:
                    DB.Display_ML_Algorithms(user.User_Id);
                    break;

                case 6:
                    System.out.println("Logged Out!");
                    return;

                default:
                    System.out.println("Invalid Option!");
            }
        }
    }






    // Machine Learning Engineer Dashboard
    public void Machine_Learning_Engineer_Dashboard_Menu(Machine_Learning_Engineer user) {

        while (true) {
            System.out.println("\n=== Machine Learning Engineer Dashboard ===");
            System.out.println("Welcome: " + user.User_Username);
            System.out.println("1. View ML Tasks");
            System.out.println("2. Implement ML Algorithms");
            System.out.println("3. Optimize ML Models");
            System.out.println("4. Add ML Framework");
            System.out.println("5. View My Frameworks");
            System.out.println("6. Logout");
            System.out.print("Choose Option: ");

            int choice = User_Input.nextInt();
            User_Input.nextLine();

            switch (choice) {

                case 1:
                    DB.Load_MLE_Tasks(user.User_Id); // fetch tasks from database
                    break;

                case 2:
                    System.out.print("Enter ML Implementation Details: ");
                    String impl = User_Input.nextLine();
                    DB.Save_ML_Implementation(user.User_Id, impl); // save implementation
                    System.out.println("ML Implementation Saved!");
                    break;

                case 3:
                    System.out.print("Enter Model Optimization Details: ");
                    String opt = User_Input.nextLine();
                    DB.Save_Model_Optimization(user.User_Id, opt); // save optimization
                    System.out.println("Model Optimization Saved!");
                    break;

                case 4:
                    System.out.print("Enter New ML Framework: ");
                    String framework = User_Input.nextLine();
                    DB.Save_ML_Framework(user.User_Id, framework);
                    System.out.println("Framework added successfully!");
                    break;

                case 5:
                    DB.Display_ML_Frameworks(user.User_Id);
                    break;

                case 6:
                    System.out.println("Logged Out!");
                    return;

                default:
                    System.out.println("Invalid Option!");
            }
        }
    }


    //    // Implemented functionality methods
//    void Update_User_Profile(Client user) {
//        System.out.println("\n=== Update Profile ===");
//        System.out.print("Enter New Username: ");
//        String newUsername = User_Input.nextLine();
//        System.out.print("Enter New Email: ");
//        String newEmail = User_Input.nextLine();
//
//        user.User_Username = newUsername;
//        user.User_Email = newEmail;
//
//        System.out.println("Profile updated successfully!");
//        System_Logger.Log_Activity("User profile updated: " + newUsername);
//    }
void Update_PASSWORD() {
    System.out.println("\n=== RESET PASSWORD ===");
    System.out.print("Enter your username: ");
    String usrname= User_Input.nextLine();
    System.out.print("Enter New Password: ");
    String newPassword= User_Input.nextLine();

    DB.reset_client_password(usrname, newPassword);


}

    void Create_New_Project(Admin admin) {
        System.out.println("\n=== Create New Project ===");
        System.out.print("Enter Project ID: ");
        String projectId = User_Input.nextLine();
        System.out.print("Enter Project Name: ");
        String projectName = User_Input.nextLine();
        System.out.print("Enter Project Description: ");
        String projectDesc = User_Input.nextLine();
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDate = User_Input.nextLine();
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDate = User_Input.nextLine();

        Project newProject = new Project(projectId, projectName, projectDesc, startDate, endDate, admin);
        All_Projects.add(newProject);

        System.out.println("Project created successfully!");
        System_Logger.Log_Activity("New project created: " + projectName + " by " + admin.User_Username);
    }

    void Assign_Project_Manager_To_Project() {
        System.out.println("\n=== Assign Project Manager ===");
        System.out.print("Enter Project ID: ");
        String projectId = User_Input.nextLine();
        System.out.print("Enter Project Manager Username: ");
        String pmUsername = User_Input.nextLine();

        Project project = Find_Project_By_Id(projectId);
        user pm = Find_User_By_Username(pmUsername);

        if (project != null && pm instanceof Project_Manager) {
            project.Project_Manager = (Project_Manager) pm;
            ((Project_Manager) pm).Add_Managed_Project(projectId);
            System.out.println("Project Manager assigned successfully!");
            System_Logger.Log_Activity("Project Manager " + pmUsername + " assigned to project " + projectId);
        } else {
            System.out.println("Project or Project Manager not found!");
        }
    }
//
//    void Assign_Team_To_Project() {
//        System.out.println("\n=== Assign Team to Project ===");
//        System.out.print("Enter Project ID: ");
//        String projectId = User_Input.nextLine();
//        System.out.print("Enter Team Member Username: ");
//        String memberUsername = User_Input.nextLine();
//
//        Project project = Find_Project_By_Id(projectId);
//        user member = Find_User_By_Username(memberUsername);
//
//        if (project != null && member != null) {
//            project.Add_Team_Member(member);
//            System_Logger.Log_Activity("Team member " + memberUsername + " assigned to project " + projectId);
//        } else {
//            System.out.println("Project or Team Member not found!");
//        }
//    }
public void Assign_Team_to_Project(String project_id,
                                   String member1, String member2, String member3,
                                   String member4, String member5, String member6,
                                   String member7, String member8, String member9,
                                   String member10, String member11, String member12,
                                   String member13, String member14) {

    try (Connection conn = getConnection();
         Statement st = conn.createStatement()) {

        String sql = "UPDATE project_team SET "
                + "member1='" + member1 + "',"
                + "member2='" + member2 + "',"
                + "member3='" + member3 + "',"
                + "member4='" + member4 + "',"
                + "member5='" + member5 + "',"
                + "member6='" + member6 + "',"
                + "member7='" + member7 + "',"
                + "member8='" + member8 + "',"
                + "member9='" + member9 + "',"
                + "member10='" + member10 + "',"
                + "member11='" + member11 + "',"
                + "member12='" + member12 + "',"
                + "member13='" + member13 + "',"
                + "member14='" + member14 + "' "
                + "WHERE project_id='" + project_id + "'";

        st.executeUpdate(sql);

    } catch (SQLException e) {
        System.out.println("Error = " + e.getMessage());
    }
}





    void Display_All_Users() {
        System.out.println("\n=== All Users ===");
        System.out.println("Clients (" + All_Clients.size() + "):");
        for (Client client : All_Clients) {
            client.Display_User_Info();
        }

        System.out.println("\nEmployees (" + All_Employees.size() + "):");
        for (user employee : All_Employees) {
            employee.Display_User_Info();
        }
    }


//        private static final Scanner UserInput = new Scanner(System.in);

        public void Start_Portal_System() {
            DB.Log_System("Project Management Portal System Started");

            while (true) {
                try {
                    System.out.println("\n=== DevSync Project Management Portal ===");
                    System.out.println("1. User Registration");
                    System.out.println("2. User Login");
                    System.out.println("3. Password Reset");
                    System.out.println("4. View All Projects");
                    System.out.println("5. View System Logs");
                    System.out.println("6. System Information");
                    System.out.println("7. Exit System");
                    System.out.print("Please Choose An Option: ");

                    int Main_Choice = User_Input.nextInt();
                    User_Input.nextLine(); // Consume newline

                    switch (Main_Choice) {

                        case 1: // User Registration
                            System.out.print("Enter Username: ");
                            String username = User_Input.nextLine();
                            System.out.print("Enter Email: ");
                            String email = User_Input.nextLine();
                            System.out.print("Enter Password: ");
                            String password = User_Input.nextLine();
                            System.out.print("Enter Role (Client/Admin/QA/DS/UIUX/MLE/ATE): ");
                            String role = User_Input.nextLine();

                            DB.Register_User(username, email, password, role);
                            DB.Log_System("New User Registered: " + username);
                            break;

                        case 2: // User Login
                            System.out.print("Enter Username: ");
                            String loginUser = User_Input.nextLine();
                            System.out.print("Enter Password: ");
                            String loginPass = User_Input.nextLine();

                            if (DB.Validate_User(loginUser, loginPass)) {
                                System.out.println("Login Successful! Welcome, " + loginUser);
                                DB.Log_System("User Logged In: " + loginUser);
                                // TODO: Redirect to user-specific dashboard based on role
                            } else {
                                System.out.println("Invalid Username or Password.");
                            }
                            break;

                        case 3: // Password Reset
                            System.out.print("Enter Username: ");
                            String resetUser = User_Input.nextLine();
                            System.out.print("Enter New Password: ");
                            String newPassword = User_Input.nextLine();

                            DB.Reset_Password(resetUser, newPassword);
                            DB.Log_System("Password Reset for User: " + resetUser);
                            break;

                        case 4: // View All Projects
                            DB.Display_All_Projects();
                            break;

                        case 5: // View System Logs
                            DB.Show_All_Logs();
                            break;

                        case 6: // System Information
                            System.out.println("\n=== System Information ===");
                            System.out.println("Portal Version: 1.0");
                            System.out.println("Database: SQL Server");
                            System.out.println("Active Users: TBD"); // You can query USERS table if needed
                            break;

                        case 7: // Exit
                            DB.Log_System("Project Management Portal System Shutting Down");
                            System.out.println("Thank You For Using DevSync Project Management Portal!");
                            return;

                        default:
                            System.out.println("Invalid Option Selected! Please Try Again.");
                            break;
                    }

                } catch (Exception error) {
                    System.out.println("Input Error: " + error.getMessage());
                    User_Input.nextLine(); // Clear the buffer
                }
            }
        }


    void Display_System_Statistics() {
        System.out.println("\n=== System Statistics ===");
        System.out.println("Total Clients: " + All_Clients.size());
        System.out.println("Total Employees: " + All_Employees.size());
        System.out.println("Total Projects: " + All_Projects.size());

        int totalTasks = 0;
        for (Project project : All_Projects) {
            totalTasks += project.Project_Tasks.size();
        }
        DB.View_clients(); DB.View_All_Employees_Data()
;DB.View_All_Projects_Data();
        System.out.println("Total Tasks: " + totalTasks);
    }

    void Manage_User_Accounts(Admin admin) {
        System.out.println("\n=== Manage User Accounts ===");
        System.out.println("1. View All Users");
        System.out.println("2. Reset User Password");
        System.out.print("Choose option: ");

        int choice = User_Input.nextInt();
        User_Input.nextLine();

        switch (choice) {
            case 1:
                Display_All_Users();
                break;
            case 2:
                System.out.print("Enter Username to reset password: ");
                String username = User_Input.nextLine();
                user user = Find_User_By_Username(username);
                if (user != null) {
                    System.out.print("Enter new password: ");
                    String newPassword = User_Input.nextLine();
                    user.Set_Password(newPassword);
                    System.out.println("Password reset successfully!");
                    System_Logger.Log_Activity("Admin reset password for user: " + username);
                } else {
                    System.out.println("User not found!");
                }
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
    }

    void Send_System_Announcement(Admin admin) {
        System.out.println("\n=== Send System Announcement ===");
        System.out.print("Enter announcement message: ");
        String message = User_Input.nextLine();

        // Send to all users
        for (Client client : All_Clients) {
            System_Communication.Send_Message("System", client.User_Username, "ANNOUNCEMENT: " + message);
        }
        for (user employee : All_Employees) {
            if (!employee.User_Username.equals("admin")) {
                System_Communication.Send_Message("System", employee.User_Username, "ANNOUNCEMENT: " + message);
            }
        }

        System.out.println("Announcement sent to all users!");
        System_Logger.Log_Activity("System announcement sent by admin: " + message);
    }

    void Divide_Project_Into_Tasks(Project_Manager pm) {
        System.out.println("\n=== Divide Project into Tasks ===");
        System.out.print("Enter Project ID: ");
        String projectId = User_Input.nextLine();

        Project project = Find_Project_By_Id(projectId);
        if (project != null) {
            System.out.print("Enter number of tasks to create: ");
            int taskCount = User_Input.nextInt();
            User_Input.nextLine();

            for (int i = 1; i <= taskCount; i++) {
                System.out.println("Creating Task " + i);
                System.out.print("Enter Task ID: ");
                String taskId = User_Input.nextLine();
                System.out.print("Enter Task Title: ");
                String taskTitle = User_Input.nextLine();
                System.out.print("Enter Task Description: ");
                String taskDesc = User_Input.nextLine();
                System.out.print("Enter Required Role: ");
                String requiredRole = User_Input.nextLine();

                Task newTask = new Task(taskId, taskTitle, taskDesc, requiredRole);
                project.Add_Task(newTask);
            }
            System.out.println("Tasks created successfully!");
            System_Logger.Log_Activity("Project Manager " + pm.User_Username + " divided project " + projectId + " into " + taskCount + " tasks");
        } else {
            System.out.println("Project not found!");
        }
    }

    void Assign_Tasks_To_Team() {
        System.out.println("\n=== Assign Tasks to Team ===");
        System.out.print("Enter Project ID: ");
        String projectId = User_Input.nextLine();
        String[] members = new String[14];

        // ask for 14 team members
        for (int i = 0; i < 14; i++) {
            System.out.print("Enter member " + (i + 1) + " (press ENTER for null): ");
            String input = User_Input.nextLine();
            members[i] = input.isEmpty() ? null : input;   // convert empty input to null
        }

        // call your original function
        DB.Assign_Team_to_Project(
                projectId,
                members[0], members[1], members[2], members[3], members[4],
                members[5], members[6], members[7], members[8], members[9],
                members[10], members[11], members[12], members[13]
        );

        System.out.println("\nTeam assigned successfully!");

    }

    void Reassign_Tasks(Project_Manager pm) {
        System.out.println("\n=== Reassign Tasks ===");
        System.out.print("Enter Project ID: ");
        String projectId = User_Input.nextLine();

        Project project = Find_Project_By_Id(projectId);
        if (project != null && !project.Project_Tasks.isEmpty()) {
            for (Task task : project.Project_Tasks) {
                task.Show_Task_Basic();
                System.out.print("Reassign to username (or skip): ");
                String username = User_Input.nextLine();

                if (!username.isEmpty()) {
                    user user = Find_User_By_Username(username);
                    if (user != null) {
                        task.Assign_To(user, System_Logger);
                    } else {
                        System.out.println("User not found!");
                    }
                }
            }
        } else {
            System.out.println("Project not found or no tasks available!");
        }
    }

    void Display_Projects_For_Manager(Project_Manager pm) {
        System.out.println("\n=== My Managed Projects ===");
        DB.View_Project_By_Manager(pm.User_Username); // prints directly
    }

    void View_Project_Progress(Project_Manager pm) {
        System.out.println("\n=== Project Progress ===");
        System.out.print("Enter Project ID: ");
        String projectId = User_Input.nextLine();
        DB.view_project_status(projectId);

    }

    void View_Team_Performance(Project_Manager pm) {
        System.out.println("\n=== Team Performance ===");
        System.out.print("Enter Project ID: ");
        String projectId = User_Input.nextLine();

        Project project = Find_Project_By_Id(projectId);
        if (project != null) {
            project.Show_Team_Performance();
        } else {
            System.out.println("Project not found!");
        }
    }

    void Send_Weekly_Report(Project_Manager pm) {
        System.out.println("\n=== Send Weekly Report ===");
        System.out.print("Enter Project ID: ");
        String projectId = User_Input.nextLine();
        System.out.print("Enter Report Content: ");
        String report = User_Input.nextLine();

        System_Communication.Send_Message(pm.User_Username, "admin", "Weekly Report for " + projectId + ": " + report);
        System.out.println("Weekly report sent to admin!");
        System_Logger.Log_Activity("Weekly report sent for project " + projectId + " by " + pm.User_Username);
    }

    void Communicate_With_Team(Project_Manager pm) {
        System.out.println("\n=== Communicate with Team ===");
        System.out.print("Enter Team Member Username: ");
        String username = User_Input.nextLine();
        System.out.print("Enter Message: ");
        String message = User_Input.nextLine();

        System_Communication.Send_Message(pm.User_Username, username, message);
        System_Logger.Log_Activity("Project Manager " + pm.User_Username + " sent message to " + username);
    }

    void Update_Project_Status(Project_Manager pm) {

        System.out.println("\n=== Update Project Status ===");
        System.out.print("Enter Project ID: ");
        String projectId = User_Input.nextLine();
        System.out.print("Enter Project status: ");
        String projectstatus = User_Input.nextLine();
        DB.Update_Project_Status(projectId,projectstatus);

}

    void Display_Tasks_For_User(user user) {
        System.out.println("\n=== My Tasks ===");
        boolean found = false;
        for (Project project : All_Projects) {
            for (Task task : project.Project_Tasks) {
                if (task.Task_Assigned_To == user) {
                    task.Show_Task_Basic();
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No tasks assigned to you.");
        }
    }

    void Upload_Code_Work(Coder coder) {
        System.out.println("\n=== Upload Code Work ===");
        Display_Tasks_For_User(coder);
        System.out.print("Enter Task ID to upload work: ");
        String taskId = User_Input.nextLine();

        for (Project project : All_Projects) {
            for (Task task : project.Project_Tasks) {
                if (task.Task_Id.equals(taskId) && task.Task_Assigned_To == coder) {
                    System.out.print("Enter upload notes: ");
                    String notes = User_Input.nextLine();
                    task.Upload_Work(notes, Portal.System_Logger);
                    return;
                }
            }
        }
        System.out.println("Task not found or not assigned to you!");
    }

    void Mark_Task_As_Completed(Coder coder) {
        System.out.println("\n=== Mark Task as Completed ===");
        Display_Tasks_For_User(coder);
        System.out.print("Enter Task ID to mark completed: ");
        String taskId = User_Input.nextLine();

        for (Project project : All_Projects) {
            for (Task task : project.Project_Tasks) {
                if (task.Task_Id.equals(taskId) && task.Task_Assigned_To == coder) {
                    task.Mark_Completed(System_Logger);
                    return;
                }
            }
        }
        System.out.println("Task not found or not assigned to you!");
    }

    void View_Assigned_Projects(Coder coder) {
        System.out.println("\n=== My Assigned Projects ===");
        boolean found = false;
        for (Project project : All_Projects) {
            for (Task task : project.Project_Tasks) {
                if (task.Task_Assigned_To == coder) {
                    project.Show_Project_Basic();
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No projects assigned to you.");
        }
    }

    void Update_Task_Progress(Coder coder) {
        System.out.println("\n=== Update Task Progress ===");
        Display_Tasks_For_User(coder);
        System.out.print("Enter Task ID to update progress: ");
        String taskId = User_Input.nextLine();

        for (Project project : All_Projects) {
            for (Task task : project.Project_Tasks) {
                if (task.Task_Id.equals(taskId) && task.Task_Assigned_To == coder) {
                    System.out.print("Enter new progress (0-100): ");
                    int progress = User_Input.nextInt();
                    User_Input.nextLine();
                    task.Update_Progress(progress, System_Logger);
                    return;
                }
            }
        }
        System.out.println("Task not found or not assigned to you!");
    }

    void Execute_Manual_Tests(QA_Engineer qa) {
        System.out.println("\n=== Execute Manual Tests ===");
        Display_Tasks_For_User(qa);
        System.out.print("Enter Task ID for testing: ");
        String taskId = User_Input.nextLine();

        for (Project project : All_Projects) {
            for (Task task : project.Project_Tasks) {
                if (task.Task_Id.equals(taskId) && task.Task_Assigned_To == qa) {
                    System.out.print("Enter test results: ");
                    String results = User_Input.nextLine();
                    System_Logger.Log_Activity(qa.User_Username + " executed manual tests for " + taskId + ": " + results);
                    System.out.println("Manual tests executed!");
                    return;
                }
            }
        }
        System.out.println("Task not found or not assigned to you!");
    }

    void Report_Bugs(QA_Engineer qa) {
        System.out.println("\n=== Report Bugs ===");
        System.out.print("Enter bug description: ");
        String bug = User_Input.nextLine();
        System.out.print("Enter severity (Low/Medium/High/Critical): ");
        String severity = User_Input.nextLine();

        System_Communication.Send_Message(qa.User_Username, "admin", "BUG REPORT [" + severity + "]: " + bug);
        System_Logger.Log_Activity("Bug reported by " + qa.User_Username + ": " + bug);
        System.out.println("Bug reported successfully!");
    }

    void Mark_Task_As_Tested(QA_Engineer qa) {
        System.out.println("\n=== Mark Task as Tested ===");
        Display_Tasks_For_User(qa);
        System.out.print("Enter Task ID to mark as tested: ");
        String taskId = User_Input.nextLine();

        for (Project project : All_Projects) {
            for (Task task : project.Project_Tasks) {
                if (task.Task_Id.equals(taskId) && task.Task_Assigned_To == qa) {
                    task.Set_Status("Tested", 100, System_Logger);
                    System.out.println("Task marked as tested!");
                    return;
                }
            }
        }
        System.out.println("Task not found or not assigned to you!");
    }

    void View_Test_Reports(QA_Engineer qa) {
        System.out.println("\n=== Test Reports ===");
        System_Communication.View_Sent_Messages_For_User(qa.User_Username);
    }

    Project Find_Project_By_Id(String Project_Id) {
        for (Project project : All_Projects) {
            if (project.Project_Id.equals(Project_Id)) {
                return project;
            }
        }
        return null;
    }

    void Display_System_Information() {
        System.out.println("\n=== System Information ===");
        System.out.println("DevSync Project Management Portal");
        System.out.println("Version: 2.0.0");
        System.out.println("Total User Roles: 20");
        System.out.println("Features: Project Management, Task Assignment, Role-Based Dashboards, Communication System, Activity Logging");
        System.out.println("Total Pre-registered Employee IDs: " + Pre_Registered_Ids.size());
    }

//    public void Start_Portal_System() {
//        System_Logger.Log_System("Project Management Portal System Started");
//
//        while (true) {
//            try {
//                System.out.println("\n=== DevSync Project Management Portal ===");
//                System.out.println("1. User Registration");
//                System.out.println("2. User Login");
//                System.out.println("3. Password Reset");
//                System.out.println("4. View All Projects");
//                System.out.println("5. View System Logs");
//                System.out.println("6. System Information");
//                System.out.println("7. Exit System");
//                System.out.print("Please Choose An Option: ");
//
//                int Main_Choice = User_Input.nextInt();
//                User_Input.nextLine();
//
//                switch (Main_Choice) {
//                    case 1:
//                        User_Signup("faizan","faizan@123@gmail.com", Integer.parseInt("12345"));
//                        break;
//                    case 2:
//                        User_Login("faizan","123", Integer.parseInt("client"));
//                        break;
//                    case 3:
//                        User_Password_Reset();
//                        break;
//                    case 4:
//                        Display_All_Projects();
//                        break;
//                    case 5:
//                        System_Logger.Show_All_Logs();
//                        break;
//                    case 6:
//                        Display_System_Information();
//                        break;
//                    case 7:
//                        System_Logger.Log_System("Project Management Portal System Shutting Down");
//                        System.out.println("Thank You For Using DevSync Project Management Portal!");
//                        return;
//                    default:
//                        System.out.println("Invalid Option Selected! Please Try Again.");
//                        break;
//                }
//            } catch (Exception error) {
//                System.out.println("Input Error: " + error.getMessage());
//                User_Input.nextLine();
//            }
        }
