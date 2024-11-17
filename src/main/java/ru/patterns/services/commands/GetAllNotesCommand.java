package ru.patterns.services.commands;

import ru.patterns.iterator.NoteIterator;
import ru.patterns.iterator.NoteIteratorImpl;
import ru.patterns.models.Note;
import ru.patterns.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Паттерн Command
 */
public class GetAllNotesCommand implements Command {
    private final NoteRepository noteRepository;

    public GetAllNotesCommand(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> execute() {
        List<Note> notes = noteRepository.findAll();
        List<Note> notesToReturn = new ArrayList<>();

        NoteIterator iterator = new NoteIteratorImpl(notes);

        while (iterator.hasNext()) {
            notesToReturn.add(iterator.next());
        }

        return notesToReturn;
    }
}
