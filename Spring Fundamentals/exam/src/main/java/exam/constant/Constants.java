package exam.constant;

public class Constants {

    // date
    public static final String DATE_CANNOT_BE_IN_FUTURE = "The date cannot be in the future";
    public static final String DATETIME_CANNOT_BE_IN_PAST = "Date and Time cannot be in the past";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm";

    // login
    public static final String USER_UNAUTHORIZED = "Unauthorized user";
    public static final String USER_INVALID = "Invalid username or password";

    // user
    public static final String USER_NAME_CANNOT_BE_EMPTY_MESSAGE = "Username cannot be empty";
    public static final String USER_NAME_LENGTH_MESSAGE = "Username must be between 3 and 20 characters";
    public static final String USER_NAME_EXISTS_MESSAGE = "Another user with this username already exists";

    public static final String USER_PASSWORD_CANNOT_BE_EMPTY_MESSAGE = "Password cannot be empty";
    public static final String USER_PASSWORD_LENGTH_MESSAGE = "Password must be between 3 and 20 characters";
    public static final String USER_PASSWORDS_DOES_NOT_MATCH_MESSAGE = "Password and Confirm Password does not match";
    public static final String USER_REGISTER_EXCEPTION_MESSAGE = "Unable to register user.";

    public static final String USER_EMAIL_CANNOT_BE_EMPTY_MESSAGE = "Email cannot be empty";
    public static final String USER_EMAIL_REGEX = "\\b[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,63}\\b";
    public static final String USER_EMAIL_MESSAGE = "Enter valid email address";
    public static final String USER_EMAIL_EXISTS_MASSAGE = "This email address is already being used";

    // product
    public static final String PRODUCT_NAME_LENGTH_MESSAGE = "Name length must be between 3 and 20 characters (inclusive 3 and 20)";
    public static final String PRODUCT_NAME_EXISTS_MESSAGE = "Product name already exists";
    public static final String PRODUCT_DESCRIPTION_LENGTH_MESSAGE = "Description min length must be minimum 5(inclusive) characters";
    public static final String PRODUCT_PRICE_MESSAGE = "Price must be a positive number";
    public static final String PRODUCT_CATEGORY_MESSAGE = "Category cannot be null.";
}
