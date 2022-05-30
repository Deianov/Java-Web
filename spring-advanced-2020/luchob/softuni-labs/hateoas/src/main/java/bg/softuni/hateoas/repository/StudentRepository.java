package bg.softuni.hateoas.repository;

import bg.softuni.hateoas.model.Order;
import bg.softuni.hateoas.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
