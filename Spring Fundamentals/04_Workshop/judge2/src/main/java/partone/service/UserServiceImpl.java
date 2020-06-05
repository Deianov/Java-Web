package partone.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import partone.model.entity.User;
import partone.model.service.UserServiceModel;
import partone.repository.UserRepository;

import java.util.Optional;

import static partone.constant.constant.ROLE_ADMIN;
import static partone.constant.constant.ROLE_USER;


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
}
