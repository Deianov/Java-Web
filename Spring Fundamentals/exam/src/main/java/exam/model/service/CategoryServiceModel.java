package exam.model.service;

import exam.model.entity.CategoryType;

public class CategoryServiceModel {
    private Integer id;
    private CategoryType name;
    private String description;

    public CategoryServiceModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryType getName() {
        return name;
    }

    public void setName(CategoryType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
