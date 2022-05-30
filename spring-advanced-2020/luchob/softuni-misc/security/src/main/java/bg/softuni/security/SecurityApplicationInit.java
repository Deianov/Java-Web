package bg.softuni.security;

import bg.softuni.security.model.AuthorityEntity;
import bg.softuni.security.model.UserEntity;
import bg.softuni.security.repository.UserRepository;
import com.sun.tools.javac.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityApplicationInit  implements CommandLineRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SecurityApplicationInit(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args)  {
    UserEntity userEntity = new UserEntity();
    userEntity.setName("user");
    userEntity.setPassword(passwordEncoder.encode("user1"));
    userEntity.setEnabled(true);

    AuthorityEntity authority = new AuthorityEntity();
    authority.setName("ROLE_USER");
    authority.setUser(userEntity);

    userEntity.setAuthorities(List.of(authority));

    UserEntity admin = new UserEntity();
    admin.setName("admin");
    admin.setPassword(passwordEncoder.encode("admin1"));
    admin.setEnabled(true);

    AuthorityEntity authorityUser = new AuthorityEntity();
    authorityUser.setName("ROLE_USER");
    authorityUser.setUser(admin);

    AuthorityEntity authorityAdmin = new AuthorityEntity();
    authorityAdmin.setName("ROLE_ADMIN");
    authorityAdmin.setUser(admin);

    admin.setAuthorities(List.of(authorityUser, authorityAdmin));

    userRepository.save(userEntity);
    userRepository.save(admin);


  }
}
