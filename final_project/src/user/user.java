package user;

public class user {
    // BUG FIX: Was 'public static String User_Id' — static means ALL user objects shared
    // the same ID. Every new login would overwrite every other user's ID. Removed static.
    public String User_Id;
    public String User_Username;
    public String User_Password;
    public String User_Email;
    public int User_Role;

    user() {}

    public user(String User_Id, String User_Username, String User_Password, String User_Email) {
        this.User_Id       = User_Id;
        this.User_Username = User_Username;
        this.User_Password = User_Password;
        this.User_Email    = User_Email;
    }

    public user(String User_Username, int User_Role) {
        this.User_Username = User_Username;
        // BUG FIX: User_Role was never assigned — every dashboard showed role 0
        this.User_Role = User_Role;
    }

    public boolean Check_Password(String Input_Password) {
        if (this.User_Password == null) return false;
        return this.User_Password.equals(Input_Password);
    }

    public void Set_Password(String New_Password) {
        this.User_Password = New_Password;
    }

    public void Display_User_Info() {
        System.out.println("ID: " + User_Id + " | Username: " + User_Username + " | Email: " + User_Email);
    }
}
