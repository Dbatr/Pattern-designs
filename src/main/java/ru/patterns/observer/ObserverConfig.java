package ru.patterns.observer;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import ru.patterns.services.EventService;

/**
 * Паттерн Observer
 */
@Configuration
public class ObserverConfig {

    private final EventService eventService;
    private final ConsoleLoggerObserver consoleLoggerObserver;

    public ObserverConfig(EventService eventService, ConsoleLoggerObserver consoleLoggerObserver) {
        this.eventService = eventService;
        this.consoleLoggerObserver = consoleLoggerObserver;
    }

    @PostConstruct
    public void init() {
        eventService.addObserver(consoleLoggerObserver);
    }
}
