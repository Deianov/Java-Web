package partone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import partone.model.entity.User;
import partone.model.service.UserServiceModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
