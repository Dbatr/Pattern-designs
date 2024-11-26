package ru.patterns.interfaces;

import ru.patterns.dto.EventDTO;
import ru.patterns.models.Event;

import java.util.List;

public interface EventServiceI {
    List<Event> getAllEvents();
    Event getEventById(Long id);
    void createEvent(EventDTO eventDto);
    Event updateEvent(Long id, EventDTO updatedEventDto);
    List<Event> getCompletedEvents();
    Event undoLastChange(Long id);
    void createEventToNotify(Event newEvent);
}
