package ru.patterns.services;

import ru.patterns.dto.NoteDTO;
import ru.patterns.interfaces.NoteServiceI;
import ru.patterns.models.Note;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Паттерн Proxy.
 */
public class NoteServiceCacheProxy implements NoteServiceI {
    private final NoteServiceI noteService;
    private final Map<Long, Note> cache = new HashMap<>();

    public NoteServiceCacheProxy(NoteServiceI noteService) {
        this.noteService = noteService;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @Override
    public Optional<Note> getNoteById(Long id) {
        if (cache.containsKey(id)) {
            System.out.println("Получение заметки из кэша: " + id);
            return Optional.of(cache.get(id));
        }
        Optional<Note> note = noteService.getNoteById(id);
        note.ifPresent(value -> cache.put(id, value));
        System.out.println("Добавление заметки в кэш: " + id);
        return note;
    }

    @Override
    public Note addNote(NoteDTO noteDto) {
        Note note = noteService.addNote(noteDto);
        cache.put(note.getId(), note);
        System.out.println("Добавление заметки в кэш: " + note.getId());
        return note;
    }

    @Override
    public void deleteNoteById(Long id) {
        noteService.deleteNoteById(id);
        cache.remove(id);
        System.out.println("Удаление заметки из кэша: " + id);
    }

    @Override
    public Optional<Note> duplicateNoteById(Long id) {
        Optional<Note> duplicatedNote = noteService.duplicateNoteById(id);
        duplicatedNote.ifPresent(note -> cache.put(note.getId(), note));
        System.out.println("Копирование заметки в кэш: " + id);
        return duplicatedNote;
    }

    @Override
    public Optional<Note> updateNoteById(Long id, String newTitle, String newContent) {
        Optional<Note> updatedNote = noteService.updateNoteById(id, newTitle, newContent);
        updatedNote.ifPresent(note -> cache.put(id, note));
        System.out.println("Обновление заметки в кэше: " + id);
        return updatedNote;
    }
}
