package judge.service;

import judge.constant.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import judge.model.entity.User;
import judge.model.service.UserServiceModel;
import judge.repository.UserRepository;

import java.util.NoSuchElementException;

import static judge.constant.Constants.ROLE_ADMIN;
import static judge.constant.Constants.ROLE_USER;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, RoleService roleService, ModelMapper mapper) {
        this.repository = repository;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        // first run -> ADMIN
        userServiceModel
                .setRole(this.roleService
                        .findByName(this.repository.count() == 0 ? ROLE_ADMIN : ROLE_USER));

        User user = this.mapper.map(userServiceModel, User.class);

        return this.mapper.map(this.repository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel login(String username, String password) {
        User user = repository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new NoSuchElementException(Constants.USER_LOGIN_INCORRECT_MESSAGE));
        return mapper.map(user, UserServiceModel.class);
    }
}
