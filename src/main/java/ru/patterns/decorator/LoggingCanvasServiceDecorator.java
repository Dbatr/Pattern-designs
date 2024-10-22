package ru.patterns.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.patterns.factory.ShapeFactory;
import ru.patterns.interfaces.CanvasServiceI;
import ru.patterns.interfaces.Color;
import ru.patterns.models.Canvas;
import ru.patterns.models.Shape;

import java.util.List;

/**
 * Паттерн Decorator.
 */
public class LoggingCanvasServiceDecorator extends CanvasServiceDecorator {

    private static final Logger log = LoggerFactory.getLogger(LoggingCanvasServiceDecorator.class);

    public LoggingCanvasServiceDecorator(CanvasServiceI decorated) {
        super(decorated);
    }

    @Override
    public Canvas createCanvas(String name) {
        log.info("Создание холста с именем: {}", name);
        Canvas canvas = super.createCanvas(name);
        log.info("Холст успешно создан: {}", canvas.getName());
        return canvas;
    }

    @Override
    public Shape addShapeToCanvas(Long canvasId, ShapeFactory factory, Color color) {
        log.info("Добавление фигуры на холст с ID: {}, цвет: {}", canvasId, color);
        Shape shape = super.addShapeToCanvas(canvasId, factory, color);
        log.info("Фигура успешно добавлена на холст с ID: {}", canvasId);
        return shape;
    }

    @Override
    public List<Shape> getShapesOnCanvas(Long canvasId) {
        log.info("Получение фигур на холсте с ID: {}", canvasId);
        List<Shape> shapes = super.getShapesOnCanvas(canvasId);
        log.info("Получено {} фигур на холсте с ID: {}", shapes.size(), canvasId);
        return shapes;
    }
}
