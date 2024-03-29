package bg.softuni.hateoas.web;

import bg.softuni.hateoas.model.Course;
import bg.softuni.hateoas.model.Order;
import bg.softuni.hateoas.model.OrderDTO;
import bg.softuni.hateoas.model.Student;
import bg.softuni.hateoas.repository.CourseRepository;
import bg.softuni.hateoas.repository.OrderRepository;
import bg.softuni.hateoas.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrderRepository orderRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public OrdersController(OrderRepository orderRepository,
        StudentRepository studentRepository,
        CourseRepository courseRepository) {
        this.orderRepository = orderRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getOrders() {
       List<Order> orders =
           this.orderRepository.findAll();
       List<EntityModel<OrderDTO>> orderDTOs = orders.
           stream().
           map(OrderDTO::asDTO).
           map(dto -> EntityModel.of(dto, buildOrderLinks(dto))).
           collect(Collectors.toList());

       return ResponseEntity.ok(CollectionModel.of(
           orderDTOs,
           linkTo(methodOn(OrdersController.class).getOrders()).withSelfRel()
       ));
    }

    @PostMapping
    public ResponseEntity<EntityModel<OrderDTO>> createOrder(@RequestBody OrderDTO orderDTO) {
        // TODO: Error handling, service layer
        Student student = studentRepository.getOne(orderDTO.getStudentId());
        Course course = courseRepository.getOne(orderDTO.getCourseId());

        Order newOrder = new Order();
        newOrder.setStudent(student);
        newOrder.setCourse(course);

        Order savedOrder = this.orderRepository.save(newOrder);
        OrderDTO savedOrderDTO = OrderDTO.asDTO(savedOrder);
        buildOrderLinks(savedOrderDTO);

        return ResponseEntity.ok(EntityModel.of(savedOrderDTO,
            buildOrderLinks(savedOrderDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OrderDTO>> getOrder(@PathVariable("id") Long id) {

        Optional<Order> orderOpt = this.orderRepository.findById(id);

        return orderOpt.
            map(OrderDTO::asDTO).
            map(dto -> EntityModel.of(dto, buildOrderLinks(dto))).
            map(ResponseEntity::ok).
            orElse(ResponseEntity.notFound().build());
    }

    private Link[] buildOrderLinks(OrderDTO order) {

        Link self = linkTo(methodOn(OrdersController.class)
            .getOrder(order.getId()))
            .withSelfRel();

        Link course = linkTo(methodOn(CoursesController.class)
            .getCourse(order.getCourseId()))
            .withRel("course");

        Link student = linkTo(methodOn(StudentsController.class)
            .getStudent(order.getStudentId()))
            .withRel("student");

        return List.of(self, course, student).toArray(new Link[0]);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<OrderDTO>> delete(@PathVariable("id") Long id) {
        this.orderRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
