package ru.patterns.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.patterns.interfaces.Color;
import ru.patterns.models.BlueColor;
import ru.patterns.models.RedColor;

@Converter
public class ColorConverter implements AttributeConverter<Color, String> {
    @Override
    public String convertToDatabaseColumn(Color color) {
        if (color instanceof RedColor) {
            return "red";
        } else if (color instanceof BlueColor) {
            return "blue";
        }
        throw new IllegalArgumentException("Unknown color");
    }

    @Override
    public Color convertToEntityAttribute(String dbData) {
        return switch (dbData.toLowerCase()) {
            case "red" -> new RedColor();
            case "blue" -> new BlueColor();
            default -> throw new IllegalArgumentException("Unknown color in database: " + dbData);
        };
    }
}
