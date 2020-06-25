package judge.constant;

public class Constants {

    // date
    public static final String DATE_CANNOT_BE_IN_FUTURE = "The date cannot be in the future";
    public static final String DATE_CANNOT_BE_IN_PAST = "The date cannot be in the past";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm";

    // roles | login
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    public static final String AUTHENTICATION_FAILED = "Please check your username/password ...";
    public static final String USER_UNAUTHORIZED = "Unauthorized user";
    public static final String USER_INVALID = "Invalid username or password";

    // user
    public static final String USER_NAME_FIELD = "username";
    public static final String USER_NAME_CANNOT_BE_EMPTY_MESSAGE = "Username cannot be empty";
    public static final String USER_NAME_LENGTH_MESSAGE = "Username must be between 2 and 10 characters";
    public static final String USER_NAME_EXISTS_MESSAGE = "Another user with this username already exists";

    public static final String USER_PASSWORD_CANNOT_BE_EMPTY_MESSAGE = "Password cannot be empty";
    public static final String USER_PASSWORD_LENGTH_MESSAGE = "Password must be between 3 and 10 characters";
    public static final String USER_PASSWORDS_DOES_NOT_MATCH_MESSAGE = "Password and Confirm Password does not match";
    public static final String USER_REGISTER_EXCEPTION_MESSAGE = "Unable to register user.";

    public static final String USER_EMAIL_FIELD = "email";
    public static final String USER_EMAIL_CANNOT_BE_EMPTY_MESSAGE = "Email cannot be empty";
    public static final String USER_EMAIL_REGEX = "\\b[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,63}\\b";
    public static final String USER_EMAIL_MESSAGE = "Enter valid email address";
    public static final String USER_EMAIL_EXISTS_MASSAGE = "This email address is already being used";

    public static final String USER_GIT_FIELD = "git";
    public static final String USER_GIT_CANNOT_BE_EMPTY_MESSAGE = "Git address cannot be empty";
    public static final String USER_GIT_PATTERN = "https:\\/\\/github\\.com\\/.+\\/.+";
    public static final String USER_GIT_MESSAGE = "Enter git address following this pattern";
    public static final String USER_GIT_MESSAGE2 = "Enter valid github address in pattern: https:/github.com/{username}/…";
    public static final String USER_GIT_EXISTS_MASSAGE = "An account with this Github address already exists";

    // exercise
    public static final String EXERCISE_NAME_FIELD = "name";
    public static final String EXERCISE_NAME_CANNOT_BE_EMPTY_MESSAGE = "Exercise Name cannot be empty";
    public static final int EXERCISE_NAME_LENGTH_MIN = 2;
    public static final String EXERCISE_NAME_LENGTH_MESSAGE = "Name length must be minimum two characters!";
    public static final String EXERCISE_NAME_EXISTS_MESSAGE = "Exercise with this name already exists";

    // homework
    public static final String HOMEWORK_GIT_EXISTS_MESSAGE = "This Git address already exists";

    // comments
    public static final String COMMENT_SCORE = "Score must be between 2 and 6 inclusive";
    public static final String COMMENT_LENGTH = "Comment text content must be more than 3 characters!";
}
