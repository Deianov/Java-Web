package bg.softuni.hateoas.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    private Long id;
    private String name;
    private Double price;
    private boolean enabled;

    public Course() {
    }


    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
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
