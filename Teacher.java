/**
 * Teacher.java
 * Represents a Teacher user.
 */

public class Teacher {
    private String username;
    private String password;

    // Constructor
    public Teacher(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters para sa username at password
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // Setters kung gusto mo i-update (optional)
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}
