package ru.patterns.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patterns.dto.EventDTO;
import ru.patterns.interfaces.EventServiceI;
import ru.patterns.memento.EventCaretaker;
import ru.patterns.memento.EventMemento;
import ru.patterns.models.Event;
import ru.patterns.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements EventServiceI {

    private final EventRepository eventRepository;
    private final EventCaretaker caretaker = new EventCaretaker();

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
    public void createEvent(EventDTO eventDto) {
        Event event = new Event(
                eventDto.getName(),
                eventDto.getDescription(),
                eventDto.getEventDate(),
                eventDto.isAllDay()
        );
        event.setCompleted(eventDto.isCompleted());
        eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, EventDTO updatedEventDto) {
        Optional<Event> existingEvent = eventRepository.findById(id);

        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();

            caretaker.save(event.saveState());

            event.setName(updatedEventDto.getName());
            event.setDescription(updatedEventDto.getDescription());
            event.setEventDate(updatedEventDto.getEventDate());
            event.setAllDay(updatedEventDto.isAllDay());
            event.setCompleted(updatedEventDto.isCompleted());
            return eventRepository.save(event);
        }
        return null;
    }

    @Override
    public List<Event> getCompletedEvents() {
        return eventRepository.findByIsCompleted(true);
    }

    @Override
    public Event undoLastChange(Long id) {
        Optional<Event> existingEvent = eventRepository.findById(id);

        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            EventMemento memento = caretaker.undo();
            if (memento != null) {
                event.restoreState(memento);
                return eventRepository.save(event);
            }
        }
        return null;
    }

    @Override
    public void createEventToNotify(Event newEvent) {
        eventRepository.save(newEvent);
    }
}
