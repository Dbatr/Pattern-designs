package ru.patterns.abstractfactory.models;

import ru.patterns.abstractfactory.interfaces.NoteBook;

public class OfficeNotebook implements NoteBook {
    @Override
    public String getDescription() {
        return "Office Notebook with 120 pages";
    }
}
