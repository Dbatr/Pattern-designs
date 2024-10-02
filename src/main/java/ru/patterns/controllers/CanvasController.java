package ru.patterns.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patterns.factory.CircleFactory;
import ru.patterns.factory.RectangleFactory;
import ru.patterns.factory.ShapeFactory;
import ru.patterns.models.Canvas;
import ru.patterns.models.Shape;
import ru.patterns.services.CanvasService;

import java.util.List;

@RestController
@RequestMapping("/canvas")
public class CanvasController {

    private final CanvasService canvasService;

    public CanvasController(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @PostMapping
    public ResponseEntity<Canvas> createCanvas(@RequestParam String name) {
        Canvas canvas = canvasService.createCanvas(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(canvas);
    }

    @PostMapping("/{id}/addRectangle")
    public ResponseEntity<Shape> addRectangle(@PathVariable Long id,
                                              @RequestParam double width,
                                              @RequestParam double height) {
        ShapeFactory factory = new RectangleFactory(width, height);
        Shape shape = canvasService.addShapeToCanvas(id, factory);
        return ResponseEntity.status(HttpStatus.CREATED).body(shape);
    }

    @PostMapping("/{id}/addCircle")
    public ResponseEntity<Shape> addCircle(@PathVariable Long id,
                                           @RequestParam double radius) {
        ShapeFactory factory = new CircleFactory(radius);
        Shape shape = canvasService.addShapeToCanvas(id, factory);
        return ResponseEntity.status(HttpStatus.CREATED).body(shape);
    }

    @GetMapping("/{id}/shapes")
    public ResponseEntity<List<Shape>> getShapesOnCanvas(@PathVariable Long id) {
        List<Shape> shapes = canvasService.getShapesOnCanvas(id);
        return ResponseEntity.ok(shapes);
    }
}
