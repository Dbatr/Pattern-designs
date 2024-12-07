package ru.patterns.dto;

public class ThemeRequestDTO {
    private String name;

    public ThemeRequestDTO() {
    }

    public ThemeRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
