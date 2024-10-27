package ru.patterns.abstractfactory.models;

public class Pen {
    private final String color;
    private final double tipSize;

    public Pen(String color, double tipSize) {
        this.color = color;
        this.tipSize = tipSize;
    }

    public String getDescription() {
        return color + " pen with a " + tipSize + "mm tip";
    }
}
