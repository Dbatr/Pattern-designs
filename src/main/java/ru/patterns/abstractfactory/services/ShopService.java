package ru.patterns.abstractfactory.services;

import org.springframework.stereotype.Service;
import ru.patterns.abstractfactory.factory.StationeryFactory;
import ru.patterns.abstractfactory.interfaces.NoteBook;
import ru.patterns.abstractfactory.interfaces.NotePad;

@Service
public class ShopService {
    private final StationeryFactory factory;

    public ShopService(StationeryFactory factory) {
        this.factory = factory;
    }

    public String buyNotebook() {
        NoteBook notebook = factory.createNotebook();
        return "You bought a " + notebook.getDescription();
    }

    public String buyNotepad() {
        NotePad notepad = factory.createNotepad();
        return "You bought a " + notepad.getDescription();
    }
}
