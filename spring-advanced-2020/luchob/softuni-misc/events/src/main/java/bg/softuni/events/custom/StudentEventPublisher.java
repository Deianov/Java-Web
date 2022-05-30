package bg.softuni.events.custom;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StudentEventPublisher {

  private final ApplicationEventPublisher eventPublisher;

  public StudentEventPublisher(ApplicationEventPublisher eventPublisher) {

    this.eventPublisher = eventPublisher;
  }

  public void publishEvent(String studentName, int grade) {
    System.out.println("Publishing student event for student and grade " +
        studentName + " " +
        grade);
    eventPublisher.publishEvent(new StudentEvent(studentName, grade));
  }

}
