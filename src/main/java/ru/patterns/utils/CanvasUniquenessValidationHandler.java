package ru.patterns.utils;

import org.springframework.stereotype.Component;
import ru.patterns.interfaces.CanvasValidationHandler;
import ru.patterns.models.Canvas;

/**
 * Паттерн Chain of Responsibility.
 */
@Component
public class CanvasUniquenessValidationHandler implements CanvasValidationHandler {
    private CanvasValidationHandler next;


    public CanvasUniquenessValidationHandler() {
    }

    @Override
    public boolean validate(Canvas canvas) {
        System.out.println("Validating canvas uniqueness...");

        if (!canvas.getName().trim().matches("^[a-zA-Z0-9 ]+$")) {
            System.out.println("Canvas name can only contain letters, numbers, and spaces!");
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
