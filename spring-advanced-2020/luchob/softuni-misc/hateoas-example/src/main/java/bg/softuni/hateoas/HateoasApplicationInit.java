package bg.softuni.hateoas;

import bg.softuni.hateoas.model.Course;
import bg.softuni.hateoas.model.Student;
import bg.softuni.hateoas.repository.CourseRepository;
import bg.softuni.hateoas.repository.OrderRepository;
import bg.softuni.hateoas.repository.StudentRepository;
import bg.softuni.hateoas.model.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HateoasApplicationInit implements CommandLineRunner {

  private final StudentRepository studentRepository;
  private final CourseRepository courseRepository;
  private final OrderRepository orderRepository;

  HateoasApplicationInit(StudentRepository studentRepository,
      CourseRepository courseRepository,
      OrderRepository orderRepository) {

    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
    this.orderRepository = orderRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Student pavel = new Student();
    pavel.setName("Pavel Ivanov");
    pavel.setAge(22);

    pavel = studentRepository.save(pavel);

    Course springAdvanced = new Course();
    springAdvanced.setName("Spring Advanced");
    springAdvanced.setPrice(100.0);
    springAdvanced.setEnabled(true);

    springAdvanced = courseRepository.save(springAdvanced);

    Course springBasic = new Course();
    springBasic.setName("Spring basic");
    springBasic.setPrice(100.0);
    springBasic.setEnabled(false);

    courseRepository.save(springBasic);

    Order pavelSpring = new Order();
    pavelSpring.setCourse(springAdvanced);
    pavelSpring.setStudent(pavel);

    orderRepository.save(pavelSpring);

  }
}
