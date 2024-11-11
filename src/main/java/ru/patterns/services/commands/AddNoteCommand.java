package ru.patterns.services.commands;

import ru.patterns.dto.NoteDTO;
import ru.patterns.models.Note;
import ru.patterns.repositories.NoteRepository;

/**
 * Паттерн Command
 */
public class AddNoteCommand implements Command {
    private final NoteRepository noteRepository;
    private final NoteDTO noteDto;

    public AddNoteCommand(NoteRepository noteRepository, NoteDTO noteDto) {
        this.noteRepository = noteRepository;
        this.noteDto = noteDto;
    }

    @Override
    public Note execute() {
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        return noteRepository.save(note);
    }
}