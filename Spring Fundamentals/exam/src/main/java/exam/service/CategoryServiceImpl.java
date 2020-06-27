package exam.service;

import exam.constant.Constants;
import exam.model.entity.Category;
import exam.model.entity.CategoryType;
import exam.model.service.CategoryServiceModel;
import exam.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init(){
        if(this.repository.count() == 0) {
            CategoryType[] categories = CategoryType.values();

            for (CategoryType categoryType : categories) {
                Category category = new Category(categoryType, "---");
                repository.saveAndFlush(category);
            }
        }
    }

    @Override
    public CategoryServiceModel getByName(String name) {
        CategoryType type = CategoryType.valueOf(name);
        Category category = repository.findByName(type)
                .orElseThrow(() -> new NoSuchElementException("Not found category"));

        return mapper.map(category, CategoryServiceModel.class);
    }
}
