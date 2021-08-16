package com.warehouse.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;


@MappedSuperclass
public abstract class Dimension implements Dimensional {

    private static final boolean CALCULATE_BOUNDING_BOX = true;

    @Min(value = 0)
    @Column
    private double width = 0;

    @Min(value = 0)
    @Column
    private double length = 0;

    @Min(value = 0)
    @Column
    private double radius = 0;

    @Min(value = 0)
    @Column
    private double height = 0;

    @Min(value = 0)
    @Column
    private double weight = 0;


    public Dimension() { }
    public Dimension(double width, double length, double height, double weight) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public double getVolume() {
        double volume;

        // circle area
        if (radius != 0) {

            // sphere
            if (height == 0) {

                if(CALCULATE_BOUNDING_BOX) {
                    volume = Math.pow(radius, 3);
                } else {
                    volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
                }

            // cylinder
            } else {
                if(CALCULATE_BOUNDING_BOX) {
                    volume = radius * radius * height;
                } else {
                    volume = Math.PI * radius * radius * height;
                }
            }

        // rectangle area
        } else {
            volume = width * length * height;
        }

        return volume;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
