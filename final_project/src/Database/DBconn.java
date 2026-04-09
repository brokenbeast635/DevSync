package Database;

import core.Portal;
import java.sql.*;
import java.util.ArrayList;

import user.*;

import static core.Portal.User_Input;

public class DBconn {

    public static class DatabaseConnection {

        private static final String URL =
                "jdbc:sqlserver://FAIZAN:1433;databaseName=master;encrypt=true;trustServerCertificate=true;";

        private static final String USER = "sa"; // <-- your SQL Server username
        private static final String PASSWORD = "123"; // <-- your SQL password

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
//
//        private static final String SERVER = "FAIZAN";
//        private static final String PORT = "1433";
//
//        private static final String DATABASE = "master";
//
//        private static final String CONNECTION_URL =
//                "jdbc:sqlserver://" + SERVER + ":" + PORT +
//                        ";databaseName=" + DATABASE +
//                        ";integratedSecurity=true;" +
//                        "encrypt=true;trustServerCertificate=true;";
//        Portal P = new Portal();
//
//        public static Connection getConnection() throws SQLException {
//            System.out.println("\nConnecting to: " + CONNECTION_URL);
//            return DriverManager.getConnection(CONNECTION_URL);
//        }

        public void insertMessage(String sender, String receiver, String message) {
            String sql = "INSERT INTO ChatMessages (Sender, Receiver, Message, SentAt) VALUES (?, ?, ?, SYSUTCDATETIME())";

            try (Connection cnxn = getConnection();
                 PreparedStatement ps = cnxn.prepareStatement(sql)) {

                ps.setString(1, sender);
                ps.setString(2, receiver);
                ps.setString(3, message);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("DB Insert Error: " + e.getMessage());
            }
        }
        public static void main(String[] args) {
            System.out.println("=== SQL Server Connection Test ===");

            try (Connection conn = getConnection()) {

                System.out.println("✓ Connected to Database: ");

                runQueries(conn);

                DatabaseConnection dc = new DatabaseConnection();
                dc.insertMessage("alice", "bob", "Hello Bob from Java!");

            } catch (SQLException e) {
                System.err.println("\n✗ FAILED: " + e.getMessage());
                e.printStackTrace();
            }
        }




        public void System_LOGS(String System_log){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO SYSTEM_LOGS (SYSTEM_LOG) VALUES ('"+System_log+"')";
                st.executeUpdate(sql);
                st.close();

                System.out.println("SYSTEM Log added  successfully!");

            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Error_LOGS(String Error_log){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO ERROR_LOGS (ERROR_LOG) VALUES ('"+Error_log+"')";
                st.executeUpdate(sql);
                st.close();

                System.out.println("ERROR Log added  successfully!");

            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void admin(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO ADMIN (ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }

        public void Assign_Project_Manager(String Project_Id, String project_manager ){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "UPDATE  projects SET project_manager = '"+project_manager+"' WHERE project_id = '"+Project_Id+"'";
                st.executeUpdate(sql);
                st.close();

            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        // In DBconn.DatabaseConnection class
        public void Add_Project(String projectId, String projectName, String projectDescription, String projectClient, int projectBudget, String projectStatus){
            // Ensure column names and order match your 'projects' table schema.
            String sql = "INSERT INTO projects (project_id, project_name, project_description, project_client, project_budget, project_status) VALUES (?, ?, ?, ?, ?, ?)";

            try(Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, projectId);
                ps.setString(2, projectName);
                ps.setString(3, projectDescription); // Description goes here
                ps.setString(4, projectClient);
                ps.setInt(5, projectBudget);
                ps.setString(6, projectStatus);

                ps.executeUpdate();
                System.out.println("Project added successfully!");

            } catch (SQLException e) {
                System.out.println("Error adding project: " + e.getMessage());
            }
        }
        // In DBconn.DatabaseConnection class
        public void ACTIVITY_LOGS(String activityLog){
            // Use PreparedStatement to prevent SQL Injection
            String sql = "INSERT INTO ACTIVITY_LOGS (ACTIVITY_LOG) VALUES (?)";
            try(Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, activityLog);
                ps.executeUpdate();
                System.out.println("Activity Log added successfully!");

            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        // In DBconn.DatabaseConnection class
        public ArrayList<Object[]> getClientProjects(String username) {
            ArrayList<Object[]> projects = new ArrayList<>();
            // Note: It's important to use PreparedStatement even for SELECT if the WHERE clause uses user input!
            String sql = "SELECT project_name, project_status, project_budget, project_description FROM projects WHERE project_client = ?";

            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    // Package the data for the GUI
                    // project_name (String), project_status (String), project_budget (int), project_description (String)
                    projects.add(new Object[] {
                            rs.getString("project_name"),
                            rs.getString("project_status"),
                            rs.getInt("project_budget"),
                            rs.getString("project_description")
                    });
                }
            } catch (SQLException e) {
                System.out.println("Error fetching client projects: " + e.getMessage());
                e.printStackTrace();
            }
            return projects;
        }
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
        public void Update_Project_Status(String projectId, String projectStatus) {
            String sql = "UPDATE PROJECTS SET project_status = ? WHERE project_id = ?";

            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, projectStatus);
                ps.setString(2, projectId);

                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }
        }
        public void view_project_status(String project_id) {

            String sql = "SELECT project_id, project_status FROM projects WHERE project_id = ?";

            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, project_id);
                ResultSet rs = ps.executeQuery();

                System.out.println("============ PROJECT STATUS ============");

                if (rs.next()) {
                    System.out.println("Project ID      : " + rs.getString("project_id"));
                    System.out.println("Project Status  : " + rs.getString("project_status"));
                } else {
                    System.out.println("No project found with ID: " + project_id);
                }

                System.out.println("============ END ============");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public void View_client_Projects_Data1(String username) {
            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM projects WHERE project_client = '" + username + "'";

                ResultSet rs = st.executeQuery(sql);

                System.out.println("============ ALL PROJECTS ============");

                while (rs.next()) {
                    String projectId = rs.getString("project_id");
                    String projectName = rs.getString("project_name");
                    String projectManager = rs.getString("project_manager");
                    String projectClient = rs.getString("project_client");
                    int projectBudget = rs.getInt("project_budget");
                    String projectStatus = rs.getString("project_status");
                    String projectDescription = rs.getString("project_description");

                    System.out.println("Project ID          : " + projectId);
                    System.out.println("Project Name        : " + projectName);
                    System.out.println("Project Manager     : " + projectManager);
                    System.out.println("Project Client      : " + projectClient);
                    System.out.println("Project Budget      : " + projectBudget);
                    System.out.println("Project Status      : " + projectStatus);
                    System.out.println("Project Description : " + projectDescription);
                    System.out.println("---------------------------------------");
                }

                System.out.println("============ END OF LIST ============");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public void reset_client_password(String username, String newPassword) {
            String sql = "UPDATE login SET Password = ? WHERE Username = ?";

            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, newPassword);
                ps.setString(2, username);

                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        public void add_coder_skill(String coder_skill , String coder_id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO coder_skill (USERID, coder_skill) VALUES ('" + coder_id + "', '" + coder_skill + "')";                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void view_coder_skill(String coderId) {
            String sql = "SELECT USERID , coder_skill FROM coder_skill WHERE coder_id = ?";

            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, coderId);
                ResultSet rs = ps.executeQuery();

                System.out.println("============ CODER SKILLS ============");
                System.out.println("Coder ID: " + coderId);

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    int userID = rs.getInt("USERID");
                    String coder_Skill = rs.getString("coder_skill");


                    System.out.println("user ID      : " + userID);
                    System.out.println("Skill Name    : " + coder_Skill);
//
                    System.out.println("------------------------------------");
                }

                if (!found) {
                    System.out.println("No skills found for this coder.");
                }

