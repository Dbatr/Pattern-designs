package ru.patterns.services.commands;

import ru.patterns.repositories.NoteRepository;

/**
 * Паттерн Command
 */
public class DeleteNoteByIdCommand implements Command {

    private final NoteRepository noteRepository;
    private final Long id;

    public DeleteNoteByIdCommand(NoteRepository noteRepository, Long id) {
        this.noteRepository = noteRepository;
        this.id = id;
    }

    @Override
    public Void execute() {
        noteRepository.deleteById(id);
        return null;
    }
}
