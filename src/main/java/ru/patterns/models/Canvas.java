package ru.patterns.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "canvas")
public class Canvas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "canvas", cascade = CascadeType.ALL)
    private List<Shape> shapes = new ArrayList<>();

    public Canvas() {
    }

    public Canvas(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        shape.setCanvas(this);
    }
}