                System.out.println("============ END OF LIST ============");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public void View_project_by_coder(String coderId) {
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(
                         "SELECT * FROM projects " +
                                 "JOIN project_team ON projects.project_id = project_team.project_id " +
                                 "WHERE member1 = ? OR member2 = ? OR member3 = ? OR member4 = ? OR member5 = ? " +
                                 "OR member6 = ? OR member7 = ? OR member8 = ? OR member9 = ? OR member10 = ? " +
                                 "OR member11 = ? OR member12 = ? OR member13 = ? OR member14 = ?"
                 )) {

                // Set same coderId for all 14 placeholders
                for (int i = 1; i <= 14; i++) {
                    ps.setString(i, coderId);
                }

                ResultSet rs = ps.executeQuery();

                System.out.println("============ PROJECTS FOR CODER: " + coderId + " ============");

                while (rs.next()) {

                    System.out.println("Project ID        : " + rs.getString("project_id"));
                    System.out.println("Project Name      : " + rs.getString("project_name"));
                    System.out.println("Project Manager   : " + rs.getString("project_manager"));
                    System.out.println("Project Client    : " + rs.getString("project_client"));
                    System.out.println("Project Budget    : " + rs.getString("project_budget"));
                    System.out.println("Project Status    : " + rs.getString("project_status"));
                    System.out.println("Description       : " + rs.getString("project_description"));

                    System.out.println("Team Members:");
                    for (int i = 1; i <= 14; i++) {
                        String col = "member" + i;
                        String val = rs.getString(col);
                        if (val != null)
                            System.out.println(" - " + val);
                    }

                    System.out.println("------------------------------------------");
                }

                System.out.println("============ END OF LIST ============");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public void mark_as_completed_by_coder(String project_id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "UPDATE CODER SET TASK_COMPLETED = 1 WHERE PROJECT_ID = '" + project_id + "'";
                st.executeUpdate(sql);
                st.close();


            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void View_My_Testing_Tasks(String qa_engineer_id) {
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement("SELECT * FROM QA_TASKS WHERE QA_ENGINEER_ID = ?")) {

                pst.setString(1, qa_engineer_id);
                ResultSet rs = pst.executeQuery();

                System.out.println("============ MY TESTING TASKS ============");

                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;

                    // Read all columns from the table
                    int taskId = rs.getInt("TASK_ID");
                    String projectId = rs.getString("PROJECT_ID");
                    String qaEngineerId = rs.getString("QA_ENGINEER_ID");
                    String taskDescription = rs.getString("TASK_DESCRIPTION");
                    int bugReported = rs.getInt("BUG_REPORTED");
                    String taskStatus = rs.getString("TASK_STATUS");
                    String testReport = rs.getString("TEST_REPORT");
                    String testingMethodology = rs.getString("TESTING_METHODOLOGY");

                    // Format the output
                    System.out.println("Task ID: " + taskId);
                    System.out.println("Project ID: " + projectId);
                    System.out.println("Task Description: " + taskDescription);
                    System.out.println("Bug Reported: " + (bugReported == 1 ? "Yes" : "No"));
                    System.out.println("Task Status: " + taskStatus);
                    System.out.println("Testing Methodology: " + (testingMethodology != null ? testingMethodology : "Not Specified"));
                    System.out.println("Test Report: " + (testReport != null ? testReport : "No report yet"));
                    System.out.println("------------------------------------");
                }

                if (!hasData) {
                    System.out.println("No testing tasks assigned to you yet.");
                }

                System.out.println("============ END OF LIST ============");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public void Report_Bugs(String qa_engineer_id, String project_id, String bug_report) {

            try (Connection conn = getConnection()) {

                // Update the task with bug report (only if bug not already reported)
                String updateSql = "UPDATE QA_TASKS SET BUG_REPORTED = 1, TEST_REPORT = ? WHERE PROJECT_ID = ? AND QA_ENGINEER_ID = ? AND BUG_REPORTED = 0";
                PreparedStatement updatePst = conn.prepareStatement(updateSql);
                updatePst.setString(1, bug_report);
                updatePst.setString(2, project_id);
                updatePst.setString(3, qa_engineer_id);

                int rowsAffected = updatePst.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("✓ Bug reported successfully for project ID: " + project_id);
                } else {
                    System.out.println("✗ Failed to report bug. Task not found, already reported, or you don't have permission.");
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public void View_Test_Reports(String projectId, String qaEngineerId) {
            System.out.println("5. View Test Reports");

            String sql = "SELECT * FROM QA_TASKS WHERE PROJECT_ID = ? AND QA_ENGINEER_ID = ?";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, projectId);
                pst.setString(2, qaEngineerId);

                try (ResultSet rs = pst.executeQuery()) {
                    System.out.println("============ TEST REPORTS ============");

                    boolean hasData = false;
                    while (rs.next()) {
                        hasData = true;

                        // Read columns
                        int taskId = rs.getInt("TASK_ID");
                        String projId = rs.getString("PROJECT_ID");
                        String qaId = rs.getString("QA_ENGINEER_ID");
                        String taskDescription = rs.getString("TASK_DESCRIPTION");
                        int bugReported = rs.getInt("BUG_REPORTED");
                        String taskStatus = rs.getString("TASK_STATUS");
                        String testReport = rs.getString("TEST_REPORT");
                        String testingMethodology = rs.getString("TESTING_METHODOLOGY");

                        // Print a concise, helpful view focused on the test report
                        System.out.println("Task ID: " + taskId);
                        System.out.println("Project ID: " + projId);
                        System.out.println("QA Engineer: " + qaId);
                        System.out.println("Task Description: " + taskDescription);
                        System.out.println("Task Status: " + (taskStatus != null ? taskStatus : "Unknown"));
                        System.out.println("Testing Methodology: " + (testingMethodology != null ? testingMethodology : "Not Specified"));
                        System.out.println("Bug Reported: " + (bugReported == 1 ? "Yes" : "No"));
                        System.out.println("Test Report: " + (testReport != null ? testReport : "No report available"));
                        System.out.println("------------------------------------");
                    }

                    if (!hasData) {
                        System.out.println("No test reports found for Project ID: " + projectId + " and QA Engineer: " + qaEngineerId);
                    }

                    System.out.println("============ END OF LIST ============");
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public void Load_UIUX_Tasks(String designerId) {

            String sql = "SELECT * FROM UIUX_TASKS WHERE DESIGNER_ID = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, designerId);

                ResultSet rs = pst.executeQuery();

                System.out.println("\n=== Your Tasks ===");
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Title: " + rs.getString("TASK_TITLE"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Status: " + rs.getString("TASK_STATUS"));
                    System.out.println("---------------------------------");
                }

                if (!found)
                    System.out.println("No tasks assigned yet.");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        public void Save_Wireframe(String designerId, String details) {
            String sql = "INSERT INTO UIUX_WIREFRAMES (DESIGNER_ID, DETAILS) VALUES (?,?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, designerId);
                pst.setString(2, details);

                pst.executeUpdate();
                System.out.println("Wireframe saved to database!");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        public void Save_Design_Tool(String designerId, String toolName) {

            String sql = "INSERT INTO UIUX_TOOLS (DESIGNER_ID, TOOL_NAME) VALUES (?,?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, designerId);
                pst.setString(2, toolName);

                pst.executeUpdate();
                System.out.println("Tool added to database!");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        public void Display_Design_Tools_DB(String designerId) {
            String sql = "SELECT * FROM UIUX_TOOLS WHERE DESIGNER_ID = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, designerId);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n=== Your Tools ===");

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Tool: " + rs.getString("TOOL_NAME"));
                }

                if (!found)
                    System.out.println("No tools added yet.");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        public void View_My_Fullstack_Tasks(String fse_id) {
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement("SELECT * FROM FULLSTACK_TASKS WHERE FSE_ID = ?")) {

                pst.setString(1, fse_id);
                ResultSet rs = pst.executeQuery();

                System.out.println("========== MY FULLSTACK TASKS ==========");

                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;

                    // Read columns from the FULLSTACK_TASKS table
                    int taskId = rs.getInt("TASK_ID");
                    String projectId = rs.getString("PROJECT_ID");
                    String fseId = rs.getString("FSE_ID");
                    String taskDescription = rs.getString("TASK_DESCRIPTION");
                    String taskStatus = rs.getString("TASK_STATUS");
                    String technologyUsed = rs.getString("TECHNOLOGY_USED");
                    String skillCategory = rs.getString("SKILL_CATEGORY");

                    // Format and print the output
                    System.out.println("Task ID: " + taskId);
                    System.out.println("Project ID: " + (projectId != null ? projectId : "Not Specified"));
                    System.out.println("FSE ID: " + (fseId != null ? fseId : "Not Specified"));
                    System.out.println("Task Description: " + (taskDescription != null ? taskDescription : "No Description"));
                    System.out.println("Task Status: " + (taskStatus != null ? taskStatus : "Pending"));
                    System.out.println("Technology Used: " + (technologyUsed != null ? technologyUsed : "Not Specified"));
                    System.out.println("Skill Category: " + (skillCategory != null ? skillCategory : "Not Specified"));
                    System.out.println("----------------------------------------");
                }

                if (!hasData) {
                    System.out.println("No fullstack tasks assigned to you yet.");
                }

                System.out.println("=========== END OF LIST ===========");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public static void Load_ATE_Tasks(String ateId) {
            String sql = "SELECT * FROM ATE_TASKS WHERE ATE_ID = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, ateId);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n===== AUTOMATION TASKS =====");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Status: " + rs.getString("TASK_STATUS"));
                    System.out.println("Script Details: " + rs.getString("SCRIPT_DETAILS"));
                    System.out.println("Test Results: " + rs.getString("TEST_RESULTS"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No tasks found.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void Save_Test_Results(String ateId, String results) {
            String sql = "INSERT INTO ATE_TASKS (ATE_ID, TEST_RESULTS, TASK_STATUS) VALUES (?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, ateId);
                pst.setString(2, results);
                pst.setString(3, "Test Run Completed");
                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void Display_Automation_Tools(String ateId) {
            String sql = "SELECT * FROM ATE_TOOLS WHERE ATE_ID = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, ateId);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n===== AUTOMATION TOOLS =====");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Tool ID: " + rs.getInt("TOOL_ID"));
                    System.out.println("Tool Name: " + rs.getString("TOOL_NAME"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No tools found.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void Save_Test_Script(String ateId, String scriptDetails) {
            String sql = "INSERT INTO ATE_TASKS (ATE_ID, SCRIPT_DETAILS, TASK_STATUS) VALUES (?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, ateId);
                pst.setString(2, scriptDetails);
                pst.setString(3, "Script Created"); // default status when script is saved
                pst.executeUpdate();

                System.out.println("Test Script Saved Successfully!");

            } catch (SQLException e) {
                System.out.println("Error saving test script: " + e.getMessage());
                e.printStackTrace();
            }
        }
        public static void Save_Automation_Tool(String ateId, String toolName) {
            String sql = "INSERT INTO ATE_TOOLS (ATE_ID, TOOL_NAME) VALUES (?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, ateId);
                pst.setString(2, toolName);
                pst.executeUpdate();

                System.out.println("Automation Tool saved successfully!");

            } catch (SQLException e) {
                System.out.println("Error saving automation tool: " + e.getMessage());
                e.printStackTrace();
            }
        }
        public void Load_DS_Tasks(String DS_ID) {
            String sql = "SELECT * FROM DATA_SCIENCE_TASKS WHERE DS_ID = ?";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, DS_ID);
                ResultSet rs = pst.executeQuery();

                System.out.println("=== DATA SCIENCE TASKS ===");

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Title: " + rs.getString("TASK_TITLE"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Status: " + rs.getString("TASK_STATUS"));
                    System.out.println("----------------------------");
                }

                if (!found) {
                    System.out.println("No tasks found for Data Scientist: " + DS_ID);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        public void Save_Data_Analysis(String DS_ID, String details) {
            String sql = "INSERT INTO DATA_ANALYSIS_REPORTS (DS_ID, REPORT_TYPE, REPORT_DETAILS) VALUES (?, ?, ?)";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, DS_ID);
                pst.setString(2, "Data Analysis");
                pst.setString(3, details);

                pst.executeUpdate();
                System.out.println("Data Analysis Saved!");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        public void Save_ML_Model(String DS_ID, String details) {
            String sql = "INSERT INTO DATA_ANALYSIS_REPORTS (DS_ID, REPORT_TYPE, REPORT_DETAILS) VALUES (?, ?, ?)";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, DS_ID);
                pst.setString(2, "ML Model");
                pst.setString(3, details);

                pst.executeUpdate();
                System.out.println("ML Model Saved!");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        public void Save_ML_Algorithm(String DS_ID, String algo) {
            String sql = "INSERT INTO ML_ALGORITHMS (DS_ID, ALGORITHM_NAME) VALUES (?, ?)";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, DS_ID);
                pst.setString(2, algo);

                pst.executeUpdate();
                System.out.println("Algorithm Saved!");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        public void View_ML_Algorithms_DB(String DS_ID) {
            String sql = "SELECT * FROM ML_ALGORITHMS WHERE DS_ID = ?";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, DS_ID);
                ResultSet rs = pst.executeQuery();

                System.out.println("=== MY ML ALGORITHMS ===");

                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println("- " + rs.getString("ALGORITHM_NAME"));
                }

                if (!found) {
                    System.out.println("No algorithms found!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }



        public static void View_All_Projects() {
            String sql = "SELECT ProjectId, ProjectName, Status FROM Projects";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                System.out.println("\nProjectID | ProjectName | Status");
                System.out.println("------------------------------------");

                while (rs.next()) {
                    String id = rs.getString("ProjectId");
                    String name = rs.getString("ProjectName");
                    String status = rs.getString("Status");

                    System.out.printf("%-10s | %-20s | %-10s%n", id, name, status);
                }

            } catch (SQLException e) {
                System.err.println("Error fetching projects: " + e.getMessage());
            }
        }
        public void View_Project_By_Manager(String managerId) {
            String sql = "SELECT * FROM projects WHERE project_manager_id = ?";

            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, managerId);
                ResultSet rs = ps.executeQuery();

                System.out.println("====== PROJECTS FOR MANAGER: " + managerId + " ======");

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    String projectId = rs.getString("project_id");
                    String projectName = rs.getString("project_name");
                    String projectManager = rs.getString("project_manager");
                    String projectClient = rs.getString("project_client");
                    int projectBudget = rs.getInt("project_budget");
                    String projectStatus = rs.getString("project_status");
                    String projectDescription = rs.getString("project_description");
                    String pmId = rs.getString("project_manager_id");

                    System.out.println("Project ID          : " + projectId);
                    System.out.println("Project Name        : " + projectName);
                    System.out.println("Project Manager     : " + projectManager);
                    System.out.println("Project Client      : " + projectClient);
                    System.out.println("Project Budget      : " + projectBudget);
                    System.out.println("Project Status      : " + projectStatus);
                    System.out.println("Project Description : " + projectDescription);
                    System.out.println("Manager ID (PM)     : " + pmId);
                    System.out.println("---------------------------------------");
                }

                if (!found) {
                    System.out.println("No projects found for Manager ID: " + managerId);
                }

                System.out.println("============ END OF LIST ============");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }


        public static void Reset_Password(String username, String newPassword) {
            String sql = "UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, newPassword);
                pst.setString(2, username);
                pst.executeUpdate();

                System.out.println("Password Updated!");

            } catch (SQLException e) {
                System.out.println("Password Reset Error: " + e.getMessage());
            }
        }


        public void View_All_Employees_Data() {
            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM LOGIN WHERE UserId NOT LIKE 'CL%' AND UserId NOT LIKE 'admin%'";
                ResultSet rs = st.executeQuery(sql);

                System.out.println("============ ALL CLIENTS ============");

                while (rs.next()) {
                    String userId = rs.getString("UserId");
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String role = rs.getString("User_Role");

                    System.out.println("User ID      : " + userId);
                    System.out.println("Username     : " + username);
                    System.out.println("Password     : " + password);
                    System.out.println("User Role    : " + role);
                    System.out.println("------------------------------------");
                }

                System.out.println("============ END OF LIST ============");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public void View_All_Projects_Data() {
            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM projects";
                ResultSet rs = st.executeQuery(sql);

                System.out.println("============ ALL PROJECTS ============");

                while (rs.next()) {
                    String projectId = rs.getString("project_id");
                    String projectName = rs.getString("project_name");
                    String projectManager = rs.getString("project_manager");
                    String projectClient = rs.getString("project_client");
                    int projectBudget = rs.getInt("project_budget");
                    String projectStatus = rs.getString("project_status");
                    String projectDescription = rs.getString("project_description");

                    System.out.println("Project ID          : " + projectId);
                    System.out.println("Project Name        : " + projectName);
                    System.out.println("Project Manager     : " + projectManager);
                    System.out.println("Project Client      : " + projectClient);
                    System.out.println("Project Budget      : " + projectBudget);
                    System.out.println("Project Status      : " + projectStatus);
                    System.out.println("Project Description : " + projectDescription);
                    System.out.println("---------------------------------------");
                }

                System.out.println("============ END OF LIST ============");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }

        public void View_clients() {
            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM LOGIN WHERE UserId LIKE 'CL%'";
                ResultSet rs = st.executeQuery(sql);

                System.out.println("============ ALL CLIENTS ============");

                while (rs.next()) {
                    String userId = rs.getString("UserId");
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String role = rs.getString("User_Role");

                    System.out.println("User ID      : " + userId);
                    System.out.println("Username     : " + username);
                    System.out.println("Password     : " + password);
                    System.out.println("User Role    : " + role);
                    System.out.println("------------------------------------");
                }

                System.out.println("============ END OF LIST ============");

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace();
            }
        }
        public static void Display_All_Projects() {
            String sql = "SELECT * FROM PROJECTS";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                System.out.println("\n===== PROJECT LIST =====");

                while (rs.next()) {
                    System.out.println("Project ID: " + rs.getInt("PROJECT_ID"));
                    System.out.println("Name: " + rs.getString("PROJECT_NAME"));
                    System.out.println("Description: " + rs.getString("DESCRIPTION"));
                    System.out.println("-------------------------");
                }

            } catch (SQLException e) {
                System.out.println("Project Load Error: " + e.getMessage());
            }
        }
        public static void Log_System(String message) {
            String sql = "INSERT INTO SYSTEM_LOGS (LOG_MESSAGE) VALUES (?)";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, message);
                pst.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Log Error: " + e.getMessage());
            }
        }
        public static void Show_All_Logs() {
            String sql = "SELECT * FROM SYSTEM_LOGS";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                System.out.println("\n===== SYSTEM LOGS =====");

                while (rs.next()) {
                    System.out.println("Log ID: " + rs.getInt("LOG_ID"));
                    System.out.println("Message: " + rs.getString("LOG_MESSAGE"));
                    System.out.println("Time: " + rs.getTimestamp("LOG_TIME"));
                    System.out.println("---------------------------");
                }

            } catch (SQLException e) {
                System.out.println("Log Load Error: " + e.getMessage());
            }
        }
        public static void Display_Project_By_ID(String projectId) {
            String q = "SELECT * FROM PROJECTS WHERE PROJECT_ID = ?";

            try (Connection con = getConnection();
                 PreparedStatement pst = con.prepareStatement(q)) {

                pst.setString(1, projectId);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    System.out.println("\n=== Project Details ===");
                    System.out.println("Project ID: " + rs.getString("PROJECT_ID"));
                    System.out.println("Name: " + rs.getString("PROJECT_NAME"));
                    System.out.println("Description: " + rs.getString("DESCRIPTION"));
                    System.out.println("Status: " + rs.getString("STATUS"));
                    System.out.println("Deadline: " + rs.getString("DEADLINE"));
                } else {
                    System.out.println("Project Not Found!");
                }

            } catch (Exception e) {
                System.out.println("Error Loading Project: " + e.getMessage());
            }
        }
        public static void Send_Message(String sender, String receiver, String content) {
            String q = "INSERT INTO MESSAGES(SENDER, RECEIVER, CONTENT) VALUES(?,?,?)";

            try (Connection con = getConnection();
                 PreparedStatement pst = con.prepareStatement(q)) {

                pst.setString(1, sender);
                pst.setString(2, receiver);
                pst.setString(3, content);

                pst.executeUpdate();
                System.out.println("Message Sent!");

            } catch (Exception e) {
                System.out.println("Error Sending Message: " + e.getMessage());
            }
        }
        public void Create_New_Project_DB() {
            System.out.print("Enter Project ID: ");
            String id = User_Input.nextLine();

            System.out.print("Enter Project Name: ");
            String name = User_Input.nextLine();

            System.out.print("Enter Description: ");
            String desc = User_Input.nextLine();

            String sql = "INSERT INTO PROJECTS (PROJECT_ID, PROJECT_NAME, DESCRIPTION, STATUS) VALUES (?,?,?,?)";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, id);
                pst.setString(2, name);
                pst.setString(3, desc);
                pst.setString(4, "Pending");

