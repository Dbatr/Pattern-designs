package ru.patterns.services.commands;

import ru.patterns.dto.NoteDTO;
import ru.patterns.mediator.SimpleMediator;
import ru.patterns.models.Note;
import ru.patterns.repositories.NoteRepository;

/**
 * Паттерн Command
 */
public class AddNoteCommand implements Command {
    private final NoteRepository noteRepository;
    private final NoteDTO noteDto;
    private final SimpleMediator mediator;

    public AddNoteCommand(NoteRepository noteRepository, NoteDTO noteDto, SimpleMediator mediator) {
        this.noteRepository = noteRepository;
        this.noteDto = noteDto;
        this.mediator = mediator;
    }

    @Override
    public Note execute() {
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());

        mediator.notify("noteCreated", note);
        return noteRepository.save(note);
    }
}