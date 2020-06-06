package vehicles.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Model> models = new ArrayList<>();

    private Date created = new Date();
    private Date modified = new Date();

    public Brand() { }
    public Brand(@NotNull @Size(min = 2, max = 40) String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public static Brand create(String name, Set<Model> models) {
        Brand brand = new Brand(name);
        models.forEach(model -> {
            model.setBrand(brand);
            brand.getModels().add(model);
        });
        return brand;
    }
}
