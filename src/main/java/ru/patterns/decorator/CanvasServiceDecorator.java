package ru.patterns.decorator;

import ru.patterns.factory.ShapeFactory;
import ru.patterns.interfaces.CanvasServiceI;
import ru.patterns.interfaces.Color;
import ru.patterns.models.Canvas;
import ru.patterns.models.Shape;

import java.util.List;

/**
 * Паттерн Decorator.
 */
public class CanvasServiceDecorator implements CanvasServiceI {

    protected final CanvasServiceI decorated;

    public CanvasServiceDecorator(CanvasServiceI decorated) {
        this.decorated = decorated;
    }

    @Override
    public Canvas createCanvas(String name) {
        return decorated.createCanvas(name);
    }

    @Override
    public Shape addShapeToCanvas(Long canvasId, ShapeFactory factory, Color color) {
        return decorated.addShapeToCanvas(canvasId, factory, color);
    }

    @Override
    public List<Shape> getShapesOnCanvas(Long canvasId) {
        return decorated.getShapesOnCanvas(canvasId);
    }
}
