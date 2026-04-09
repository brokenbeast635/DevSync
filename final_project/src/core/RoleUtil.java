package core;


public class RoleUtil {
    public static int roleNameToId(String role) {
        return switch(role) {
            case "Client" -> 1;
            case "Project Manager" -> 2;
            case "Coder" -> 3;
            case "Software Architect" -> 4;
            case "Backend Engineer" -> 5;
            case "Frontend Engineer" -> 6;
            case "Fullstack Engineer" -> 7;
            case "Mobile Developer" -> 8;
            case "Site Reliability Engineer" -> 9;
            case "QA Engineer" -> 10;
            case "Automation Test Engineer" -> 11;
            case "Product Manager" -> 12;
            case "Business Analyst" -> 13;
            case "Scrum Master" -> 14;
            case "UI/UX Designer" -> 15;
            case "Product Designer" -> 16;
            case "Data Engineer" -> 17;
            case "Data Scientist" -> 18;
            case "Machine Learning Engineer" -> 19;
            default -> -1;
        };
    }
}

