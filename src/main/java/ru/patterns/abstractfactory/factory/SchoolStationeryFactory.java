package ru.patterns.abstractfactory.factory;

import ru.patterns.abstractfactory.interfaces.NoteBook;
import ru.patterns.abstractfactory.interfaces.NotePad;
import ru.patterns.abstractfactory.models.SchoolNotebook;
import ru.patterns.abstractfactory.models.SimpleNotepad;

public class SchoolStationeryFactory implements StationeryFactory {
    @Override
    public NoteBook createNotebook() {
        return new SchoolNotebook();
    }

    @Override
    public NotePad createNotepad() {
        return new SimpleNotepad();
    }
}
