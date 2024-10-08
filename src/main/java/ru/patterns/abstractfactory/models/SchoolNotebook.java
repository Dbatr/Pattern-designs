package ru.patterns.abstractfactory.models;

import ru.patterns.abstractfactory.interfaces.NoteBook;

public class SchoolNotebook implements NoteBook {
    @Override
    public String getDescription() {
        return "School Notebook with 80 pages";
    }
}
