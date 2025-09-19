import java.util.ArrayList;

public class User {
    private String userId;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String userType; // "admin" or "student"

    private ArrayList<CourseModel> courses = new ArrayList<>();

    public User() {}

    public User(String userId, String username, String email, String phone, String password, String userType) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userType = userType;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    public ArrayList<CourseModel> getCourses() { return courses; }
    public void setCourses(ArrayList<CourseModel> courses) { this.courses = courses; }

    @Override
    public String toString() {
        return username + " (" + userType + ", " + email + ")";
    }
}
