/**
 * GradeSystemMain.java
 * Main program for the Grade Management System.
 *
 * How to run:
 *   javac GradeSystemMain.java Teacher.java Student.java
 *   java GradeSystemMain
 *
 * Notes:
 * - Uses JOptionPane for input and menus.
 * - Simple and beginner-friendly.
 */

import javax.swing.*;
import java.util.ArrayList;

public class GradeSystemMain {
    // Gumagamit tayo ng ArrayList para i-store ang mga registered teachers at students
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Teacher> teachers = new ArrayList<>();

    public static void main(String[] args) {
        // Default teacher account (admin)
        teachers.add(new Teacher("admin", "1234"));
        showWelcome(); // Simula ng program
    }

    // ==================== FLOW START ====================
    static void showWelcome() {
        // Welcome message
        JOptionPane.showMessageDialog(null, "Welcome to the Grade Management System!");

        while (true) {
            // Main menu options
            String[] mainMenu = {"Register", "Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Choose an option:",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    mainMenu,
                    mainMenu[0]
            );

            if (choice == 0) register();   // Register user
            else if (choice == 1) login(); // Login user
            else break;                    // Exit
        }
    }

    // ==================== REGISTER PART ====================
    static void register() {
        String[] type = {"Teacher", "Student", "Cancel"};
        int choice = JOptionPane.showOptionDialog(null, "Register as:", "Register",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, type, type[0]);

        // Teacher registration
        if (choice == 0) {
            String user = JOptionPane.showInputDialog("Enter username:");
            if (user == null) return; // Kung nag-cancel
            String pass = JOptionPane.showInputDialog("Enter password:");
            if (pass == null) return;
            teachers.add(new Teacher(user, pass));
            JOptionPane.showMessageDialog(null, "Teacher registered!");
        }

        // Student registration
        else if (choice == 1) {
            String id = JOptionPane.showInputDialog("Enter Student ID:");
            if (id == null) return;
            String name = JOptionPane.showInputDialog("Enter surname:");
            if (name == null) return;
            String pass = JOptionPane.showInputDialog("Enter password:");
            if (pass == null) return;
            students.add(new Student(id, name, pass));
            JOptionPane.showMessageDialog(null, "Student registered!");
        }
    }

    // ==================== LOGIN PART ====================
    static void login() {
        String[] type = {"Teacher", "Student", "Cancel"};
        int choice = JOptionPane.showOptionDialog(null, "Login as:", "Login",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, type, type[0]);

        if (choice == 0) teacherLogin();
        else if (choice == 1) studentLogin();
    }

    // ---------- TEACHER LOGIN ----------
    static void teacherLogin() {
        String user = JOptionPane.showInputDialog("Enter username:");
        if (user == null) return;
        String pass = JOptionPane.showInputDialog("Enter password:");
        if (pass == null) return;

        // Hahanapin sa list kung may match na teacher
        for (Teacher t : teachers) {
            if (t.getUsername().equals(user) && t.getPassword().equals(pass)) {
                teacherMenu(t);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid login!");
    }

    // ========== TEACHER MENU ==========
    static void teacherMenu(Teacher t) {
        while (true) {
            String[] menu = {"Add/Update Grades", "View All Students", "Logout"};
            int ch = JOptionPane.showOptionDialog(null, "Teacher Menu", "Welcome " + t.getUsername(),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menu, menu[0]);

            if (ch == 0) addGrades();       // Add or update student grades
            else if (ch == 1) viewAllStudents(); // View all students’ grades
            else break;                     // Logout
        }
    }

    // ========== TEACHER ADD GRADES ==========
    static void addGrades() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        if (id == null) return;

        Student s = null;
        // Hanapin yung student base sa ID
        for (Student st : students) {
            if (st.getId().equals(id)) s = st;
        }

        if (s == null) {
            JOptionPane.showMessageDialog(null, "Student not found!");
            return;
        }

        try {
            // Input ng tatlong scores
            double exam = Double.parseDouble(JOptionPane.showInputDialog("Exam score (0-60):"));
            double quiz = Double.parseDouble(JOptionPane.showInputDialog("Quiz score (0-20):"));
            double attendance = Double.parseDouble(JOptionPane.showInputDialog("Attendance (0-20):"));

            // Tawagin yung setter method sa Student class
            s.setGrades(exam, quiz, attendance);
            JOptionPane.showMessageDialog(null, "Grades updated successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input!");
        }
    }

    // ========== TEACHER VIEW STUDENTS ==========
    static void viewAllStudents() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students registered yet.");
            return;
        }

        // Gamit StringBuilder para maipon lahat ng info
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append("ID: ").append(s.getId()).append("\n");
            sb.append("Name: ").append(s.getSurname()).append("\n");
            sb.append("Overall: ").append(String.format("%.2f", s.getOverall())).append("\n");
            sb.append("Status: ").append(s.getStatus()).append("\n\n");
        }

        // Para ma-scroll pag mahaba
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, scroll, "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    // ========== STUDENT LOGIN ==========
    static void studentLogin() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        if (id == null) return;
        String name = JOptionPane.showInputDialog("Enter Surname:");
        if (name == null) return;
        String pass = JOptionPane.showInputDialog("Enter Password:");
        if (pass == null) return;

        // Hanapin kung match ang student info
        for (Student s : students) {
            if (s.getId().equals(id) && s.getSurname().equalsIgnoreCase(name) && s.getPassword().equals(pass)) {
                studentMenu(s);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid login!");
    }

    // ========== STUDENT MENU ==========
    static void studentMenu(Student s) {
        while (true) {
            String[] menu = {"View My Grades", "Logout"};
            int ch = JOptionPane.showOptionDialog(null, "Student Menu", "Welcome " + s.getSurname(),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menu, menu[0]);

            // Student can only view own grade
            if (ch == 0) {
                JOptionPane.showMessageDialog(null,
                        "ID: " + s.getId() +
                        "\nName: " + s.getSurname() +
                        "\nExam: " + s.getExam() +
                        "\nQuiz: " + s.getQuiz() +
                        "\nAttendance: " + s.getAttendance() +
                        "\nOverall: " + String.format("%.2f", s.getOverall()) +
                        "\nStatus: " + s.getStatus());
            } else break; // logout
        }
    }
}
