package bg.softuni.hateoas.web;

import bg.softuni.hateoas.model.OrderDTO;
import bg.softuni.hateoas.model.Student;
import bg.softuni.hateoas.repository.OrderRepository;
import bg.softuni.hateoas.repository.StudentRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentsController {

  private final StudentRepository studentRepository;
  private final OrderRepository orderRepository;

  public StudentsController(StudentRepository studentRepository,
      OrderRepository orderRepository) {

    this.studentRepository = studentRepository;
    this.orderRepository = orderRepository;
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<Student>> getStudent(@PathVariable(name = "id")
      Long id) {

    Optional<Student> studentOpt = this.studentRepository.findById(id);

    return studentOpt.
        map(s -> ResponseEntity.ok(EntityModel.of(s, getStudentLinks(s)))).
        orElse(ResponseEntity.notFound().build());
  }

  private Link[] getStudentLinks(Student student) {
    Link self = linkTo(methodOn(StudentsController.class)
        .getStudent(student.getId()))
        .withSelfRel();

    Link orders = linkTo(methodOn(StudentsController.class).getAllOrdersByStudentId(student.getId()))
        .withRel("orders");

    return List.of(self, orders).toArray(new Link[0]);
  }

  @GetMapping("/{id}/orders")
  public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getAllOrdersByStudentId(@PathVariable(name = "id")
      Long studentId) {
    List<EntityModel<OrderDTO>> orders =
        orderRepository.findAllByStudentId(studentId).
            stream().
            map(OrderDTO::asDTO).
            map(
                dto -> EntityModel.of(dto,
                    linkTo(methodOn(OrdersController.class).getOrder(dto.getId())).withSelfRel())
            ).
            collect(Collectors.toList());

    return ResponseEntity.ok(
        CollectionModel.of(orders,
            linkTo(methodOn(StudentsController.class).getAllOrdersByStudentId(studentId)).withSelfRel()));
  }

  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<Student>>> getAllStudents() {
    List<EntityModel<Student>> all = this.studentRepository
        .findAll().
            stream().
            map(s -> EntityModel.of(s, getStudentLinks(s))).
            collect(Collectors.toList());

    return ResponseEntity.ok(
        CollectionModel.of(all,
            linkTo(methodOn(StudentsController.class).getAllStudents()).withSelfRel()));
  }

  @PostMapping
  public ResponseEntity<EntityModel<Student>> createStudent(@RequestBody Student student) {
    if (student.getId() != null) {
      return ResponseEntity.badRequest().build();
    } else {
      Student savedStudent = studentRepository.save(student);
      return ResponseEntity.ok(
          EntityModel.of(savedStudent, getStudentLinks(savedStudent)));
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<Student>> updateStudent(@PathVariable(name = "id") Long id,
      @RequestBody Student editedStudent) {

    Optional<Student> studentOpt =
        this.studentRepository.findById(id);

    return studentOpt.map(s -> {
      this.studentRepository.save(editedStudent);
      return ResponseEntity.ok(EntityModel.of(s, getStudentLinks(s)));
    }).orElse(
        ResponseEntity.notFound().build()
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<EntityModel<Student>> deleteStudent(@PathVariable(name = "id") Long id) {

    this.studentRepository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
