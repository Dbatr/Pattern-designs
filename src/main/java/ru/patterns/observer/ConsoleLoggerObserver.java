package ru.patterns.observer;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Паттерн Observer.
 */
@Component
public class ConsoleLoggerObserver implements Observer {

    private final Logger logger = Logger.getLogger(ConsoleLoggerObserver.class.getName());

    @Override
    public void update(String message) {
        logger.info("Console logger observer: " + message);
    }
}
