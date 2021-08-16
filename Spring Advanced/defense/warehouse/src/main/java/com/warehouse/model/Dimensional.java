package com.warehouse.model;

public interface Dimensional {
    double getWidth();
    double getLength();
    double getRadius();
    double getHeight();
    double getVolume();
    double getWeight();

    void setWidth(double width);
    void setLength(double length);
    void setRadius(double radius);
    void setHeight(double height);
    void setWeight(double weight);
}
