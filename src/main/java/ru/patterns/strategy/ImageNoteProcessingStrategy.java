package ru.patterns.strategy;

import ru.patterns.models.ContentNote;

/**
 * Паттерн Strategy
 */
public class ImageNoteProcessingStrategy implements NoteProcessingStrategy {

    @Override
    public void process(ContentNote note) {
        System.out.println("Processing image note with path: " + note.getImagePath());
    }
}
