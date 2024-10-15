package ru.patterns.factory;

import ru.patterns.interfaces.Color;
import ru.patterns.models.Circle;
import ru.patterns.models.Shape;

/** Паттерн Factory Method */
public class CircleFactory implements ShapeFactory {

    private double radius;
    private Color color;

    public CircleFactory(double radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Shape createShape() {
        return new Circle(radius, color);
    }
}