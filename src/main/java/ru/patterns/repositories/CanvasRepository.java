package ru.patterns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patterns.models.Canvas;

@Repository
public interface CanvasRepository extends JpaRepository<Canvas, Long> {
}
