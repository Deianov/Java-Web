package com.warehouse.model.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseLongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
    @Access(AccessType.PROPERTY)
    private long id;

    public BaseLongEntity() { }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }
}
