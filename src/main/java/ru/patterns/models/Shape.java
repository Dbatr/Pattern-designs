package ru.patterns.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import ru.patterns.interfaces.Color;
import ru.patterns.utils.ColorConverter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "canvas_id")
    @JsonIgnore
    private Canvas canvas;

    @Convert(converter = ColorConverter.class)
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public Shape() {

    }

    public abstract String draw();

    public String applyColor() {
        return color.applyColor();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}