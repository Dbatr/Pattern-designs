package ru.patterns.utils;

import ru.patterns.interfaces.Color;
import ru.patterns.models.BlueColor;
import ru.patterns.models.RedColor;

public class ColorUtils {

    public static Color getColorByString(String color) {
        return switch (color.toLowerCase()) {
            case "red" -> new RedColor();
            case "blue" -> new BlueColor();
            default -> throw new IllegalArgumentException("Invalid color: " + color);
        };
    }
}
