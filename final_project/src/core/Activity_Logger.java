package core;

import java.util.ArrayList;

public class Activity_Logger {
    public ArrayList<String> Activity_Logs = new ArrayList<>();
    public ArrayList<String> Error_Logs = new ArrayList<>();
    public ArrayList<String> System_Logs = new ArrayList<>();
    public void Log_Activity(String Log_Entry) {
        Activity_Logs.add(Log_Entry);
        System.out.println("[ACTIVITY_LOG] " + Log_Entry);
    }
    void Log_Error(String Error_Entry) {
        Error_Logs.add(Error_Entry);
        System.out.println("[ERROR_LOG] " + Error_Entry);
    }
    void Log_System(String System_Entry) {
        System_Logs.add(System_Entry);
        System.out.println("[SYSTEM_LOG] " + System_Entry);
    }
    void Show_activity_logs() {
        System.out.println("\n--- Activity Logs ---");
        for (String log : Activity_Logs) {
            System.out.println(log);
        }
    }
    void Show_error_logs() {
        System.out.println("\n--- Error Logs ---");
        for (String error : Error_Logs) {
            System.out.println(error);
        }
    }

    void Show_system_logs() {
        System.out.println("\n--- System Logs ---");
        for (String system_log : System_Logs) {
            System.out.println(system_log);
        }
    }

    void Show_All_Logs() {
        Show_activity_logs();
        Show_error_logs();
        Show_system_logs();
    }

    public void Clear_activity_logs() {
        Activity_Logs.clear();
        System.out.println("Activity logs cleared.");
    }

    public void Clear_error_Logs() {
        Error_Logs.clear();
        System.out.println("Error logs cleared.");
    }

    public void Clear_system_logs() {
        System_Logs.clear();
        System.out.println("System logs cleared.");
    }
    public void Clear_All_Logs() {
        Clear_activity_logs();
        Clear_error_Logs();
        Clear_system_logs();
        System.out.println("All logs cleared.");
    }
}

