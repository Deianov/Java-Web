package judge.model.binding;

import judge.constant.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class HomeworkBindingModel {

    private LocalDateTime addedOn = LocalDateTime.now();
    private String git;
    private String author;
    private ExerciseBindingModel exercise;

    public HomeworkBindingModel() {
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    @NotNull
    @Pattern(regexp = Constants.USER_GIT_PATTERN, message = Constants.USER_GIT_MESSAGE)
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    @NotNull
    @NotEmpty
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @NotNull
    public ExerciseBindingModel getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseBindingModel exercise) {
        this.exercise = exercise;
    }
}
