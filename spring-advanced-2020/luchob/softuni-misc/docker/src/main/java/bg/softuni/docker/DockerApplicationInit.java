package bg.softuni.docker;

import bg.softuni.docker.students.Student;
import bg.softuni.docker.students.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DockerApplicationInit implements CommandLineRunner {

  private final StudentRepository studentRepository;

  public DockerApplicationInit(
      StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Student student = new Student();
    student.setFirstName("Viki");
    student.setLastName("Baleva");
    studentRepository.save(student);
  }
}
