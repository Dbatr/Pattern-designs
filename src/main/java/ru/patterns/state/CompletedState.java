package ru.patterns.state;

import ru.patterns.models.Event;

/**
 * Паттерн State
 */
public class CompletedState implements EventState {
    @Override
    public void handle(Event event) {
        System.out.println("Событие завершено. " + event.getId() + event.getName());
        event.setState(new CanceledState());
    }
}
