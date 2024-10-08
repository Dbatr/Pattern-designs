package ru.patterns.abstractfactory.factory;


import ru.patterns.abstractfactory.interfaces.NoteBook;
import ru.patterns.abstractfactory.interfaces.NotePad;
import ru.patterns.abstractfactory.models.DesignerNotepad;
import ru.patterns.abstractfactory.models.OfficeNotebook;

public class OfficeStationeryFactory implements StationeryFactory {
    @Override
    public NoteBook createNotebook() {
        return new OfficeNotebook();
    }

    @Override
    public NotePad createNotepad() {
        return new DesignerNotepad();
    }
}
