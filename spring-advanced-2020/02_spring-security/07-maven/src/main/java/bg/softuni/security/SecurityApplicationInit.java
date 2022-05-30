package bg.softuni.security;

import bg.softuni.security.model.AuthorityEntity;
import bg.softuni.security.model.UserEntity;
import bg.softuni.security.repository.UserRepository;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityApplicationInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityApplicationInit(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.count() == 0) {

            UserEntity user = new UserEntity();
            user.setName("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEnabled(true);

            AuthorityEntity authorityEntity = new AuthorityEntity();
            authorityEntity.setName("ROLE_USER");
            authorityEntity.setUser(user);
            user.setAuthorities(List.of(authorityEntity));

            userRepository.save(user);

            UserEntity admin = new UserEntity();
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEnabled(true);

            AuthorityEntity adminUserAuthorityEntity = new AuthorityEntity();
            adminUserAuthorityEntity.setName("ROLE_USER");
            adminUserAuthorityEntity.setUser(admin);

            AuthorityEntity adminAdminAuthorityEntity = new AuthorityEntity();
            adminAdminAuthorityEntity.setName("ROLE_ADMIN");
            adminAdminAuthorityEntity.setUser(admin);

            admin.setAuthorities(List.of(adminUserAuthorityEntity, adminAdminAuthorityEntity));

            userRepository.save(admin);
        }

    }
}
