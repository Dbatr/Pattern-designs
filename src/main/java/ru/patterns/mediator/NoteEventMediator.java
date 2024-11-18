package ru.patterns.mediator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.patterns.services.EventService;
import ru.patterns.models.Note;
import ru.patterns.models.Event;

import java.time.LocalDateTime;

/**
 * Паттерн Mediator
 */
@Component
public class NoteEventMediator implements SimpleMediator {

    private final EventService eventService;

    @Autowired
    public NoteEventMediator(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void notify(String event, Object data) {
        if ("noteCreated".equals(event) && data instanceof Note note) {

            LocalDateTime eventTime = note.getTimestamp() != null ? note.getTimestamp() : LocalDateTime.now();

            Event newEvent = new Event(
                    "Напоминание: " + note.getTitle(),
                    note.getContent(),
                    eventTime.plusDays(1),
                    false
            );
            eventService.createEvent(newEvent);
            System.out.println("Создано событие на основе заметки: " + newEvent.getName());
        }
    }
}
