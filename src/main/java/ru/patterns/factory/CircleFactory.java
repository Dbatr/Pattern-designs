package ru.patterns.factory;

import ru.patterns.models.Circle;
import ru.patterns.models.Shape;

/** Паттерн Factory Method */
public class CircleFactory implements ShapeFactory {

    private double radius;

    public CircleFactory(double radius) {
        this.radius = radius;
    }

    @Override
    public Shape createShape() {
        return new Circle(radius);
    }
}