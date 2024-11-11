package ru.patterns.services.commands;

import ru.patterns.models.Note;
import ru.patterns.repositories.NoteRepository;

import java.util.Optional;

/**
 * Паттерн Command
 */
public class GetNoteByIdCommand implements Command {
    private final NoteRepository noteRepository;
    private final Long id;

    public GetNoteByIdCommand(NoteRepository noteRepository, Long id) {
        this.noteRepository = noteRepository;
        this.id = id;
    }

    @Override
    public Optional<Note> execute() {
        return noteRepository.findById(id);
    }
}
