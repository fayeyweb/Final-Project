/**
 * Student.java
 * Represents a Student with grades.
 */

public class Student {
    private String id;
    private String surname;
    private String password;

    // Tatlong fields para sa grades
    private double exam;
    private double quiz;
    private double attendance;

    // Constructor
    public Student(String id, String surname, String password) {
        this.id = id;
        this.surname = surname;
        this.password = password;
    }

    // Method para mag-set ng grades (input from teacher)
    public void setGrades(double exam, double quiz, double attendance) {
        this.exam = exam;
        this.quiz = quiz;
        this.attendance = attendance;
    }

    // Kinukuha total or overall score
    public double getOverall() {
        return exam + quiz + attendance;
    }

    // Check kung pasado o bagsak
    public String getStatus() {
        return (getOverall() >= 75) ? "PASSED" : "FAILED";
    }

    // Getters (para makuha values)
    public String getId() { return id; }
    public String getSurname() { return surname; }
    public String getPassword() { return password; }
    public double getExam() { return exam; }
    public double getQuiz() { return quiz; }
    public double getAttendance() { return attendance; }
}
