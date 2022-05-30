package exam.model.bind;

import exam.constant.Constants;
import javax.validation.constraints.NotEmpty;

public class UserLoginBindModel {
    private String username;
    private String password;

    public UserLoginBindModel() {
    }

    @NotEmpty(message = Constants.USER_NAME_CANNOT_BE_EMPTY_MESSAGE)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = Constants.USER_PASSWORD_CANNOT_BE_EMPTY_MESSAGE)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
