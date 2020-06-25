package judge.service;

import judge.model.entity.Homework;
import judge.model.service.CommentServiceModel;

public interface CommentService {
    void addComment(CommentServiceModel commentServiceModel, Homework homework);
}
