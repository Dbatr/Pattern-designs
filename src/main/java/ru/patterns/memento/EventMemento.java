package ru.patterns.memento;

import java.time.LocalDateTime;

/**
 * Паттерн Memento
 */
public record EventMemento(String name, String description, LocalDateTime eventDate,
                           boolean isAllDay, boolean isCompleted) {

}

