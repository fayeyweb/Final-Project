// CLASS: Student – represents a student with login info and grades
public class Student {

    //All of these are marked as private, meaning only the Student class can access them directly. This is called encapsulation, and it keeps data safe.
    // FIELD 1 – student ID
    private String id;

    // FIELD 2 – student surname
    private String surname;

    // EXTRA FIELD
    private String password;

    private double exam;
    private double quiz;
    private double attendance;
    
       // METHOD 1 – Constructor (sets basic information)
       // Ito yung gumagawa ng student object, kapag nag-register ung student, ito ung gumagana.
    public Student(String id, String surname, String password) {
        this.id = id;
        this.surname = surname;
        this.password = password;
    }

    // METHOD 2 – sets the grades of the student
    // Dito ina-assign yung scores ng student.
    public void setGrades(double exam, double quiz, double attendance) {
        this.exam = exam;
        this.quiz = quiz;
        this.attendance = attendance;
    }
    // WEIGHTED FORMULA: 60% exam, 20% quiz, 20% attendance
    public double getOverall() {
        double examPercent = (exam / 60) * 100;
        double quizPercent = (quiz / 20) * 100;
        double attendancePercent = (attendance / 20) * 100;

    // Weighted computation
        return (examPercent * 0.60) + (quizPercent * 0.20) + (attendancePercent * 0.20);
    }
    public double getOverall() { return exam + quiz + attendance; }
    public String getStatus() {
      if (getOverall() >= 75) {
          return "PASSED";
      } else {
          return "FAILED";
    }
}
    
    //Lahat ng ito ay pangbasa lang ng data, hindi sila nagbabago ng data.
    public String getId() { return id; }
    public String getSurname() { return surname; }
    public String getPassword() { return password; }

    public double getExam() { return exam; }
    public double getQuiz() { return quiz; }
    public double getAttendance() { return attendance; }

}

