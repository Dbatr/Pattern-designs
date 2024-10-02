package ru.patterns.factory;

import ru.patterns.models.Shape;

/** Паттерн Factory Method */
public interface ShapeFactory {
    Shape createShape();
}
