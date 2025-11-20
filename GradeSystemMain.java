/* import javax.swing.*;

→ Para magamit ang JOptionPane (pop-up inputs).

import java.util.ArrayList;

→ Para makagamit ng ArrayList (list ng objects).

*/

import javax.swing.*;
import java.util.ArrayList;

public class GradeSystemMain {

    // FIELD 1 – list of students
    static ArrayList<Student> students = new ArrayList<>();

    // FIELD 2 – list of teachers
    static ArrayList<Teacher> teachers = new ArrayList<>();

    // METHOD 1 – main method (starting point)
    public static void main(String[] args) {

        // default teacher
        teachers.add(new Teacher("admin", "1234"));

        // WELCOME MESSAGE
        JOptionPane.showMessageDialog(null,
                "WELCOME TO THE GRADE SYSTEM!");

        showMenu();   // continue to main menu, tatawagin ang showMenu().
    }

    // METHOD 2 – show main menu
    static void showMenu() {
        while (true) {   //babalik sa menu after mag register or what

            String[] options = {"Register", "Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                    null, "Choose an option:", "Main Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) register();  //Itatawag yung register() method, mapupunta user sa registration screen
            else if (choice == 1) login(); //Itatawag yung login() method, mapupunta user sa login screen

            else break; // Lalabas sa while loop
        }
    }

    // REGISTER USER
    static void register() {
        String[] type = {"Teacher", "Student", "Cancel"};

        int c = JOptionPane.showOptionDialog(null, "Register As:", "Register",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, type, type[0]);

        if (c == 0) {   //→ Teacher pinili
            String user = JOptionPane.showInputDialog("Username:");
            String pass = JOptionPane.showInputDialog("Password:");
            teachers.add(new Teacher(user, pass));
            JOptionPane.showMessageDialog(null, "Teacher Registered!");
        }
        else if (c == 1) {  //→ Student pinili
            String id = JOptionPane.showInputDialog("Student ID:");
            String name = JOptionPane.showInputDialog("Surname:");
            String pass = JOptionPane.showInputDialog("Password:");
            students.add(new Student(id, name, pass));
            JOptionPane.showMessageDialog(null, "Student Registered!");
        }
    }

    // LOGIN
    static void login() {
        String[] type = {"Teacher", "Student", "Cancel"};

        int c = JOptionPane.showOptionDialog(null, "Login As:", "Login",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, type, type[0]);

        if (c == 0) teacherLogin();
        else if (c == 1) studentLogin();
    }

    // TEACHER LOGIN
    static void teacherLogin() {
        String user = JOptionPane.showInputDialog("Username:");
        String pass = JOptionPane.showInputDialog("Password:");

        for (Teacher t : teachers) {
            if (t.getUsername().equals(user) &&
                t.getPassword().equals(pass)) {
                teacherMenu(t);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid login!");
    }

    // STUDENT LOGIN
    static void studentLogin() {
        String id = JOptionPane.showInputDialog("ID:");
        String name = JOptionPane.showInputDialog("Surname:");
        String pass = JOptionPane.showInputDialog("Password:");

        for (Student s : students) {
            if (s.getId().equals(id) &&
                s.getSurname().equalsIgnoreCase(name) &&
                s.getPassword().equals(pass)) {
                studentMenu(s);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid login!");
    }

    // TEACHER MENU
    static void teacherMenu(Teacher t) {
        while (true) {
            String[] menu = {"Add/Update Grades", "View Students", "Logout"};
            int c = JOptionPane.showOptionDialog(null, "Teacher Menu",
                    "Welcome " + t.getUsername(),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, menu, menu[0]);

            if (c == 0) addGrades();
            else if (c == 1) viewStudents();
            else break;
        }
    }

    // ADD GRADES
    static void addGrades() {
        String id = JOptionPane.showInputDialog("Student ID:");

        Student s = null;
        for (Student st : students)
            if (st.getId().equals(id)) s = st;

        if (s == null) {
            JOptionPane.showMessageDialog(null, "Student Not Found!");
            return;
        }

        double exam = Double.parseDouble(JOptionPane.showInputDialog("Exam (0-60):"));
        double quiz = Double.parseDouble(JOptionPane.showInputDialog("Quiz (0-20):"));
        double att  = Double.parseDouble(JOptionPane.showInputDialog("Attendance (0-20):"));

        s.setGrades(exam, quiz, att);

        JOptionPane.showMessageDialog(null, "Grades Updated!");
    }

    // VIEW ALL STUDENTS
    static void viewStudents() {
        StringBuilder sb = new StringBuilder();

        for (Student s : students) {
            sb.append("ID: ").append(s.getId()).append("\n");
            sb.append("Name: ").append(s.getSurname()).append("\n");
            sb.append("Overall: ").append(s.getOverall()).append("\n");
            sb.append("Status: ").append(s.getStatus()).append("\n\n");
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);

        JOptionPane.showMessageDialog(null,
                new JScrollPane(area), "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    // STUDENT MENU
    static void studentMenu(Student s) {
        while (true) {
            String[] menu = {"View My Grades", "Logout"};
            int c = JOptionPane.showOptionDialog(null, "Student Menu",
                    "Welcome " + s.getSurname(),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, menu, menu[0]);

            if (c == 0) {
                JOptionPane.showMessageDialog(null,
                        "ID: " + s.getId() +
                        "\nName: " + s.getSurname() +
                        "\nExam: " + s.getExam() +
                        "\nQuiz: " + s.getQuiz() +
                        "\nAttendance: " + s.getAttendance() +
                        "\nOverall: " + s.getOverall() +
                        "\nStatus: " + s.getStatus());
            } else break;
        }
    }
}
