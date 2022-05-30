package bg.softuni.events.custom;

import org.springframework.context.ApplicationEvent;

public class StudentEvent extends ApplicationEvent {

  private final String studentName;
  private final Integer grade;

  public StudentEvent(String studentName, Integer grade) {
    super("Student: " + studentName);
    this.studentName = studentName;
    this.grade = grade;
  }

  public String getStudentName() {
    return studentName;
  }

  public Integer getGrade() {
    return grade;
  }
}
