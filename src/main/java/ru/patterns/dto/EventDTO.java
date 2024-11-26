package ru.patterns.dto;

import java.time.LocalDateTime;

public class EventDTO {

    private String name;
    private String description;
    private LocalDateTime eventDate;
    private boolean isAllDay;
    private boolean isCompleted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
