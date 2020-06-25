package judge.service;

import judge.constant.Constants;
import judge.exception.AlreadyExistsException;
import judge.model.entity.Homework;
import judge.model.service.CommentServiceModel;
import judge.model.service.ExerciseServiceModel;
import judge.model.service.HomeworkServiceModel;
import judge.model.service.UserServiceModel;
import judge.repository.HomeworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository repository;
    private final ModelMapper mapper;
    private final UserService userService;
    private final CommentService commentService;
    private final ExerciseService exerciseService;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository repository, ModelMapper mapper, UserService userService, CommentService commentService, ExerciseService exerciseService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
        this.commentService = commentService;
        this.exerciseService = exerciseService;
    }

    @Override
    public void addHomework(HomeworkServiceModel homeworkServiceModel) {

        if(homeworkServiceModel == null) {
            throw new IllegalArgumentException("Not found homework");
        }

        UserServiceModel userServiceModel = homeworkServiceModel.getAuthor();
        if (!userService.isAuthorizedUser(userServiceModel, null)) {
            throw new SecurityException(Constants.USER_UNAUTHORIZED);
        }

        if (homeworkServiceModel.getExercise().getDueDate().isBefore(homeworkServiceModel.getAddedOn())) {
            throw new IllegalArgumentException("To late.");
        }

        Homework current = repository
                .findFirstByAuthor_IdAndExercise_Name(
                        userServiceModel.getId(),
                        homeworkServiceModel.getExercise().getName()
                )
                .orElse(null);

        // check git address
        if (repository.findAllByGit(homeworkServiceModel.getGit())
                .stream()
                .anyMatch(homework -> current == null || !homework.getId().equals(current.getId()))) {

            throw new AlreadyExistsException("git", Constants.HOMEWORK_GIT_EXISTS_MESSAGE);
        }

        if (current == null) {
            // add
            repository.save(mapper.map(homeworkServiceModel, Homework.class));

        } else {
            // update
            current.setAddedOn(LocalDateTime.now());
            current.setGit(homeworkServiceModel.getGit());
            repository.save(current);
        }
    }

    @Override
    public void addCommentToHomework(CommentServiceModel commentServiceModel) {
        repository.findById(commentServiceModel.getHomework().getId())
                .ifPresent(homework -> commentService.addComment(commentServiceModel, homework));
    }

    @Override
    public HomeworkServiceModel getLastAdded(String authorId) {
        Homework homework = repository.findFirstByAuthor_IdOrderByAddedOnDesc(authorId).orElse(null);
        return homework == null ? null : mapper.map(homework, HomeworkServiceModel.class);
    }

    @Override
    public HomeworkServiceModel getRandom(String currentUserId) {

        ExerciseServiceModel lastExercise = exerciseService.getLast();

        if (lastExercise != null &&
                lastExercise.getDueDate().isAfter(LocalDateTime.now())) {

            // remove current user
            List<Homework> collection =
                    repository.findAllByExercise_Id(lastExercise.getId())
                    .stream()
                    .filter(homework -> !homework.getAuthor().getId().equals(currentUserId))
                    .collect(Collectors.toList());

            if (!collection.isEmpty()) {
                int index = ThreadLocalRandom.current().nextInt(collection.size());
                return mapper.map(collection.get(index),HomeworkServiceModel.class );
            }
        }
        return null;
    }
}
