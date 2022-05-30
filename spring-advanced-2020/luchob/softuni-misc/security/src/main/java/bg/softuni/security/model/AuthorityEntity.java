package bg.softuni.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="authority")
public class AuthorityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id", nullable = false, updatable = false)
  private long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name="user_id", nullable = false)
  private UserEntity user;

  public long getId() {
    return id;
  }

  public AuthorityEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public AuthorityEntity setName(String name) {
    this.name = name;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public AuthorityEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }
}
