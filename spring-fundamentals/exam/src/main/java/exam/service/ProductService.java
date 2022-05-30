package exam.service;

import exam.model.entity.CategoryType;
import exam.model.service.ProductServiceModel;
import exam.model.view.ProductViewModel;

import java.util.Collection;

public interface ProductService {
    ProductServiceModel addProduct(ProductServiceModel productServiceModel);
    Collection<ProductViewModel> getProducts(CategoryType categoryType);

    void buyById(String id);
    void buyAll();
}
