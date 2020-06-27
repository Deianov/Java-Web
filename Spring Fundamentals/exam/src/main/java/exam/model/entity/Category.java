package exam.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    private Integer id;
    private CategoryType name;
    private String description;

    public Category() {
    }

    public Category(CategoryType name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    public CategoryType getName() {
        return name;
    }

    public void setName(CategoryType name) {
        this.name = name;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
