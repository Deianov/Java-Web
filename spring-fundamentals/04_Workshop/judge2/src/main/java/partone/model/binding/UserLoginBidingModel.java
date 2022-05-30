package partone.model.binding;

import org.hibernate.validator.constraints.Length;
import partone.constant.constant;

public class UserLoginBidingModel {
    private String username;
    private String password;

    public UserLoginBidingModel() {
    }

    @Length(min = 2, max = 10, message = constant.USER_NAME_LENGTH_MESSAGE)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // TODO: 02.06.2020 @
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
