package ru.patterns.utils;

import org.springframework.stereotype.Component;
import ru.patterns.interfaces.CanvasValidationHandler;
import ru.patterns.models.Canvas;

/**
 * Паттерн Chain of Responsibility.
 */
@Component
public class CanvasNameValidationHandler implements CanvasValidationHandler {
    private CanvasValidationHandler next;

    @Override
    public boolean validate(Canvas canvas) {
        System.out.println("Validating canvas name...");
        if (canvas.getName().trim().isEmpty()) {
            System.out.println("Canvas name is invalid!");
            return false;
        }

        if (next != null) {
            return next.validate(canvas);
        }
        return true;
    }

    @Override
    public void setNext(CanvasValidationHandler next) {
        this.next = next;
    }
}
