package bg.softuni.events.cache;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/students")
  public ResponseEntity<List<Student>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).
        body(studentService.getAllStudents());
  }

  @GetMapping("/students-renew")
  public void renew() {
      studentService.updateStudents();
  }

  @GetMapping("/students-purge")
  public void purge() {
    studentService.evictCache();
  }

}
