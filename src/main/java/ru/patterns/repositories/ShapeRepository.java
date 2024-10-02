package ru.patterns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patterns.models.Shape;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Long> {
}
