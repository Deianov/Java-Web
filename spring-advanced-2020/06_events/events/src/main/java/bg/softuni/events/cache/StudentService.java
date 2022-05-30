package bg.softuni.events.cache;

import java.util.List;
import java.util.Random;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Cacheable("students")
  public List<Student> getAllStudents() {
    System.out.println("I'm doing heavy and complicated work to generate students....");

    return generateStudents();
  }

  @CachePut("students")
  public List<Student> updateAllStudents() {
    System.out.println("I'm updating all the students");
    return getAllStudents();
  }

  @CacheEvict(cacheNames = "students", allEntries = true)
  public void evictCache() {
    System.out.println("Evicting cache!");
  }

  private List<Student> generateStudents() {
    Random ageGen = new Random();
    Student pesho = new Student().setAge(20 + ageGen.nextInt(40)).setName("Pesho");
    Student anna = new Student().setAge(20 + ageGen.nextInt(40)).setName("Anna");

    return List.of(pesho, anna);
  }
}
