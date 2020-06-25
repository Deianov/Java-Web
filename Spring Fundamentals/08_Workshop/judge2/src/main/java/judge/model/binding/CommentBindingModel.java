package judge.model.binding;


import judge.constant.Constants;

import javax.validation.constraints.*;

public class CommentBindingModel {
    private String score;
    private String textContent;

    public CommentBindingModel() {
    }

    @Pattern(regexp = "[2-6]", message = Constants.COMMENT_SCORE)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Size(min = 3, message = Constants.COMMENT_LENGTH)
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
