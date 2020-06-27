package exam.repository;

import exam.model.entity.CategoryType;
import exam.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByName(String name);
    Collection<Product> findAllByCategory_Name(CategoryType name);
    Optional<Product> findById(String id);
}
