package ru.patterns.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patterns.interfaces.EventServiceI;
import ru.patterns.models.Event;
import ru.patterns.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements EventServiceI {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event updatedEvent) {
        Optional<Event> existingEvent = eventRepository.findById(id);

        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            event.setName(updatedEvent.getName());
            event.setDescription(updatedEvent.getDescription());
            event.setEventDate(updatedEvent.getEventDate());
            event.setAllDay(updatedEvent.isAllDay());
            event.setCompleted(updatedEvent.isCompleted());
            return eventRepository.save(event);
        }
        return null;
    }

    @Override
    public List<Event> getCompletedEvents() {
        return eventRepository.findByIsCompleted(true);
    }
}
