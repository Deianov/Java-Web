package bg.softuni.security.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final DataSource dataSource;

  public SecurityConfig(PasswordEncoder passwordEncoder,
      DataSource dataSource) {
    this.passwordEncoder = passwordEncoder;
    this.dataSource = dataSource;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.
        jdbcAuthentication().
        withDefaultSchema().
        dataSource(dataSource).
        passwordEncoder(passwordEncoder).
        withUser("user").password(passwordEncoder.encode("user")).roles("USER").and().
        withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN", "USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
        authorizeRequests().
        antMatchers("/h2_console/**", "/home").permitAll(). // h2-console?
        antMatchers("/admin").hasRole("ADMIN").
        antMatchers("/user").hasRole("USER").
        and().
        formLogin();

    //h2 magic, TODO: delete me
    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
