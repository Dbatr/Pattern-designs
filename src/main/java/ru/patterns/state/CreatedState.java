package ru.patterns.state;

import ru.patterns.models.Event;

/**
 * Паттерн State
 */
public class CreatedState implements EventState {
    @Override
    public void handle(Event event) {
        System.out.println("Событие создано. " + event.getId() + event.getName());
        event.setState(new UpdatedState());
    }
}