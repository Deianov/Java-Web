package bg.softuni.hateoas.model;

import org.springframework.hateoas.RepresentationModel;

public class OrderDTO {

  private long id;
  private long studentId;
  private long courseId;

  public long getId() {
    return id;
  }

  public OrderDTO setId(long id) {
    this.id = id;
    return this;
  }

  public long getStudentId() {
    return studentId;
  }

  public OrderDTO setStudentId(long studentId) {
    this.studentId = studentId;
    return this;
  }

  public long getCourseId() {
    return courseId;
  }

  public OrderDTO setCourseId(long courseId) {
    this.courseId = courseId;
    return this;
  }

  public static OrderDTO asDTO(Order order) {
    return new OrderDTO().
        setId(order.getId()).
        setCourseId(order.getCourse().getId()).
        setStudentId(order.getStudent().getId());
  }

}
