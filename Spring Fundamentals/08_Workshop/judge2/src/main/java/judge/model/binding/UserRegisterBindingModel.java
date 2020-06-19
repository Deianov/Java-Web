package judge.model.binding;

import judge.constant.Constants;

import javax.validation.constraints.*;

public class UserRegisterBindingModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String git;

    public UserRegisterBindingModel() {
    }

    @NotEmpty
    @Size(min = 2, max = 10, message = Constants.USER_NAME_LENGTH_MESSAGE)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    @Size(min = 3, max = 10, message = Constants.USER_PASSWORD_LENGTH_MESSAGE)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = Constants.USER_PASSWORD_CANNOT_BE_EMPTY_MESSAGE)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotEmpty
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty
    @Pattern(regexp = Constants.USER_GIT_PATTERN, message = Constants.USER_GIT_MESSAGE)
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
