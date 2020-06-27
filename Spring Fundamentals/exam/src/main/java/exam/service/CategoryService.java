package exam.service;

import exam.model.service.CategoryServiceModel;

public interface CategoryService {
    CategoryServiceModel getByName(String name);
}
