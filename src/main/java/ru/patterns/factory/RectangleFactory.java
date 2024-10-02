package ru.patterns.factory;

import ru.patterns.models.Rectangle;
import ru.patterns.models.Shape;

/** Паттерн Factory Method */
public class RectangleFactory implements ShapeFactory {

    private double width;
    private double height;

    public RectangleFactory(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape createShape() {
        return new Rectangle(width, height);
    }
}