                pst.executeUpdate();
                System.out.println("Project Created Successfully!");

            } catch (Exception e) {
                System.out.println("Error Creating Project: " + e.getMessage());
            }
        }

        public void Assign_Project_Manager_DB() {
            System.out.print("Enter Project ID: ");
            String projectId = User_Input.nextLine();

            System.out.print("Enter Manager Username: ");
            String manager = User_Input.nextLine();

            String sql = "UPDATE PROJECTS SET MANAGER = ? WHERE PROJECT_ID = ?";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, manager);
                pst.setString(2, projectId);

                pst.executeUpdate();
                System.out.println("Project Manager Assigned!");

            } catch (Exception e) {
                System.out.println("Error Assigning PM: " + e.getMessage());
            }
        }
        public void Assign_Team_To_Project_DB() {
            System.out.print("Enter Project ID: ");
            String projectId = User_Input.nextLine();

            System.out.print("Enter Team Member Username: ");
            String member = User_Input.nextLine();

            String sql = "INSERT INTO PROJECT_TEAM (PROJECT_ID, USERNAME) VALUES (?,?)";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, projectId);
                pst.setString(2, member);

                pst.executeUpdate();
                System.out.println("Team Member Assigned!");

            } catch (Exception e) {
                System.out.println("Error Assigning Team: " + e.getMessage());
            }
        }
        public static void Display_All_Users() {
            String sql = "SELECT * FROM USERS";

            try (Connection con = getConnection();
                 PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                System.out.println("\n=== All Users ===");

                while (rs.next()) {
                    System.out.println("Username: " + rs.getString("USERNAME"));
                    System.out.println("Email: " + rs.getString("EMAIL"));
                    System.out.println("Role: " + rs.getString("ROLE"));
                    System.out.println("---------------------------------");
                }

            } catch (Exception e) {
                System.out.println("Error Loading Users: " + e.getMessage());
            }
        }
        public static void Display_System_Statistics() {
            try (Connection con = getConnection()) {

                System.out.println("\n=== System Stats ===");

                // Total Users
                ResultSet u = con.prepareStatement("SELECT COUNT(*) FROM USERS").executeQuery();
                if (u.next()) System.out.println("Total Users: " + u.getInt(1));

                // Total Projects
                ResultSet p = con.prepareStatement("SELECT COUNT(*) FROM PROJECTS").executeQuery();
                if (p.next()) System.out.println("Total Projects: " + p.getInt(1));

                // Total Messages
                ResultSet m = con.prepareStatement("SELECT COUNT(*) FROM MESSAGES").executeQuery();
                if (m.next()) System.out.println("Total Messages Sent: " + m.getInt(1));

            } catch (Exception e) {
                System.out.println("Statistics Error: " + e.getMessage());
            }
        }
        public void Send_System_Announcement_DB(String sender) {
            System.out.print("Enter Announcement Message: ");
            String content = User_Input.nextLine();

            String q = "INSERT INTO ANNOUNCEMENTS (SENDER, MESSAGE) VALUES (?,?)";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(q)) {

                pst.setString(1, sender);
                pst.setString(2, content);
                pst.executeUpdate();

                System.out.println("Announcement Sent!");

            } catch (Exception e) {
                System.out.println("Error Sending Announcement: " + e.getMessage());
            }
        }
        public void Create_Task_DB(String manager) {
            System.out.print("Enter Project ID: ");
            String pid = User_Input.nextLine();

            System.out.print("Enter Task Title: ");
            String title = User_Input.nextLine();

            System.out.print("Enter Task Description: ");
            String desc = User_Input.nextLine();

            String sql = "INSERT INTO TASKS (PROJECT_ID, TITLE, DESCRIPTION, ASSIGNED_TO, STATUS) VALUES (?, ?, ?, NULL, 'Pending')";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, pid);
                pst.setString(2, title);
                pst.setString(3, desc);

                pst.executeUpdate();
                System.out.println("Task Created Successfully!");

            } catch (Exception e) {
                System.out.println("Error Creating Task: " + e.getMessage());
            }
        }
        public void Assign_Task_DB(String manager) {
            System.out.print("Enter Task ID: ");
            int taskId = User_Input.nextInt();
            User_Input.nextLine();

            System.out.print("Enter Team Member Username: ");
            String member = User_Input.nextLine();

            String sql = "UPDATE TASKS SET ASSIGNED_TO = ?, STATUS = 'Assigned' WHERE TASK_ID = ?";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, member);
                pst.setInt(2, taskId);

                pst.executeUpdate();
                System.out.println("Task Assigned Successfully!");

            } catch (Exception e) {
                System.out.println("Error Assigning Task: " + e.getMessage());
            }
        }
        public void Reassign_Task_DB(String manager) {
            System.out.print("Enter Task ID: ");
            int taskId = User_Input.nextInt();
            User_Input.nextLine();

            System.out.print("Enter New Team Member Username: ");
            String member = User_Input.nextLine();

            String sql = "UPDATE TASKS SET ASSIGNED_TO = ? WHERE TASK_ID = ?";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, member);
                pst.setInt(2, taskId);
                pst.executeUpdate();

                System.out.println("Task Reassigned!");

            } catch (Exception e) {
                System.out.println("Error Reassigning Task: " + e.getMessage());
            }
        }
        public void Display_Projects_For_Manager_DB(String manager) {
            String sql = "SELECT * FROM PROJECTS WHERE MANAGER = ?";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, manager);

                ResultSet rs = pst.executeQuery();

                System.out.println("\n=== Your Projects ===");

                while (rs.next()) {
                    System.out.println("ID: " + rs.getString("PROJECT_ID"));
                    System.out.println("Name: " + rs.getString("PROJECT_NAME"));
                    System.out.println("Status: " + rs.getString("STATUS"));
                    System.out.println("---------------------------------");
                }

            } catch (Exception e) {
                System.out.println("Error Loading Projects: " + e.getMessage());
            }
        }
        public void View_Project_Progress_DB(String manager) {
            System.out.print("Enter Project ID: ");
            String pid = User_Input.nextLine();

            String sql = "SELECT STATUS, COUNT(*) AS total FROM TASKS WHERE PROJECT_ID = ? GROUP BY STATUS";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, pid);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n=== Project Progress ===");

                while (rs.next()) {
                    System.out.println(rs.getString("STATUS") + ": " + rs.getInt("total"));
                }

            } catch (Exception e) {
                System.out.println("Error Displaying Progress: " + e.getMessage());
            }
        }
        public void View_Team_Performance_DB(String manager) {

            String sql =
                    "SELECT ASSIGNED_TO, COUNT(*) AS completed " +
                            "FROM TASKS WHERE STATUS='Completed' GROUP BY ASSIGNED_TO";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                System.out.println("\n=== Team Performance ===");

                while (rs.next()) {
                    System.out.println("User: " + rs.getString("ASSIGNED_TO"));
                    System.out.println("Completed Tasks: " + rs.getInt("completed"));
                    System.out.println("-----------------------------");
                }

            } catch (Exception e) {
                System.out.println("Error Loading Performance: " + e.getMessage());
            }
        }
        public void Send_Weekly_Report_DB(String manager) {
            System.out.print("Enter Project ID: ");
            String pid = User_Input.nextLine();

            System.out.print("Enter Report Message: ");
            String msg = User_Input.nextLine();

            String sql = "INSERT INTO REPORTS (PROJECT_ID, SENDER, MESSAGE) VALUES (?,?,?)";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, pid);
                pst.setString(2, manager);
                pst.setString(3, msg);

                pst.executeUpdate();
                System.out.println("Weekly Report Sent!");

            } catch (Exception e) {
                System.out.println("Error Sending Report: " + e.getMessage());
            }
        }
        public void Communicate_With_Team_DB(String manager) {
            System.out.print("Enter Team Member Username: ");
            String receiver = User_Input.nextLine();

            System.out.print("Enter Message: ");
            String msg = User_Input.nextLine();

            String sql = "INSERT INTO MESSAGES (SENDER, RECEIVER, MESSAGE) VALUES (?,?,?)";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, manager);
                pst.setString(2, receiver);
                pst.setString(3, msg);

                pst.executeUpdate();
                System.out.println("Message Sent!");

            } catch (Exception e) {
                System.out.println("Error Sending Message: " + e.getMessage());
            }
        }
        public void Update_Project_Status_DB(String manager) {
            System.out.print("Enter Project ID: ");
            String pid = User_Input.nextLine();

            System.out.print("Enter New Status (Pending/Ongoing/Completed): ");
            String status = User_Input.nextLine();

            String sql = "UPDATE PROJECTS SET STATUS = ? WHERE PROJECT_ID = ?";

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, status);
                pst.setString(2, pid);

                pst.executeUpdate();
                System.out.println("Project Status Updated!");

            } catch (Exception e) {
                System.out.println("Error Updating Status: " + e.getMessage());
            }
        }
        // Save Specialization
        public static void Save_Specialization(String username, String specialization) {
            String sql = "INSERT INTO SOFTWARE_ARCHITECT_SPECIALIZATIONS (USERNAME, SPECIALIZATION) VALUES (?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, specialization);
                pst.executeUpdate();
                System.out.println("Specialization saved!");
            } catch (SQLException e) {
                System.out.println("Error saving specialization: " + e.getMessage());
            }
        }

        // View Specializations
        public static void View_Specializations(String username) {
            String sql = "SELECT * FROM SOFTWARE_ARCHITECT_SPECIALIZATIONS WHERE USERNAME = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Specializations ===");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("SA_ID") + ", Specialization: " + rs.getString("SPECIALIZATION"));
                }
            } catch (SQLException e) {
                System.out.println("Error fetching specializations: " + e.getMessage());
            }
        }

        // Save Architecture Task
        public static void Save_Architecture_Task(String username, String description) {
            String sql = "INSERT INTO ARCHITECTURE_TASKS (USERNAME, TASK_DESCRIPTION) VALUES (?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, description);
                pst.executeUpdate();
                System.out.println("Task saved!");
            } catch (SQLException e) {
                System.out.println("Error saving task: " + e.getMessage());
            }
        }

        // View Architecture Tasks
        public static void View_Architecture_Tasks(String username) {
            String sql = "SELECT * FROM ARCHITECTURE_TASKS WHERE USERNAME = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Architecture Tasks ===");
                while (rs.next()) {
                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Status: " + rs.getString("TASK_STATUS"));
                    System.out.println("Notes: " + rs.getString("NOTES"));
                    System.out.println("---------------------------");
                }
            } catch (SQLException e) {
                System.out.println("Error fetching tasks: " + e.getMessage());
            }
        }
        // Save a backend task
        public static void Save_Backend_Task(String username, String taskDesc, String taskType) {
            String sql = "INSERT INTO BACKEND_TASKS (BE_USERNAME, TASK_DESCRIPTION, TASK_TYPE) VALUES (?, ?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, taskDesc);
                pst.setString(3, taskType);
                pst.executeUpdate();
                System.out.println("Task saved successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving backend task: " + e.getMessage());
            }
        }

        // View all backend tasks for a user
        public static void View_Backend_Tasks(String username) {
            String sql = "SELECT TASK_ID, TASK_DESCRIPTION, TASK_TYPE, CREATED_AT FROM BACKEND_TASKS WHERE BE_USERNAME = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Backend Tasks ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Type: " + rs.getString("TASK_TYPE"));
                    System.out.println("Created At: " + rs.getTimestamp("CREATED_AT"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No backend tasks found.");
            } catch (SQLException e) {
                System.out.println("Error fetching backend tasks: " + e.getMessage());
            }
        }

        // Save a backend technology / skill
        public static void Save_Backend_Technology(String username, String techName) {
            String sql = "INSERT INTO BACKEND_TECHNOLOGIES (BE_USERNAME, TECHNOLOGY_NAME) VALUES (?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, techName);
                pst.executeUpdate();
                System.out.println("Technology added successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving technology: " + e.getMessage());
            }
        }

        // View all backend technologies for a user
        public static void View_Backend_Technologies(String username) {
            String sql = "SELECT TECH_ID, TECHNOLOGY_NAME, ADDED_AT FROM BACKEND_TECHNOLOGIES WHERE BE_USERNAME = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Backend Technologies ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Tech ID: " + rs.getInt("TECH_ID"));
                    System.out.println("Technology: " + rs.getString("TECHNOLOGY_NAME"));
                    System.out.println("Added At: " + rs.getTimestamp("ADDED_AT"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No technologies found.");
            } catch (SQLException e) {
                System.out.println("Error fetching technologies: " + e.getMessage());
            }
        }
        // Save a frontend task
        public static void Save_Frontend_Task(String username, String taskDesc, String taskType) {
            String sql = "INSERT INTO FRONTEND_TASKS (FE_USERNAME, TASK_DESCRIPTION, TASK_TYPE) VALUES (?, ?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, taskDesc);
                pst.setString(3, taskType);
                pst.executeUpdate();
                System.out.println("Frontend task saved successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving frontend task: " + e.getMessage());
            }
        }

        // View all frontend tasks for a user
        public static void View_Frontend_Tasks(String username) {
            String sql = "SELECT TASK_ID, TASK_DESCRIPTION, TASK_TYPE, CREATED_AT FROM FRONTEND_TASKS WHERE FE_USERNAME = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Frontend Tasks ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Type: " + rs.getString("TASK_TYPE"));
                    System.out.println("Created At: " + rs.getTimestamp("CREATED_AT"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No frontend tasks found.");
            } catch (SQLException e) {
                System.out.println("Error fetching frontend tasks: " + e.getMessage());
            }
        }

        // Save a frontend framework / skill
        public static void Save_Frontend_Framework(String username, String frameworkName) {
            String sql = "INSERT INTO FRONTEND_FRAMEWORKS (FE_USERNAME, FRAMEWORK_NAME) VALUES (?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, frameworkName);
                pst.executeUpdate();
                System.out.println("Frontend framework added successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving frontend framework: " + e.getMessage());
            }
        }

        // View all frontend frameworks for a user
        public static void View_Frontend_Frameworks(String username) {
            String sql = "SELECT FRAMEWORK_ID, FRAMEWORK_NAME, ADDED_AT FROM FRONTEND_FRAMEWORKS WHERE FE_USERNAME = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Frontend Frameworks ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Framework ID: " + rs.getInt("FRAMEWORK_ID"));
                    System.out.println("Framework: " + rs.getString("FRAMEWORK_NAME"));
                    System.out.println("Added At: " + rs.getTimestamp("ADDED_AT"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No frontend frameworks found.");
            } catch (SQLException e) {
                System.out.println("Error fetching frontend frameworks: " + e.getMessage());
            }
        }
        // Save a Fullstack task
        public static void Save_Fullstack_Task(String username, String taskDesc, String taskType) {
            String sql = "INSERT INTO FULLSTACK_TASKS (FSE_USERNAME, TASK_DESCRIPTION, TASK_TYPE) VALUES (?, ?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, taskDesc);
                pst.setString(3, taskType);
                pst.executeUpdate();
                System.out.println("Fullstack task saved successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving Fullstack task: " + e.getMessage());
            }
        }

        // View all Fullstack tasks for a user
        public static void View_Fullstack_Tasks(String username) {
            String sql = "SELECT TASK_ID, TASK_DESCRIPTION, TASK_TYPE, CREATED_AT FROM FULLSTACK_TASKS WHERE FSE_USERNAME = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Fullstack Tasks ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Type: " + rs.getString("TASK_TYPE"));
                    System.out.println("Created At: " + rs.getTimestamp("CREATED_AT"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No Fullstack tasks found.");
            } catch (SQLException e) {
                System.out.println("Error fetching Fullstack tasks: " + e.getMessage());
            }
        }

        // Save a Fullstack technology / skill
        public static void Save_Fullstack_Technology(String username, String techName) {
            String sql = "INSERT INTO FULLSTACK_TECHNOLOGIES (FSE_USERNAME, TECH_NAME) VALUES (?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, techName);
                pst.executeUpdate();
                System.out.println("Fullstack technology added successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving Fullstack technology: " + e.getMessage());
            }
        }

        // View all Fullstack technologies for a user
        public static void View_Fullstack_Technologies(String username) {
            String sql = "SELECT TECH_ID, TECH_NAME, ADDED_AT FROM FULLSTACK_TECHNOLOGIES WHERE FSE_USERNAME = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Fullstack Technologies ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Tech ID: " + rs.getInt("TECH_ID"));
                    System.out.println("Technology: " + rs.getString("TECH_NAME"));
                    System.out.println("Added At: " + rs.getTimestamp("ADDED_AT"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No Fullstack technologies found.");
            } catch (SQLException e) {
                System.out.println("Error fetching Fullstack technologies: " + e.getMessage());
            }
        }
        // Add a new task
        public static void Save_Mobile_Task(String username, String projectId, String description) {
            String sql = "INSERT INTO MOBILE_TASKS (DEVELOPER_USERNAME, PROJECT_ID, TASK_DESCRIPTION) VALUES (?, ?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, projectId);
                pst.setString(3, description);
                pst.executeUpdate();
                System.out.println("Task saved successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving task: " + e.getMessage());
            }
        }

        // View tasks for a developer
        public static void View_Mobile_Tasks(String username) {
            String sql = "SELECT TASK_ID, TASK_DESCRIPTION, TASK_STATUS, CREATED_AT FROM MOBILE_TASKS WHERE DEVELOPER_USERNAME = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Mobile Tasks ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Status: " + rs.getString("TASK_STATUS"));
                    System.out.println("Created At: " + rs.getTimestamp("CREATED_AT"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No tasks found.");
            } catch (SQLException e) {
                System.out.println("Error fetching tasks: " + e.getMessage());
            }
        }

        // Save a mobile feature
        public static void Save_Mobile_Feature(String username, String projectId, String featureName, String details) {
            String sql = "INSERT INTO MOBILE_FEATURES (DEVELOPER_USERNAME, PROJECT_ID, FEATURE_NAME, FEATURE_DETAILS) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, projectId);
                pst.setString(3, featureName);
                pst.setString(4, details);
                pst.executeUpdate();
                System.out.println("Mobile feature saved successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving mobile feature: " + e.getMessage());
            }
        }

        // Add a mobile platform
        public static void Save_Mobile_Platform(String username, String platformName) {
            String sql = "INSERT INTO MOBILE_PLATFORMS (DEVELOPER_USERNAME, PLATFORM_NAME) VALUES (?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, platformName);
                pst.executeUpdate();
                System.out.println("Mobile platform added successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving platform: " + e.getMessage());
            }
        }

        // View mobile platforms
        public static void View_Mobile_Platforms(String username) {
            String sql = "SELECT PLATFORM_ID, PLATFORM_NAME, CREATED_AT FROM MOBILE_PLATFORMS WHERE DEVELOPER_USERNAME = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                System.out.println("\n=== Mobile Platforms ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Platform ID: " + rs.getInt("PLATFORM_ID"));
                    System.out.println("Platform Name: " + rs.getString("PLATFORM_NAME"));
                    System.out.println("Added At: " + rs.getTimestamp("CREATED_AT"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No platforms found.");
            } catch (SQLException e) {
                System.out.println("Error fetching platforms: " + e.getMessage());
            }
        }
        // Display all SRE tools for a specific engineer
        public static void View_SRE_Tools(String engineerUsername) {
            String sql = "SELECT TOOL_ID, TOOL_NAME, CREATED_AT FROM SRE_TOOLS WHERE ENGINEER_USERNAME = ?";

            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, engineerUsername);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n=== My SRE Tools ===");
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println("Tool ID: " + rs.getInt("TOOL_ID"));
                    System.out.println("Tool Name: " + rs.getString("TOOL_NAME"));
                    System.out.println("Added At: " + rs.getTimestamp("CREATED_AT"));
                    System.out.println("---------------------------------");
                }

                if (!found) {
                    System.out.println("No SRE tools found for user: " + engineerUsername);
                }

            } catch (SQLException e) {
                System.out.println("Error fetching SRE tools: " + e.getMessage());
                e.printStackTrace();
            }
        }
        public static void Load_ATE_Tasks(int engineerId) {
            String sql = "SELECT * FROM ATE_TASKS WHERE ENGINEER_ID = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, engineerId);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n=== My Automation Tasks ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Status: " + rs.getString("TASK_STATUS"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No tasks found.");
            } catch (SQLException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
        public static void Save_Test_Script(int engineerId, String scriptDetails) {
            String sql = "INSERT INTO TEST_SCRIPTS (ENGINEER_ID, SCRIPT_DETAILS) VALUES (?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, engineerId);
                pst.setString(2, scriptDetails);
                pst.executeUpdate();
                System.out.println("Test script saved successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving test script: " + e.getMessage());
            }
        }

        public static void Save_Test_Results(int engineerId, String resultDetails) {
            String sql = "INSERT INTO TEST_RESULTS (ENGINEER_ID, RESULT_DETAILS) VALUES (?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, engineerId);
                pst.setString(2, resultDetails);
                pst.executeUpdate();
                System.out.println("Test results saved successfully!");
            } catch (SQLException e) {
                System.out.println("Error saving test results: " + e.getMessage());
            }
        }

        public static void Save_Automation_Tool(int engineerId, String toolName) {
            String sql = "INSERT INTO AUTOMATION_TOOLS (ENGINEER_ID, TOOL_NAME) VALUES (?, ?)";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, engineerId);
                pst.setString(2, toolName);
                pst.executeUpdate();
                System.out.println("Automation tool added successfully!");
            } catch (SQLException e) {
                System.out.println("Error adding tool: " + e.getMessage());
            }
        }

        public static void Display_Automation_Tools(int engineerId) {
            String sql = "SELECT TOOL_ID, TOOL_NAME, CREATED_AT FROM AUTOMATION_TOOLS WHERE ENGINEER_ID = ?";
            try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, engineerId);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n=== My Automation Tools ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Tool ID: " + rs.getInt("TOOL_ID"));
                    System.out.println("Tool Name: " + rs.getString("TOOL_NAME"));
                    System.out.println("Added At: " + rs.getTimestamp("CREATED_AT"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No tools found.");
            } catch (SQLException e) {
                System.out.println("Error loading tools: " + e.getMessage());
            }
        }

        public void loadTasks(int userId) {
            String query = "SELECT TaskDescription, CreatedAt FROM UIUX_Tasks WHERE UserID=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                System.out.println("Your Tasks:");
                while (rs.next()) {
                    System.out.println("- " + rs.getString("TaskDescription") +
                            " (Created: " + rs.getTimestamp("CreatedAt") + ")");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void saveWireframe(int userId, String details) {
            String query = "INSERT INTO UIUX_Wireframes(UserID, Details, CreatedAt) VALUES(?, ?, GETDATE())";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, userId);
                ps.setString(2, details);
                ps.executeUpdate();
                System.out.println("Saved Successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Save a design tool
        public void saveDesignTool(int userId, String tool) {
            String query = "INSERT INTO Design_Tools(UserID, ToolName) VALUES(?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, userId);
                ps.setString(2, tool);
                ps.executeUpdate();
                System.out.println("Tool added!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void displayDesignTools(int userId) {
            String query = "SELECT ToolName FROM Design_Tools WHERE UserID=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                System.out.println("Your Design Tools:");
                while (rs.next()) {
                    System.out.println("- " + rs.getString("ToolName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // --------------------------------------------
// Display all ML Algorithms for a Data Scientist
// --------------------------------------------
        public static void Display_ML_Algorithms(String dsId) {
            String sql = "SELECT * FROM ML_ALGORITHMS WHERE DS_ID = ?";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, dsId);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n===== ML ALGORITHMS =====");
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println("Algo ID: " + rs.getInt("ALGO_ID"));
                    System.out.println("Algorithm: " + rs.getString("ALGORITHM_NAME"));
                    System.out.println("---------------------------------");
                }

                if (!found) {
                    System.out.println("No ML Algorithm Saved.");
                }

            } catch (SQLException e) {
                System.out.println("Error Loading Algorithms: " + e.getMessage());
                e.printStackTrace();
            }
        }
        public static void Load_MLE_Tasks(String mleId) {
            String sql = "SELECT * FROM MLE_TASKS WHERE MLE_ID = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, mleId);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n===== ML TASKS =====");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Task ID: " + rs.getInt("TASK_ID"));
                    System.out.println("Description: " + rs.getString("TASK_DESCRIPTION"));
                    System.out.println("Status: " + rs.getString("TASK_STATUS"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No tasks found.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void Save_ML_Implementation(String mleId, String implementation) {
            String sql = "INSERT INTO MLE_TASKS (MLE_ID, TASK_DESCRIPTION, TASK_STATUS) VALUES (?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, mleId);
                pst.setString(2, implementation);
                pst.setString(3, "Implemented");
                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void Save_Model_Optimization(String mleId, String optimization) {
            String sql = "INSERT INTO MLE_TASKS (MLE_ID, TASK_DESCRIPTION, TASK_STATUS) VALUES (?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, mleId);
                pst.setString(2, "Optimization: " + optimization);
                pst.setString(3, "Optimized");
                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void Display_ML_Frameworks(String mleId) {
            String sql = "SELECT * FROM MLE_FRAMEWORKS WHERE MLE_ID = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, mleId);
                ResultSet rs = pst.executeQuery();

                System.out.println("\n===== ML FRAMEWORKS =====");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Framework ID: " + rs.getInt("FRAMEWORK_ID"));
                    System.out.println("Framework Name: " + rs.getString("FRAMEWORK_NAME"));
                    System.out.println("---------------------------------");
                }
                if (!found) System.out.println("No frameworks found.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void Save_ML_Framework(String mleId, String framework) {
            String sql = "INSERT INTO MLE_FRAMEWORKS (MLE_ID, FRAMEWORK_NAME) VALUES (?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, mleId);
                pst.setString(2, framework);
                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void View_All_Users() {
            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM projects";
                ResultSet rs = st.executeQuery(sql);

                System.out.println("============ ALL PROJECTS ============");

                while (rs.next()) {
                    // Read all columns from the table
                    String projectId = rs.getString("project_id");
                    String projectName = rs.getString("project_name");
                    String manager = rs.getString("project_manager");
                    String client = rs.getString("project_client");
                    int budget = rs.getInt("project_budget");
                    String status = rs.getString("project_status");
                    String description = rs.getString("project_description");

                    // Format the output
                    System.out.println("Project ID: " + projectId);
                    System.out.println("Project Name: " + projectName);
                    System.out.println("Project Manager: " + manager);
                    System.out.println("Project Client: " + client);
                    System.out.println("Project Budget: $" + budget);
                    System.out.println("Project Status: " + status);
                    System.out.println("Project Description: " + description);
                    System.out.println("------------------------------------");
                }

                System.out.println("============ END OF LIST ============");

                // Note: No need to manually close rs and st - try-with-resources does it automatically

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
                e.printStackTrace(); // Better for debugging
            }
        }

        public void Site_Reliability_Engineer(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Site_Reliability_Engineer(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void SRE_Tool(String SRE_Tool,int id ){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO SRE_Tool (SRE_TOOL, USERID) VALUES ('"+SRE_Tool+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> SRE_Tool() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM SRE_Tool";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }

        public void Backend_Engineer(String User_Id, String User_Username, String User_Password, String User_Email,String User_Role){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Backend_Engineer(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Backend_Technology(String Backend_Technology, int id ) {
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Backend_Technology (Backend_Technology , USERID ) VALUES ('"+Backend_Technology+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Backend_Skills() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Backend_Technology";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }

        public void FullStack_Engineer(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Fullstack_Engineer(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Fullstack_Technology(String Fullstack_Technology, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Fullstack_Technology(Fullstack_Engineer_Technology , USERID) VALUES ('"+Fullstack_Technology+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Fullstack_Engineer_Skills(){
            ArrayList<String> skills = new ArrayList<>();
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "SELECT * FROM Fullstack_Technology";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
            return  skills;
        }
        public void Mobile_Developer(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Mobile_Developer(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Mobile_Developer_Technology(String Mobile_Developer_Technology, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Mobile_Developer_Technology(Mobile_Developer_Technology , USERID) VALUES ('"+Mobile_Developer_Technology+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Mobile_Developer_Skills() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Mobile_Developer_Technology";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }

        public void Data_Scientist(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Data_Scientist(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Data_Scientist_Technology(String Mobile_Developer_Technology, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Data_Scientist_Technology(Data_Scientist_Technology , USERID) VALUES ('"+Mobile_Developer_Technology+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Data_Scientist_Skills() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Data_Scientist_Technology";
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    String skill = rs.getString("skill_name");
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }

        public void QA_Engineer(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO QA_Engineer(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void QA_Engineer_Methodology(String QA_Engineer_Methodology, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO QA_Engineer_Methodology(QA_Engineer_Methodology , USERID) VALUES ('"+QA_Engineer_Methodology+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> QA_Engineer_Methodology() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM QA_Engineer_Methodology";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }
        public void Frontend_Engineer(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Frontend_Engineer(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Frontend_Skills(String Frontend_Skills, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Frontend_Skills(Frontend_Skills , USERID) VALUES ('"+Frontend_Skills+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Frontend_Skills() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Frontend_Skills";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }
        public static void Register_User(String username, String email, String password, String role) {
            String sql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, ROLE) VALUES (?, ?, ?, ?)";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, username);
                pst.setString(2, email);
                pst.setString(3, password);
                pst.setString(4, role);
                pst.executeUpdate();

                System.out.println("User Registered Successfully!");

            } catch (SQLException e) {
                System.out.println("Registration Error: " + e.getMessage());
            }
        }
        public static boolean Validate_User(String username, String password) {
            String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";

            try (Connection conn = getConnection();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                return rs.next();

            } catch (SQLException e) {
                System.out.println("Login Error: " + e.getMessage());
                return false;
            }
        }



        public void UIUX_Designer(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO UIUX_Designer(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void UIUX_Designer_Technology(String UIUX_Designer_Technology, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO UIUX_Designer_Technology(UIUX_Designer_Technology , USERID) VALUES ('"+UIUX_Designer_Technology+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> UIUX_Designer_Skills() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM UIUX_Designer_Technology";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }

        public void Project_Manager(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Project_Manager(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Project_Manager_Projects(String Project_Manager_Projects, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Project_Manager_Projects(Software_Architect_Specialization , USERID) VALUES ('"+Project_Manager_Projects+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Project_Manager_Projects() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Project_Manager_Projects";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }
        public void Software_Architect(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Software_Architect(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Software_Architect_Specialization(String Software_Architect_Specialization, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Software_Architect_Specialization(Software_Architect_Specialization , USERID) VALUES ('"+Software_Architect_Specialization+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Software_Architect_Specialization() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Software_Architect_Specialization";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }



        public void Client(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Client(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Client_Projects(String Client_projects, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Client_projects(Client_projects , USERID) VALUES ('"+Client_projects+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Client_projects() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Client_projects";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }

        public void Coder(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Coder(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Coder_Skill(String Coder_Skill, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Coder_Skill(Coder_Skill , USERID) VALUES ('"+Coder_Skill+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Coder_Skill() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Coder_Skill";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }

        public void Machine_Learning_Engineer(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Machine_Learning_Engineer(ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Machine_Learning_Engineer_Technology(String Machine_Learning_Engineer_Technology, int id){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Machine_Learning_Engineer_Technology(Machine_Learning_Engineer_Technology , USERID) VALUES ('"+Machine_Learning_Engineer_Technology+"','"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Machine_Learning_Engineer_Technology() {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Machine_Learning_Engineer_Technology";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }

        public void automation_testengineer(String User_Id, String User_Username, String User_Password, String User_Email){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO AUTOMATION_TESTENGINEER (ID , USERNAME , PASSWORD , EMAIL) VALUES ('"+User_Id+"','"+User_Username+"','"+User_Password+"','"+User_Email+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public void Automation_Tool(String Automation_Tool , int id  ){
            try(Connection conn = getConnection();
                Statement st = conn.createStatement();){
                String sql = "INSERT INTO Automation_Tool (AUTOMATION_TOOL,USERID) VALUES ('"+Automation_Tool+"'+'"+id+"')";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e) {
                System.out.println(" Error = " + e.getMessage());
            }
        }
        public ArrayList<String> Automation_Tool(String Username) {
            ArrayList<String> skills = new ArrayList<>();

            try (Connection conn = getConnection();
                 Statement st = conn.createStatement()) {

                String sql = "SELECT * FROM Automation_Tool WHERE USERNAME = '" + Username + "'";
                ResultSet rs = st.executeQuery(sql);   // Correct method for SELECT

                while (rs.next()) {
                    String skill = rs.getString("skill_name");  // change the column name to your table column
                    skills.add(skill);
                }

            } catch (SQLException e) {
                System.out.println("Error = " + e.getMessage());
            }

            return skills;
        }

        public static void runQueries(Connection conn) throws SQLException {
            Statement stmt = conn.createStatement();

            System.out.println("\n--- INSERT Example ---");
            // ensure Students table exists before running this demo
            stmt.executeUpdate("INSERT INTO Students (name, age) VALUES ('John Doe', 22)");
            System.out.println("Inserted 1 row.");

            System.out.println("\n--- SELECT Example ---");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Name: " + rs.getString("name") +
                                ", Age: " + rs.getInt("age")
                );
            }

            System.out.println("\n--- UPDATE Example ---");
            stmt.executeUpdate("UPDATE Students SET age = 23 WHERE name = 'John Doe'");
            System.out.println("Updated 1 row.");

            System.out.println("\n--- DELETE Example ---");
            stmt.executeUpdate("DELETE FROM Students WHERE name = 'John Doe'");
            System.out.println("Deleted 1 row.");
        }
    }


        // ─── Helper methods added for GUI dashboards ──────────────────────────────

        /** Returns list of [projectId, projectName, status] for a given manager username */
        public java.util.List<String[]> getProjectsForManager(String managerUsername) {
            java.util.List<String[]> list = new java.util.ArrayList<>();
            String sql = "SELECT PROJECT_ID, PROJECT_NAME, STATUS FROM PROJECTS WHERE MANAGER = ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, managerUsername);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                    list.add(new String[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            } catch (SQLException e) {
                System.err.println("getProjectsForManager error: " + e.getMessage());
            }
            return list;
        }

        /** Returns list of [username, role] for all users */
        public java.util.List<String[]> getAllUsersForAdmin() {
            java.util.List<String[]> list = new java.util.ArrayList<>();
            String sql = "SELECT Username, User_Role FROM LOGIN ORDER BY Username";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    list.add(new String[]{rs.getString(1), String.valueOf(rs.getInt(2))});
            } catch (SQLException e) {
                System.err.println("getAllUsersForAdmin error: " + e.getMessage());
            }
            return list;
        }

        /** Returns current status string of a project */
        public String getProjectStatus(String projectId) {
            String sql = "SELECT STATUS FROM PROJECTS WHERE PROJECT_ID = ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, projectId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) return rs.getString(1);
            } catch (SQLException e) {
                System.err.println("getProjectStatus error: " + e.getMessage());
            }
            return null;
        }

        /** Creates a task and assigns it to a user */
        public void Create_Task_For_Manager(String projectId, String title, String desc,
                                             String assignTo, String createdBy) {
            String sql = "INSERT INTO TASKS (PROJECT_ID, TASK_NAME, TASK_DESCRIPTION, ASSIGNED_TO, STATUS) VALUES (?,?,?,?,'Pending')";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, projectId);
                ps.setString(2, title);
                ps.setString(3, desc);
                ps.setString(4, assignTo);
                ps.executeUpdate();
                Log_Activity("Task created by " + createdBy + " for project " + projectId);
            } catch (SQLException e) {
                System.err.println("Create_Task_For_Manager error: " + e.getMessage());
            }
        }

        /** Change password for the currently logged in user */
        public boolean changePassword(String username, String oldPassword, String newPassword) {
            String checkSql = "SELECT Password FROM LOGIN WHERE Username = ?";
            String updateSql = "UPDATE LOGIN SET Password = ? WHERE Username = ?";
            try (Connection conn = getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                    ps.setString(1, username);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) return false;
                    if (!rs.getString(1).equals(oldPassword)) return false;
                }
                try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                    ps.setString(1, newPassword);
                    ps.setString(2, username);
                    ps.executeUpdate();
                    return true;
                }
            } catch (SQLException e) {
                System.err.println("changePassword error: " + e.getMessage());
                return false;
            }
        }

        /** Returns tasks assigned to a specific user */
        public java.util.List<String[]> getTasksForUser(String username) {
            java.util.List<String[]> list = new java.util.ArrayList<>();
            String sql = "SELECT TASK_ID, TASK_NAME, STATUS, PROJECT_ID FROM TASKS WHERE ASSIGNED_TO = ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                    list.add(new String[]{
                        String.valueOf(rs.getInt(1)), rs.getString(2),
                        rs.getString(3), rs.getString(4)});
            } catch (SQLException e) {
                System.err.println("getTasksForUser error: " + e.getMessage());
            }
            return list;
        }

        /** Update task status */
        public void updateTaskStatus(int taskId, String newStatus) {
            String sql = "UPDATE TASKS SET STATUS = ? WHERE TASK_ID = ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newStatus);
                ps.setInt(2, taskId);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("updateTaskStatus error: " + e.getMessage());
            }
        }

        /** Returns all projects */
        public java.util.List<String[]> getAllProjects() {
            java.util.List<String[]> list = new java.util.ArrayList<>();
            String sql = "SELECT PROJECT_ID, PROJECT_NAME, STATUS, MANAGER FROM PROJECTS";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    list.add(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
            } catch (SQLException e) {
                System.err.println("getAllProjects error: " + e.getMessage());
            }
            return list;
        }

        /** Returns messages received by a user */
        public java.util.List<String[]> getMessagesForUser(String username) {
            java.util.List<String[]> list = new java.util.ArrayList<>();
            String sql = "SELECT SENDER, CONTENT, SENT_AT FROM MESSAGES WHERE RECEIVER = ? ORDER BY SENT_AT DESC";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                    list.add(new String[]{rs.getString(1), rs.getString(2),
                        rs.getTimestamp(3) != null ? rs.getTimestamp(3).toString() : ""});
            } catch (SQLException e) {
                System.err.println("getMessagesForUser error: " + e.getMessage());
            }
            return list;
        }


        public void Save_SRE_Tool(String username, String toolName) {
            String sql = "INSERT INTO SRE_TOOLS (ENGINEER_USERNAME, TOOL_NAME, CREATED_AT) VALUES (?,?,GETDATE())";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username); ps.setString(2, toolName); ps.executeUpdate();
                System.out.println("SRE tool saved: " + toolName);
            } catch (SQLException e) { System.err.println("Save_SRE_Tool error: " + e.getMessage()); }
        }

        public void Save_Architecture_Task(String username, String details, String type) {
            String sql = "INSERT INTO ARCHITECTURE_TASKS (USERNAME, TASK_DESCRIPTION, TASK_STATUS) VALUES (?,?,'Pending')";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username); ps.setString(2, "["+type+"] "+details); ps.executeUpdate();
                System.out.println("Architecture task saved.");
            } catch (SQLException e) { System.err.println("Save_Architecture_Task error: " + e.getMessage()); }
        }

        public void Save_Specialization(String username, String spec) {
            String sql = "INSERT INTO SOFTWARE_ARCHITECT_SPECIALIZATIONS (USERNAME, SPECIALIZATION) VALUES (?,?)";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username); ps.setString(2, spec); ps.executeUpdate();
                System.out.println("Specialization saved: " + spec);
            } catch (SQLException e) { System.err.println("Save_Specialization error: " + e.getMessage()); }
        }

        public void Save_Mobile_Platform(String username, String platform) {
            String sql = "INSERT INTO MOBILE_PLATFORMS (DEVELOPER_USERNAME, PLATFORM_NAME, CREATED_AT) VALUES (?,?,GETDATE())";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username); ps.setString(2, platform); ps.executeUpdate();
                System.out.println("Mobile platform saved: " + platform);
            } catch (SQLException e) { System.err.println("Save_Mobile_Platform error: " + e.getMessage()); }
        }

        public void Save_ML_Framework(String username, String framework) {
            String sql = "INSERT INTO MLE_FRAMEWORKS (MLE_ID, FRAMEWORK_NAME) VALUES (?,?)";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username); ps.setString(2, framework); ps.executeUpdate();
                System.out.println("ML framework saved: " + framework);
            } catch (SQLException e) { System.err.println("Save_ML_Framework error: " + e.getMessage()); }
        }

        public void Save_Data_Analysis(String username, String details, String type) {
            String sql = "INSERT INTO DATA_ANALYSIS_REPORTS (DS_ID, REPORT_TYPE, REPORT_DETAILS) VALUES (?,?,?)";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username); ps.setString(2, type); ps.setString(3, details); ps.executeUpdate();
                System.out.println("Analysis saved.");
            } catch (SQLException e) { System.err.println("Save_Data_Analysis error: " + e.getMessage()); }
        }

        public void Save_Test_Script(String username, String script) {
            String sql = "INSERT INTO ATE_TASKS (ATE_ID, SCRIPT_DETAILS, TASK_STATUS) VALUES (?,?,'Pending')";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username); ps.setString(2, script); ps.executeUpdate();
                System.out.println("Test script saved.");
            } catch (SQLException e) { System.err.println("Save_Test_Script error: " + e.getMessage()); }
        }

        public void Save_Automation_Tool(String username, String tool) {
            String sql = "INSERT INTO AUTOMATION_TOOLS (ENGINEER_ID, TOOL_NAME, CREATED_AT) VALUES (?,?,GETDATE())";
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username); ps.setString(2, tool); ps.executeUpdate();
                System.out.println("Automation tool saved: " + tool);
            } catch (SQLException e) { System.err.println("Save_Automation_Tool error: " + e.getMessage()); }
        }


    public static void main(String[] args) {
        DatabaseConnection.main(args);
    }
}

