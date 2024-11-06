package ru.patterns.interfaces;

import ru.patterns.models.Canvas;

/**
 * Паттерн Chain of Responsibility.
 */
public interface CanvasValidationHandler {
    boolean validate(Canvas canvas);
    void setNext(CanvasValidationHandler next);
}
