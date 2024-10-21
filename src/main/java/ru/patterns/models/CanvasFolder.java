package ru.patterns.models;

import jakarta.persistence.*;
import ru.patterns.interfaces.CanvasComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Паттерн Composite.
 */
@Entity
public class CanvasFolder extends CanvasComponent {

    @OneToMany(cascade = CascadeType.ALL)
    private final List<CanvasComponent> children = new ArrayList<>();

    public CanvasFolder() {
    }

    public CanvasFolder(String name) {
        this.setName(name);
    }

    @Override
    public void add(CanvasComponent component) {
        children.add(component);
    }

    @Override
    public void remove(CanvasComponent component) {
        children.remove(component);
    }

    @Override
    public List<CanvasComponent> getChildren() {
        return children;
    }
}
