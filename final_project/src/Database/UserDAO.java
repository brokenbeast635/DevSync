//package Database;
//
//import Database.DBconn;
//import user.user;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDAO {
//
//    // Insert a user into the table
//    public boolean insertUser(user u) {
//        String query = "INSERT INTO Users(User_Id, User_Username, User_Password, User_Email) VALUES (?, ?, ?, ?)";
//        try (Connection con = DBconn.getConnection();
//             PreparedStatement ps = con.prepareStatement(query)) {
//
//            ps.setString(1, u.User_Id);
//            ps.setString(2, u.User_Username);
//            ps.setString(3, u.User_Password);
//            ps.setString(4, u.User_Email);
//
//            ps.executeUpdate();
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // Retrieve all users from table
//    public List<user> getAllUsers() {
//        List<user> users = new ArrayList<>();
//        String query = "SELECT * FROM Users";
//
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(query);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                user u = new user(
//                        rs.getString("User_Id"),
//                        rs.getString("User_Username"),
//                        rs.getString("User_Password"),
//                        rs.getString("User_Email")
//                );
//                users.add(u);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return users;
//    }
//
//    // Check login (example)
//    public boolean checkLogin(String username, String password) {
//        String query = "SELECT * FROM Users WHERE User_Username=? AND User_Password=?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(query)) {
//
//            ps.setString(1, username);
//            ps.setString(2, password);
//
//            ResultSet rs = ps.executeQuery();
//            return rs.next(); // true if found
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
