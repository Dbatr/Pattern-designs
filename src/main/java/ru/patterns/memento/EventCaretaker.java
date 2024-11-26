package ru.patterns.memento;

import java.util.Stack;

/**
 * Паттерн Memento
 */
public class EventCaretaker {
    private final Stack<EventMemento> mementos = new Stack<>();

    public void save(EventMemento memento) {
        mementos.push(memento);
    }

    public EventMemento undo() {
        return mementos.isEmpty() ? null : mementos.pop();
    }
}
