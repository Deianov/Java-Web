package bg.softuni.security.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column
  private boolean enabled;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER
  )
  private List<AuthorityEntity> authorities = new ArrayList<>();

  public long getId() {
    return id;
  }

  public UserEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public UserEntity setEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public List<AuthorityEntity> getAuthorities() {
    return authorities;
  }

  public UserEntity setAuthorities(
      List<AuthorityEntity> authorities) {
    this.authorities = authorities;
    return this;
  }
}
