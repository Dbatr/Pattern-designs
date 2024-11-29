package ru.patterns.state;

import ru.patterns.models.Event;

/**
 * Паттерн State
 */
public class UpdatedState implements EventState {
    @Override
    public void handle(Event event) {
        System.out.println("Событие обновлено. " + event.getId() + event.getName());
        event.setState(new CompletedState());
    }
}