package ru.patterns.interpreter;

import ru.patterns.interfaces.CanvasServiceI;

public class CreateCanvasCommand implements CommandExpression {

    private final String name;

    public CreateCanvasCommand(String name) {
        this.name = name;
    }

    @Override
    public void interpret(CanvasServiceI canvasService) {
        canvasService.createCanvas(name);
    }
}
