package ru.patterns.visitor;

/**
 * Паттерн Visitor
 */
public interface ThemeElement {
    void accept(ThemeVisitor visitor);
}
