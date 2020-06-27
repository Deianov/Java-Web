package exam.service;

import exam.model.service.UserServiceModel;

import java.util.Collection;


public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);
    UserServiceModel login(String username, String password);

    Collection<UserServiceModel> getUsers();
    Collection<String> getNames();

}
