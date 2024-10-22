package ru.patterns.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patterns.factory.CircleFactory;
import ru.patterns.factory.RectangleFactory;
import ru.patterns.factory.ShapeFactory;
import ru.patterns.interfaces.CanvasServiceI;
import ru.patterns.interfaces.Color;
import ru.patterns.models.Canvas;
import ru.patterns.models.Shape;
import ru.patterns.utils.ColorUtils;

import java.util.List;

@RestController
@RequestMapping("/canvas")
public class CanvasController {

    private final CanvasServiceI canvasService;

    public CanvasController(@Qualifier("loggingDecoratedCanvasService") CanvasServiceI canvasService) {
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
                                              @RequestParam double height,
                                              @RequestParam String color) {
        Color convertedColor = ColorUtils.getColorByString(color);
        ShapeFactory factory = new RectangleFactory(width, height, convertedColor);
        Shape shape = canvasService.addShapeToCanvas(id, factory, convertedColor);
        return ResponseEntity.status(HttpStatus.CREATED).body(shape);
    }

    @PostMapping("/{id}/addCircle")
    public ResponseEntity<Shape> addCircle(@PathVariable Long id,
                                           @RequestParam double radius,
                                           @RequestParam String color) {
        Color convertedColor = ColorUtils.getColorByString(color);
        ShapeFactory factory = new CircleFactory(radius, convertedColor);
        Shape shape = canvasService.addShapeToCanvas(id, factory, convertedColor);
        return ResponseEntity.status(HttpStatus.CREATED).body(shape);
    }

    @GetMapping("/{id}/shapes")
    public ResponseEntity<List<Shape>> getShapesOnCanvas(@PathVariable Long id) {
        List<Shape> shapes = canvasService.getShapesOnCanvas(id);
        return ResponseEntity.ok(shapes);
    }
}
