package ru.patterns.state;

import ru.patterns.models.Event;

/**
 * Паттерн State
 */
public class CanceledState implements EventState {
    @Override
    public void handle(Event event) {
        System.out.println("Событие отменено. " + event.getId() + event.getName());
    }
}
