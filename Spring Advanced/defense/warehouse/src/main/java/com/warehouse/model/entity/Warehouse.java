package com.warehouse.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Warehouse extends BaseLongEntity {

    private String name;

    @Column(name = "square_metres")
    private double area;

    // TODO: 19.07.2020  
    @Column(name = "cubic_meters")
    private double volume;

    @Column(name = "max_height")
    private double height;

    public Warehouse() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
