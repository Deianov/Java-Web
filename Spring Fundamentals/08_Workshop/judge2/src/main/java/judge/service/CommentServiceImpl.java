package judge.service;

import judge.model.entity.Comment;
import judge.model.entity.Homework;
import judge.model.service.CommentServiceModel;
import judge.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public CommentServiceImpl(CommentRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void addComment(CommentServiceModel commentServiceModel, Homework homework) {
        Comment comment = mapper.map(commentServiceModel, Comment.class);
        comment.setHomework(homework);
        repository.saveAndFlush(comment);
    }
}
