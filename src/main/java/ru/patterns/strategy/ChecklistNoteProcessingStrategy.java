package ru.patterns.strategy;

import ru.patterns.models.ContentNote;

/**
 * Паттерн Strategy
 */
public class ChecklistNoteProcessingStrategy implements NoteProcessingStrategy {

    @Override
    public void process(ContentNote note) {
        System.out.println("Processing checklist note with tasks: " + note.getTasks());
    }
}
