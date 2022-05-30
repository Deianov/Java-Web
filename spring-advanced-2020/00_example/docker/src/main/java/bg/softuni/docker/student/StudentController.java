package bg.softuni.docker.student;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private final StudentRepository studentRepository;

  public StudentController(
      StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @GetMapping("/students")
  public ResponseEntity<List<Student>> getAllStudents() {
    return ResponseEntity.ok(studentRepository.findAll());
  }

}
