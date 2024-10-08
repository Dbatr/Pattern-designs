package ru.patterns.abstractfactory.models;

import ru.patterns.abstractfactory.interfaces.NotePad;

public class SimpleNotepad implements NotePad {
    @Override
    public String getDescription() {
        return "Simple Notepad with 50 pages";
    }
}
