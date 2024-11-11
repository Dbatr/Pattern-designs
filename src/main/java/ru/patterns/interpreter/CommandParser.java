package ru.patterns.interpreter;

import org.springframework.stereotype.Component;

@Component
public class CommandParser {

    public CommandExpression parseCommand(String command) {
        String[] parts = command.split(" ");

        if (command.startsWith("create canvas")) {

            String name = parts[2];
            return new CreateCanvasCommand(name);
        }
        return null;
    }
}
