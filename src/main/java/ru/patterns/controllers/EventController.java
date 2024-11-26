package ru.patterns.controllers;

import org.springframework.web.bind.annotation.*;
import ru.patterns.dto.EventDTO;
import ru.patterns.interfaces.EventServiceI;
import ru.patterns.models.Event;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    public EventServiceI eventService;

    public EventController(EventServiceI eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public void createEvent(@RequestBody EventDTO event) {
        eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody EventDTO updatedEvent) {
        return eventService.updateEvent(id, updatedEvent);
    }

    @PostMapping("/{id}/undo")
    public Event undoLastChange(@PathVariable Long id) {
        return eventService.undoLastChange(id);
    }
}
