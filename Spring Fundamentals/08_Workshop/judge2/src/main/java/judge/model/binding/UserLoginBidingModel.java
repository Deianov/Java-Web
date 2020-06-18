package judge.model.binding;


import javax.validation.constraints.NotNull;

public class UserLoginBidingModel {
    private String username;
    private String password;

    public UserLoginBidingModel() {
    }

    @NotNull(message = "Username cannot be null")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "Password cannot be empty!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
