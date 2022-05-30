package exam.service;

import exam.exception.AlreadyExistsException;
import exam.model.entity.CategoryType;
import exam.model.entity.Product;
import exam.model.service.ProductServiceModel;
import exam.model.view.ProductViewModel;
import exam.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static exam.constant.Constants.PRODUCT_NAME_EXISTS_MESSAGE;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {

        if (repository.existsByName(productServiceModel.getName())){
            throw new AlreadyExistsException("name", PRODUCT_NAME_EXISTS_MESSAGE);
        }

        Product product = mapper.map(productServiceModel, Product.class);
        return mapper.map(repository.save(product), ProductServiceModel.class);
    }

    @Override
    public Collection<ProductViewModel> getProducts(CategoryType name) {
        return repository.findAll()
                .stream()
                .filter(product -> product.getCategory().getName() == name)
                .map(product -> mapper.map(product, ProductViewModel.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void buyById(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    @Override
    public void buyAll() {
        repository.deleteAll();
    }
}
