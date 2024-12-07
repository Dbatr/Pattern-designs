package ru.patterns.visitor;

import ru.patterns.models.Theme;

/**
 * Паттерн Visitor
 */
public interface ThemeVisitor {
    void visit(Theme theme);
}
