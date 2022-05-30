package bg.softuni.hateoas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private Double price;
  private boolean enabled;

  public Long getId() {
    return id;
  }

  public Course setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Course setName(String name) {
    this.name = name;
    return this;
  }

  public Double getPrice() {
    return price;
  }

  public Course setPrice(Double price) {
    this.price = price;
    return this;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public Course setEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }
}
