package ru.patterns.interfaces;

import jakarta.persistence.*;

import java.util.List;

/**
 * Паттерн Composite.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CanvasComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void add(CanvasComponent component);

    public abstract void remove(CanvasComponent component);

    public abstract List<CanvasComponent> getChildren();
}