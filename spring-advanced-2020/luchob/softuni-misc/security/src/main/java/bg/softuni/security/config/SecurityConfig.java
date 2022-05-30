package bg.softuni.security.config;

import bg.softuni.security.security.DemoUserDetailsService;
import javax.management.MXBean;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final DataSource dataSource;
  private final PasswordEncoder passwordEncoder;
  private final DemoUserDetailsService userDetailsService;

  public SecurityConfig(DataSource dataSource,
      PasswordEncoder passwordEncoder,
      DemoUserDetailsService userDetailsService) {
    this.dataSource = dataSource;
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.
        userDetailsService(userDetailsService).
        passwordEncoder(passwordEncoder);
//    auth.
//        inMemoryAuthentication().
//        withUser("user").password(passwordEncoder.encode("user")).roles("USER").and().
//        withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
//    auth.
//        jdbcAuthentication().
//        withDefaultSchema().
//        dataSource(dataSource).
//        passwordEncoder(passwordEncoder).
//        withUser("user").password(passwordEncoder.encode("user")).roles("USER").and().
//        withUser("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
        authorizeRequests().
        antMatchers("/h2_console/**", "/home").permitAll().
        antMatchers("/admin").hasRole("ADMIN").
        antMatchers("/user").hasRole("USER").
        and().formLogin();

    //h2 database
    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
