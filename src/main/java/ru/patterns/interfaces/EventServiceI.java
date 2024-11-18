package ru.patterns.interfaces;

import ru.patterns.models.Event;

import java.util.List;

public interface EventServiceI {
    List<Event> getAllEvents();
    Event getEventById(Long id);
    void createEvent(Event event);
    Event updateEvent(Long id, Event updatedEvent);
    List<Event> getCompletedEvents();
}
