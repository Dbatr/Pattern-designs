package ru.patterns.iterator;

import ru.patterns.models.Note;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * паттерн Iterator
 */
public class NoteIteratorImpl implements NoteIterator {

    private final List<Note> notes;
    private int currentIndex = 0;

    public NoteIteratorImpl(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < notes.size();
    }

    @Override
    public Note next() {
        if (hasNext()) {
            return notes.get(currentIndex++);
        }
        throw new NoSuchElementException();
    }
}

