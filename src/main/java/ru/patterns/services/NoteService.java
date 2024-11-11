package ru.patterns.services;

import ru.patterns.dto.NoteDTO;
import ru.patterns.interfaces.NoteServiceI;
import ru.patterns.models.Note;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.patterns.services.commands.CommandFactory;
import ru.patterns.services.commands.CommandType;

/**
 * Паттерн Singleton.
 */
@Service
public class NoteService implements NoteServiceI {

    private final CommandFactory commandFactory;
//    private static NoteService instance;

    private NoteService(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
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
        return
                (List<Note>) commandFactory
                        .create(CommandType.GET_ALL_NOTES)
                        .execute();
    }

    /**
     * Добавляет новую заметку в базу данных.
     * 
     * @param noteDto Заметка, которую нужно добавить.
     * @return Сохраненная заметка.
     */
    public Note addNote(NoteDTO noteDto) {
        return
                (Note) commandFactory
                        .create(CommandType.ADD_NOTE, noteDto)
                        .execute();
    }

    /**
     * Получает заметку по ее уникальному идентификатору.
     * 
     * @param id Уникальный идентификатор заметки.
     * @return Optional, содержащий заметку, если она найдена, иначе пустой
     *         Optional.
     */
    public Optional<Note> getNoteById(Long id) {
        return
                (Optional<Note>) commandFactory
                        .create(CommandType.GET_NOTE_BY_ID, id)
                        .execute();
    }

    /**
     * Удаляет заметку из базы данных по ее уникальному идентификатору.
     * 
     * @param id Уникальный идентификатор заметки, которую нужно удалить.
     */
    public void deleteNoteById(Long id) {
        commandFactory
                .create(CommandType.DELETE_NOTE_BY_ID, id)
                .execute();
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
        return
                (Optional<Note>) commandFactory
                        .create(CommandType.DUPLICATE_NOTE, id)
                        .execute();
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
        return
                (Optional<Note>) commandFactory
                        .create(CommandType.UPDATE_NOTE, id, newTitle, newContent)
                        .execute();
    }
}
