package ru.patterns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patterns.models.CanvasFolder;

public interface CanvasFolderRepository extends JpaRepository<CanvasFolder, Long> {
}
