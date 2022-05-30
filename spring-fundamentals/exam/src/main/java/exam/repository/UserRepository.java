package exam.repository;

import exam.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String id);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("select u.username from User u")
    Collection<String> getNames();

//    @Query(value =
//            "select u.id, u.username, u.password, u.email, u.git, u.role_id from users u " +
//            "left join comments c on u.id = c.author_id " +
//            "group by u.id " +
//            "order by avg(c.score) desc, u.username asc", nativeQuery = true)
//    Collection<User> findAllByScore();

//    @Query("select u from User u left join u.comments c group by u.id order by avg(c.score) desc, u.username asc")
//    Collection<User> findAllByScore();
}
