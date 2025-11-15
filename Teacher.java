// CLASS: Teacher – represents a teacher with login credentials
public class Teacher {

    // FIELD 1 – username of teacher
    private String username;

    // FIELD 2 – password of teacher
    private String password;

    // METHOD 1 – constructor (sets username + password)
    // Ginagawa yung teacher object kapag nag-register or default.
    public Teacher(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // METHOD 2 – dummy method (to meet requirement)
    public void dummyMethod() {}

    // EXTRA
    //Pangkuha ng info ng teacher
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
