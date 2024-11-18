package ru.patterns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patterns.models.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByIsCompleted(boolean isCompleted);
}