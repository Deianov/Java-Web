package exam.service;

import exam.constant.Constants;
import exam.exception.AlreadyExistsException;
import exam.model.entity.User;
import exam.model.service.UserServiceModel;
import exam.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static exam.constant.Constants.*;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {

        if (repository.existsByUsername(userServiceModel.getUsername())) {
            throw new AlreadyExistsException("username", USER_NAME_EXISTS_MESSAGE);
        }
        if (repository.existsByEmail(userServiceModel.getEmail())) {
            throw new AlreadyExistsException("email", USER_EMAIL_EXISTS_MASSAGE);
        }

        User user = this.mapper.map(userServiceModel, User.class);
        return this.mapper.map(this.repository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel login(String username, String password) {
        User user = repository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new NoSuchElementException(Constants.USER_INVALID));
        return mapper.map(user, UserServiceModel.class);
    }

    @Override
    public Collection<UserServiceModel> getUsers() {
        return repository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserServiceModel.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<String> getNames() {
        return repository.getNames();
    }

}
