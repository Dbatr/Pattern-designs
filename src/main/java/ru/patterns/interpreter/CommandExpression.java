package ru.patterns.interpreter;

import ru.patterns.interfaces.CanvasServiceI;

public interface CommandExpression {
    void interpret(CanvasServiceI canvasService);
}
