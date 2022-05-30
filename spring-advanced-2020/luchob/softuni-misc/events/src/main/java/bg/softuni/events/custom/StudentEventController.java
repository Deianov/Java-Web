package bg.softuni.events.custom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentEventController {

  private final StudentEventPublisher eventPublisher;

  StudentEventController(StudentEventPublisher eventPublisher) {

    this.eventPublisher = eventPublisher;
  }

  @GetMapping("/student-event")
  public ResponseEntity<String> studentRequest(
      @RequestParam String studentName,
      @RequestParam Integer studentGrade
  ) {
    System.out.println("Publishing event in thread " + Thread.currentThread().getId());
    eventPublisher.publishEvent(studentName, studentGrade);
    return ResponseEntity.ok("OK");
  }

}
