package vehicles.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import vehicles.model.Model;
import vehicles.repository.BrandRepository;
import vehicles.exception.EntityNotFoundException;
import vehicles.model.Brand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vehicles.web.bind.ModelBinding;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;
    private final ModelMapper mapper;
    //    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public BrandServiceImpl(BrandRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Collection<Brand> getBrands() {
        return repository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public Brand getBrandByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    @Override
    public Brand getBrandById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Brand with ID=%s not found.", id)));
    }

    @Override
    public Brand createBrand(@Valid Brand brand) {
        if(brand.getCreated() == null) {
            brand.setCreated(new Date());
        }
        brand.setModified(brand.getCreated());

        return repository.save(brand);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        brand.setModified(new Date());
        Brand old = getBrandById(brand.getId());
        if(old == null) {
            throw new EntityNotFoundException(String.format("Brand with ID=%s not found.", brand.getId()));
        }
        return repository.save(brand);
    }

    @Override
    public Brand deleteBrand(Long id) {
        Brand old = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Brand with ID=%s not found.", id)));
        repository.deleteById(id);
        return old;
    }

    @Override
    public long getBrandsCount() {
        return repository.count();
    }

    // Declarative transaction
    @Transactional
    public List<Brand> createBrandsBatch(List<Brand> brands) {
        List<Brand> created = brands.stream()
                .map(brand -> {
                    Brand resultBrand = createBrand(brand);
                    return resultBrand;
                }).collect(Collectors.toList());
        return created;
    }

    @Override
    public boolean addModel(ModelBinding binding) {
        if(binding == null || binding.getBrand() == null){
            return false;
        }
        Model model = mapper.map(binding, Model.class);
        Brand brand = this.repository.findByName(binding.getBrand()).orElse(new Brand(binding.getBrand()));
        model.setBrand(brand);
        brand.getModels().add(model);
        this.repository.save(brand);
        return true;
    }
}
