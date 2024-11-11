package ru.patterns.services.commands;

import ru.patterns.models.Note;
import ru.patterns.repositories.NoteRepository;

import java.util.Optional;

/**
 * Паттерн Command
 */
public class DuplicateNoteCommand implements Command {

    private final NoteRepository noteRepository;
    private final Long id;

    public DuplicateNoteCommand(NoteRepository noteRepository, Long id) {
        this.noteRepository = noteRepository;
        this.id = id;
    }

    @Override
    public Optional<Note> execute() {
        Optional<Note> existingNote = noteRepository.findById(id);

        if (existingNote.isPresent()) {
            Note noteCopy = (Note) existingNote.get().copy();
            noteRepository.save(noteCopy);
            return Optional.of(noteCopy);

        } else {
            return Optional.empty();
        }
    }
}


