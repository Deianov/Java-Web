package bg.softuni.hateoas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
  private Set<Order> orders;

  public Long getId() {
    return id;
  }

  public Student setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Student setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getAge() {
    return age;
  }

  public Student setAge(Integer age) {
    this.age = age;
    return this;
  }

  @JsonIgnore
  public Set<Order> getOrders() {
    return orders;
  }

  public Student setOrders(Set<Order> orders) {
    this.orders = orders;
    return this;
  }
}
