package ru.patterns.interfaces;

import ru.patterns.factory.ShapeFactory;
import ru.patterns.models.Canvas;
import ru.patterns.models.Shape;

import java.util.List;

public interface CanvasServiceI {
    Canvas createCanvas(String name);
    Shape addShapeToCanvas(Long canvasId, ShapeFactory factory, Color color);
    List<Shape> getShapesOnCanvas(Long canvasId);
}
