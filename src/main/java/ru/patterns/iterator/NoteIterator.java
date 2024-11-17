package ru.patterns.iterator;

import ru.patterns.models.Note;

/**
 * паттерн Iterator
 */
public interface NoteIterator {
    boolean hasNext();
    Note next();
}
