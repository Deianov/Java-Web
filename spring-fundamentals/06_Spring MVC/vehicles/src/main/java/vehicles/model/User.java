package vehicles.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import vehicles.model.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
import static vehicles.constant.Constant.ROLE_ADMIN;
import static vehicles.constant.Constant.ROLE_USER;

@Entity
@JsonIgnoreProperties({"authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"})
public class User  {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 60)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 60)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 60)
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull
    @Size(min = 4, max = 80)
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private boolean active = true;

//    @Size(min = 8, max = 512)
    private String imageUrl;

    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private Collection<Offer> offers = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modified = new Date();

    public User() { }
    public User(@NotNull @Size(min = 2, max = 60) String firstName, @NotNull @Size(min = 2, max = 60) String lastName, @NotNull @Size(min = 3, max = 60) String username, @NotNull @Size(min = 4, max = 80) String password, @NotNull Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static String getRoleUser() {
        return ROLE_USER;
    }

    public static String getRoleAdmin() {
        return ROLE_ADMIN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Collection<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Collection<Offer> offers) {
        this.offers = offers;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString(){
        return String.format("%s, %s %s", this.username, this.firstName, this.lastName);
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream()
//                .map(role -> role.toString() + "_ROLE")
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return active;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return active;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return active;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return active;
//    }

}
