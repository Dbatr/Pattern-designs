package ru.patterns.services.commands;

import ru.patterns.models.Note;
import ru.patterns.repositories.NoteRepository;
import ru.patterns.utils.NoteUtils;

import java.util.Optional;

/**
 * Паттерн Command
 */
public class UpdateNoteCommand implements Command {

    private final NoteRepository noteRepository;
    private final Long id;
    private final String newTitle;
    private final String newContent;

    public UpdateNoteCommand(NoteRepository noteRepository, Long id, String newTitle, String newContent) {
        this.noteRepository = noteRepository;
        this.id = id;
        this.newTitle = newTitle;
        this.newContent = newContent;
    }

    @Override
    public Object execute() {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {
            Note noteToUpdate = existingNote.get();

            NoteUtils.updateNote(noteToUpdate, newTitle, newContent);
            noteRepository.save(noteToUpdate);
            return Optional.of(noteToUpdate);
        }
        return Optional.empty();
    }
}
