package ru.patterns.services.commands;

import org.springframework.stereotype.Component;
import ru.patterns.dto.NoteDTO;
import ru.patterns.repositories.NoteRepository;

/**
 * Паттерн Command
 */
@Component
public class CommandFactory {

    private final NoteRepository noteRepository;

    public CommandFactory(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Command create(int commandCode, Object... params) {
        return switch (commandCode) {
            case CommandType.GET_ALL_NOTES ->
                    new GetAllNotesCommand(noteRepository);

            case CommandType.ADD_NOTE ->
                    new AddNoteCommand(noteRepository, (NoteDTO) params[0]);

            case CommandType.GET_NOTE_BY_ID ->
                    new GetNoteByIdCommand(noteRepository, (Long) params[0]);

            case CommandType.DELETE_NOTE_BY_ID ->
                    new DeleteNoteByIdCommand(noteRepository, (Long) params[0]);

            case CommandType.UPDATE_NOTE ->
                    new UpdateNoteCommand(noteRepository, (Long) params[0], (String) params[1], (String) params[2]);

            case CommandType.DUPLICATE_NOTE ->
                    new DuplicateNoteCommand(noteRepository, (Long) params[0]);

            default -> throw new IllegalArgumentException("Invalid command code: " + commandCode);
        };
    }
}
