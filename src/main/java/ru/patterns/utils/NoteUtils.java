package ru.patterns.utils;

import ru.patterns.models.Note;

import java.time.LocalDateTime;

/**
 * Паттерн Static Factory Method.
 */
public class NoteUtils {

    private NoteUtils() {
    }

    public static void changeTitle(Note note, String newTitle) {
        note.setTitle(newTitle);
    }

    public static void changeContent(Note note, String newContent) {
        note.setContent(newContent);
    }

    public static void updateNote(Note note, String newTitle, String newContent) {
        note.setTitle(newTitle);
        note.setContent(newContent);
        note.setTimestamp(LocalDateTime.now());
    }
}
