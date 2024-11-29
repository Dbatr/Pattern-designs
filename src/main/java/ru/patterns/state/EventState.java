package ru.patterns.state;

import ru.patterns.models.Event;

/**
 * Паттерн State
 */
public interface EventState {
    void handle(Event event);
}
