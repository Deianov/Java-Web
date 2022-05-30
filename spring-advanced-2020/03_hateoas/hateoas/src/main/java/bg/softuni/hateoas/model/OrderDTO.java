package bg.softuni.hateoas.model;

import org.aspectj.weaver.ast.Or;

public class OrderDTO {

  private Long id;
  private Long studentId;
  private Long courseId;

  public Long getId() {
    return id;
  }

  public OrderDTO setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getStudentId() {
    return studentId;
  }

  public OrderDTO setStudentId(Long studentId) {
    this.studentId = studentId;
    return this;
  }

  public Long getCourseId() {
    return courseId;
  }

  public OrderDTO setCourseId(Long courseId) {
    this.courseId = courseId;
    return this;
  }

  public static OrderDTO asDTO(Order order) {
    return new OrderDTO().setCourseId(order.getCourse().getId())
        .setStudentId(order.getStudent().getId()).setId(order.getId());
  }
}
