package judge.service;

import judge.model.service.UserServiceModel;


public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);
    UserServiceModel login(String username, String password);
}
