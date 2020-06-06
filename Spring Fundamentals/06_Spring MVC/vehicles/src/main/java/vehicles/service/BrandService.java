package vehicles.service;

import vehicles.model.Brand;
import vehicles.web.bind.ModelBinding;

import java.util.Collection;
import java.util.List;

public interface BrandService {
    Collection<Brand> getBrands();
    boolean existsByName(String name);
    Brand getBrandByName(String name);
    Brand getBrandById(Long id);
    Brand createBrand(Brand post);
    Brand updateBrand(Brand post);
    Brand deleteBrand(Long id);
    long getBrandsCount();
    List<Brand> createBrandsBatch(List<Brand> posts);

    // models
    boolean addModel(ModelBinding binding);
}
