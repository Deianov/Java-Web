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
    //do very slow calculation
    System.out.println("Very slow calculation done");

    return getRandomStudents();
  }

  @CachePut("students")
  public List<Student> updateStudents() {
    System.out.println("Updating students cache");
    return getRandomStudents();
  }

  private List<Student> getRandomStudents() {
    Student student1 = new Student().setName("Pesho").setAge(new Random().nextInt(10) + 20);
    Student student2 = new Student().setName("Anna").setAge(new Random().nextInt(10) + 20);

    return List.of(student1, student2);
  }

  @CacheEvict(cacheNames="students", allEntries=true)
  public void evictCache() {
  }

}
