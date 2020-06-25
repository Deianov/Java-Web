package judge.repository;

import judge.model.entity.Comment;
import judge.model.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    Collection<Comment> findAllByHomework(Homework homework);
    Collection<Comment> findAllByAuthor_Id(String authorId);
}
