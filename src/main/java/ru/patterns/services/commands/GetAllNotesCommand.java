package ru.patterns.services.commands;

import ru.patterns.models.Note;
import ru.patterns.repositories.NoteRepository;

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
        return noteRepository.findAll();
    }
}
