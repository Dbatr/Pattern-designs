package ru.patterns.abstractfactory.factory;

import ru.patterns.abstractfactory.interfaces.NoteBook;
import ru.patterns.abstractfactory.interfaces.NotePad;

public interface StationeryFactory {
    NoteBook createNotebook();
    NotePad createNotepad();
}
