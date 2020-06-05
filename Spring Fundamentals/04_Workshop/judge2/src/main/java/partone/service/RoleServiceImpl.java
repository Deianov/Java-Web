package partone.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import partone.model.entity.Role;
import partone.model.service.RoleServiceModel;
import partone.repository.RoleRepository;

import javax.annotation.PostConstruct;

import static partone.constant.constant.ROLE_ADMIN;
import static partone.constant.constant.ROLE_USER;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // run with bin initialisation
    @PostConstruct
    public void init(){
        if(this.repository.count() == 0) {
            this.repository.save(new Role(ROLE_ADMIN));
            this.repository.save(new Role(ROLE_USER));
        }
    }

    @Override
    public RoleServiceModel findByName(String name) {
        return this.repository
                .findByName(name)
                .map(role -> this.mapper.map(role, RoleServiceModel.class))
                .orElse(null);
    }
}
