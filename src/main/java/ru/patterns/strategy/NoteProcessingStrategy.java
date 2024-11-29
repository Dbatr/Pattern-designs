package ru.patterns.strategy;

import ru.patterns.models.ContentNote;

/**
 * Паттерн Strategy
 */
public interface NoteProcessingStrategy {
    void process(ContentNote note);
}
