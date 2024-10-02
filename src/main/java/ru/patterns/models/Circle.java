package ru.patterns.models;

import jakarta.persistence.Entity;

@Entity
public class Circle extends Shape {

    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String draw() {
        return "Drawing a circle with radius: " + radius;
    }
}
