package ru.patterns.services;

import org.springframework.stereotype.Service;
import ru.patterns.factory.ShapeFactory;
import ru.patterns.models.Canvas;
import ru.patterns.models.Shape;
import ru.patterns.repositories.CanvasRepository;
import ru.patterns.repositories.ShapeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CanvasService {

    private final CanvasRepository canvasRepository;
    private final ShapeRepository shapeRepository;

    public CanvasService(CanvasRepository canvasRepository, ShapeRepository shapeRepository) {
        this.canvasRepository = canvasRepository;
        this.shapeRepository = shapeRepository;
    }

    public Canvas createCanvas(String name) {
        Canvas canvas = new Canvas(name);
        return canvasRepository.save(canvas);
    }

    public Shape addShapeToCanvas(Long canvasId, ShapeFactory factory) {
        Optional<Canvas> canvasOptional = canvasRepository.findById(canvasId);
        if (canvasOptional.isPresent()) {
            Canvas canvas = canvasOptional.get();
            Shape shape = factory.createShape();
            canvas.addShape(shape);
            shapeRepository.save(shape);
            return shape;
        } else {
            throw new RuntimeException("Canvas not found");
        }
    }

    public List<Shape> getShapesOnCanvas(Long canvasId) {
        Optional<Canvas> canvasOptional = canvasRepository.findById(canvasId);
        return canvasOptional.map(Canvas::getShapes).orElse(Collections.emptyList());
    }
}
