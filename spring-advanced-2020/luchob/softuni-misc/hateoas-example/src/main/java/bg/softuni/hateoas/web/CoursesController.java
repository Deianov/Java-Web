package bg.softuni.hateoas.web;

import bg.softuni.hateoas.model.Course;
import bg.softuni.hateoas.model.OrderDTO;
import bg.softuni.hateoas.repository.CourseRepository;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/courses")
public class CoursesController {

  private final CourseRepository courseRepository;

  @Autowired
  public CoursesController(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<Course>>> getAllCourses() {

    List<EntityModel<Course>> courses = courseRepository.findAll().stream()
        .map(course -> EntityModel.of(course, createCourseLinks(course)))
        .collect(Collectors.toList());

    return ResponseEntity.ok(
        CollectionModel.of(courses,
            linkTo(methodOn(CoursesController.class).getAllCourses()).withSelfRel()));
  }



  @PostMapping
  public ResponseEntity<EntityModel<Course>> createCourse(Course course) {

    if (course.getId() != null) {
      return ResponseEntity.badRequest().build();
    }
    Course result = courseRepository.save(course);
    return ResponseEntity.ok(EntityModel.of(result, createCourseLinks(course)));
  }


  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<Course>> getCourse(@PathVariable("id") Long id) {

    Optional<Course> courseOpt = this.courseRepository
        .findById(id);

    return courseOpt.
        map(
            c -> EntityModel.of(c, createCourseLinks(c))
        ).map(ResponseEntity::ok).
        orElse(ResponseEntity.notFound().build());
  }

  private Link[] createCourseLinks(Course course) {

    List<Link> result = new ArrayList<>();

    Link self = linkTo(methodOn(CoursesController.class)
        .getCourse(course.getId()))
        .withSelfRel();
    result.add(self);

    if (course.isEnabled()) {

      Link employ = linkTo(methodOn(OrdersController.class).
          createOrder(new OrderDTO().setCourseId(course.getId()))).
          withRel("employ");

      result.add(employ);
    }

    return result.toArray(new Link[0]);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Course> delete(@PathVariable("id") Long id) {

    this.courseRepository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
