package ru.patterns.abstractfactory.models;

import ru.patterns.abstractfactory.interfaces.NotePad;

public class DesignerNotepad implements NotePad {
    @Override
    public String getDescription() {
        return "Designer Notepad with custom design";
    }
}
