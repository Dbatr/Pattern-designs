package ru.patterns.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patterns.dto.EventDTO;
import ru.patterns.interfaces.EventServiceI;
import ru.patterns.memento.EventCaretaker;
import ru.patterns.memento.EventMemento;
import ru.patterns.models.Event;
import ru.patterns.observer.Observed;
import ru.patterns.observer.Observer;
import ru.patterns.repositories.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService implements EventServiceI, Observed {

    private final EventRepository eventRepository;
    private final EventCaretaker caretaker = new EventCaretaker();
    private final List<Observer> observers = new ArrayList<>();

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

        eventRepository.save(event);

        notifyObservers("New event created: " + event.getName());

        event.handle();
    }

    @Override
    public Event updateEvent(Long id, EventDTO updatedEventDto) {
        Optional<Event> existingEvent = eventRepository.findById(id);

        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();

            event.handle();

            caretaker.save(event.saveState());

            event.setName(updatedEventDto.getName());
            event.setDescription(updatedEventDto.getDescription());
            event.setEventDate(updatedEventDto.getEventDate());
            event.setAllDay(updatedEventDto.isAllDay());
            event.setCompleted(updatedEventDto.isCompleted());

            notifyObservers("Event updated: " + event.getName());

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

                notifyObservers("Event restored: " + event.getName());

                return eventRepository.save(event);
            }
        }
        return null;
    }

    @Override
    public void createEventToNotify(Event newEvent) {
        eventRepository.save(newEvent);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
