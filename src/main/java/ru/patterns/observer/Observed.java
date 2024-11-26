package ru.patterns.observer;

/**
 * Паттерн Observer
 */
public interface Observed {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
