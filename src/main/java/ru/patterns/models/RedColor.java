package ru.patterns.models;

import ru.patterns.interfaces.Color;

public class RedColor implements Color {
    @Override
    public String applyColor() {
        return "Applying red color";
    }
}
