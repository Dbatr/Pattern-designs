package ru.patterns.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import ru.patterns.memento.EventMemento;
import ru.patterns.state.CreatedState;
import ru.patterns.state.EventState;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event name cannot be blank")
    private String name;

    private String description;
    private LocalDateTime eventDate;
    private boolean isAllDay;
    private boolean isCompleted;

    @JsonIgnore
    @Transient
    private EventState state;

    public void setState(EventState state) {
        this.state = state;
    }

    public EventState getState() {
        return state;
    }

    public void handle() {
        state.handle(this);
    }

    public Event() {
        this.state = new CreatedState();
    }

    public Event(String name, String description, LocalDateTime eventDate, boolean isAllDay) {
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.isAllDay = isAllDay;
        this.isCompleted = false;

        this.state = new CreatedState();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public EventMemento saveState() {
        return new EventMemento(name, description, eventDate, isAllDay, isCompleted);
    }

    public void restoreState(EventMemento memento) {
        this.name = memento.name();
        this.description = memento.description();
        this.eventDate = memento.eventDate();
        this.isAllDay = memento.isAllDay();
        this.isCompleted = memento.isCompleted();
    }
}
