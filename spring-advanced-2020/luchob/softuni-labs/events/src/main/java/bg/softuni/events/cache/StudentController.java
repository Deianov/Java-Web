package bg.softuni.events.cache;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/students-all")
  public ResponseEntity<List<Student>> getAllStudents() {
    return ResponseEntity.ok(studentService.getAllStudents());
  }

  @GetMapping("/students-update")
  public ResponseEntity<List<Student>> updateAllStudents() {
    return ResponseEntity.ok(studentService.updateAllStudents());
  }

  @GetMapping("/students-delete")
  public ResponseEntity<List<Student>> deleteAllStudents() {
    studentService.evictCache();
    return ResponseEntity.ok().build();
  }

}
