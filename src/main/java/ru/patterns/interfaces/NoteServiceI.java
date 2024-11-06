package ru.patterns.interfaces;

import ru.patterns.dto.NoteDTO;
import ru.patterns.models.Note;

import java.util.List;
import java.util.Optional;

public interface NoteServiceI {
    List<Note> getAllNotes();
    Optional<Note> getNoteById(Long id);
    Note addNote(NoteDTO noteDto);
    void deleteNoteById(Long id);
    Optional<Note> duplicateNoteById(Long id);
    Optional<Note> updateNoteById(Long id, String newTitle, String newContent);
}
