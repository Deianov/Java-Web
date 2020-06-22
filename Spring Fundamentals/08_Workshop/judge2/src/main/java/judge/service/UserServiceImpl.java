package judge.service;

import judge.constant.Constants;
import judge.exception.AlreadyExistsException;
import judge.exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import judge.model.entity.User;
import judge.model.service.UserServiceModel;
import judge.repository.UserRepository;

import java.util.NoSuchElementException;

import static judge.constant.Constants.*;
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
        userServiceModel.setRole(this.roleService
                        .findByName(this.repository.count() == 0 ? ROLE_ADMIN : ROLE_USER));

        if (repository.existsByUsername(userServiceModel.getUsername())) {
            throw new AlreadyExistsException(USER_NAME_FIELD, USER_NAME_EXISTS_MESSAGE);
        }
        if (repository.existsByEmail(userServiceModel.getEmail())) {
            throw new AlreadyExistsException(USER_EMAIL_FIELD, USER_EMAIL_EXISTS_MASSAGE);
        }
        if (repository.existsByGit(userServiceModel.getGit())) {
            throw new AlreadyExistsException(USER_GIT_FIELD, USER_GIT_EXISTS_MASSAGE);
        }

        User user = this.mapper.map(userServiceModel, User.class);
        return this.mapper.map(this.repository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel login(String username, String password) {
        User user = repository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new NoSuchElementException(USER_LOGIN_INCORRECT_MESSAGE));
        return mapper.map(user, UserServiceModel.class);
    }

    @Override
    public boolean isAuthorizedUser(UserServiceModel userServiceModel, String role) {
        User user = repository.findById(userServiceModel.getId()).orElse(null);

        if (user != null &&
                user.getUsername().equals(userServiceModel.getUsername()) &&
                user.getPassword().equals(userServiceModel.getPassword())) {

            if(role != null && !user.getRole().getName().equals(role)) {
                throw new SecurityException(FAILED_TO_AUTHENTICATE_USER);
            }

            return true;
        }
        throw new EntityNotFoundException(USER_NOT_AUTHORISED_MESSAGE);
    }

    @Override
    public void grantAuthority(UserServiceModel admin, String id, String role) {
        if (isAuthorizedUser(admin, ROLE_ADMIN)) {
            try {
                User user = repository.findById(id).orElseThrow();
                UserServiceModel userServiceModel = mapper.map(user, UserServiceModel.class);
                userServiceModel.setRole(roleService.findByName(role));
                repository.save(mapper.map(userServiceModel, User.class));

            } catch (Exception ex) {
                throw new EntityNotFoundException("Unable to grant authority.");
            }
        }
    }

    @Override
    public UserServiceModel[] getUsers() {
        return repository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserServiceModel.class))
                .toArray(UserServiceModel[]::new);
    }
}
