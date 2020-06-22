package judge.repository;

import judge.model.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, String> {
    Optional<Homework> findByExercise_Name(String name);
}
