package ru.patterns.mediator;

/**
 * Паттерн Mediator
 */
public interface SimpleMediator {
    void notify(String event, Object data);
}
