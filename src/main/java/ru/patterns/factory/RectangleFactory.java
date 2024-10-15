package ru.patterns.factory;

import ru.patterns.interfaces.Color;
import ru.patterns.models.Rectangle;
import ru.patterns.models.Shape;

/** Паттерн Factory Method */
public class RectangleFactory implements ShapeFactory {

    private double width;
    private double height;
    private Color color;

    public RectangleFactory(double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public Shape createShape() {
        return new Rectangle(width, height, color);
    }
}
