package vehicles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vehicles.model.Model;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByName(String name);
    Optional<Model> findById(Long id);
}
