package com.warehouse.model.entity;

import javax.persistence.*;

@Entity
@Table(name="authority")
public class AuthorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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
