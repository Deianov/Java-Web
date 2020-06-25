package judge.service;

import judge.model.service.CommentServiceModel;
import judge.model.service.HomeworkServiceModel;

public interface HomeworkService {
    void addHomework(HomeworkServiceModel homeworkServiceModel);
    void addCommentToHomework(CommentServiceModel commentServiceModel);
    HomeworkServiceModel getLastAdded(String authorId);
    HomeworkServiceModel getRandom(String currentUserId);
}
