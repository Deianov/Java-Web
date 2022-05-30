package vehicles.constant;

import vehicles.model.enums.VehicleCategory;

public class Constants {

    // roles
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    // user
    public static final String USER_ALREADY_EXISTS = "User with username '%s' already exists.";

    // admin
    public static final String ADMIN_SELF_REGISTER = "Admins can not self-register.";

    // cars
    public static final VehicleCategory DEFAULT_CATEGORY = VehicleCategory.CAR;
    public static final String CARS_HARDCODE_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Land_Rover_Discovery_Sport_-_Static_%2815071077156%29.jpg/320px-Land_Rover_Discovery_Sport_-_Static_%2815071077156%29.jpg";
}
