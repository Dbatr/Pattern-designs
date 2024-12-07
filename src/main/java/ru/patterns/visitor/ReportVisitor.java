package ru.patterns.visitor;

import ru.patterns.models.Theme;

/**
 * Паттерн Visitor
 */
public class ReportVisitor implements ThemeVisitor {
    @Override
    public void visit(Theme theme) {
        System.out.println("Report for Theme: " + theme.getName());
        System.out.println("Primary Color: " + theme.getPrimaryColor());
        System.out.println("Secondary Color: " + theme.getSecondaryColor());
        System.out.println("Font: " + theme.getFont());
    }
}
