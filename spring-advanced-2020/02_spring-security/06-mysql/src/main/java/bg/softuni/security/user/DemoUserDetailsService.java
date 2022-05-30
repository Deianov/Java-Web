package bg.softuni.security.user;

import bg.softuni.security.model.AuthorityEntity;
import bg.softuni.security.model.UserEntity;
import bg.softuni.security.repository.UserRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DemoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DemoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userEntityOpt = userRepository.findByName(username);
        return userEntityOpt.map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return new User(
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.
                        getAuthorities().
                        stream().
                        map(this::map).
                        collect(Collectors.toList())
        );
    }

    private GrantedAuthority map(AuthorityEntity authorityEntity) {
        return new SimpleGrantedAuthority(authorityEntity.getName());
    }
}