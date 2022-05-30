package exam.model.bind;

import exam.model.entity.CategoryType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryBindModel{
    private CategoryType name;
    private String description;

    public CategoryBindModel() {
    }

    @NotNull
    @NotEmpty
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
