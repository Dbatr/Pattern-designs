package ru.patterns.services;

import ru.patterns.dto.NoteDTO;
import ru.patterns.interfaces.NoteServiceI;
import ru.patterns.models.Note;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.patterns.repositories.NoteRepository;
import ru.patterns.utils.NoteUtils;

/**
 * Паттерн Singleton.
 */
@Service
public class NoteService implements NoteServiceI {

    private final NoteRepository noteRepository;
//    private static NoteService instance;

    private NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

//    public static NoteService getInstance(NoteRepository noteRepository) {
//        if (instance == null) {
//            instance = new NoteService(noteRepository);
//        }
//        return instance;
//    }

    /**
     * Получает список всех заметок.
     * 
     * @return Список всех заметок.
     */
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Добавляет новую заметку в базу данных.
     * 
     * @param noteDto Заметка, которую нужно добавить.
     * @return Сохраненная заметка.
     */
    public Note addNote(NoteDTO noteDto) {
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        return noteRepository.save(note);
    }

    /**
     * Получает заметку по ее уникальному идентификатору.
     * 
     * @param id Уникальный идентификатор заметки.
     * @return Optional, содержащий заметку, если она найдена, иначе пустой
     *         Optional.
     */
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    /**
     * Удаляет заметку из базы данных по ее уникальному идентификатору.
     * 
     * @param id Уникальный идентификатор заметки, которую нужно удалить.
     */
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    /**
     * Создает дубликат существующей заметки по ее уникальному идентификатору.
     * Использует паттерн проектирования Prototype для копирования объекта заметки.
     *
     * @param id Уникальный идентификатор заметки, которую нужно продублировать.
     * @return Optional, содержащий дубликат заметки, если оригинальная заметка найдена,
     *         иначе возвращается пустой Optional.
     */
    public Optional<Note> duplicateNoteById(Long id) {
        Optional<Note> existingNote = noteRepository.findById(id);

        if (existingNote.isPresent()) {
            Note noteCopy = (Note) existingNote.get().copy();  // Копируем заметку
            noteRepository.save(noteCopy);
            return Optional.of(noteCopy);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Обновляет существующую заметку по ее уникальному идентификатору.
     * Использует статический фабричный метод для изменения заголовка и содержания.
     *
     * @param id Уникальный идентификатор заметки, которую нужно обновить.
     * @param newTitle Новый заголовок для заметки.
     * @param newContent Новое содержание для заметки.
     * @return Optional, содержащий обновленную заметку, если оригинальная заметка найдена,
     *         иначе возвращается пустой Optional.
     */
    public Optional<Note> updateNoteById(Long id, String newTitle, String newContent) {
        Optional<Note> existingNote = noteRepository.findById(id);

        if (existingNote.isPresent()) {
            Note noteToUpdate = existingNote.get();
            NoteUtils.updateNote(noteToUpdate, newTitle, newContent);
            noteRepository.save(noteToUpdate);

            return Optional.of(noteToUpdate);
        } else {
            return Optional.empty();
        }
    }
}
