package partone.service;

import partone.model.service.RoleServiceModel;

import java.util.Optional;

public interface RoleService {
    RoleServiceModel findByName(String name);
}
