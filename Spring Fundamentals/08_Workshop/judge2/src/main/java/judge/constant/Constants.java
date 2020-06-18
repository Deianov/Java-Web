package judge.constant;

public class Constants {

    // date
    public static final String DATE_CANNOT_BE_IN_FUTURE = "The date cannot be in the future";
    public static final String DATE_CANNOT_BE_IN_PAST = "The date cannot be in the past";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm";

    // roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    // user
    public static final String USER_NAME_LENGTH_MESSAGE = "Username must be between 2 and 10 characters";
    public static final String USER_PASSWORD_LENGTH_MESSAGE = "Password must be between 3 and 10 characters";
    public static final String USER_LOGIN_INCORRECT_MESSAGE = "Incorrect username or password";

    public static final String USER_EMAIL_REGEX = "\\b[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,63}\\b";
    public static final String USER_EMAIL_MESSAGE = "Enter valid email address";

    public static final String USER_GIT_PATTERN = "https:\\/\\/github\\.com\\/.+\\/.+";
    public static final String USER_GIT_MESSAGE = "Enter git address following this pattern";
    public static final String USER_GIT_MESSAGE2 = "Enter valid github address in pattern: https:/github.com/{username}/…";

    // exercise
    public static final String NAME_LENGTH_MESSAGE = "Name length must be minimum two characters!";


}
