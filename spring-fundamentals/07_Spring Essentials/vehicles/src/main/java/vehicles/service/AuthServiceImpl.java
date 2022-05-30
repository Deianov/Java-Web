package vehicles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicles.constant.Constants;
import vehicles.exception.InvalidEntityException;
import vehicles.model.User;
import vehicles.model.enums.Role;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User register(User user) {
        if(user.getRoles().contains(Role.ADMIN)) {
            throw new InvalidEntityException(Constants.ADMIN_SELF_REGISTER);
        }
        return userService.createUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userService.getUserByUsername(username);
        if(user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
