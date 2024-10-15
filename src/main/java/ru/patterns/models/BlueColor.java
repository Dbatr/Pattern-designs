package ru.patterns.models;

import ru.patterns.interfaces.Color;

public class BlueColor implements Color {
    @Override
    public String applyColor() {
        return "Applying blue color";
    }
}
