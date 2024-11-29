package ru.patterns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patterns.models.ContentNote;


public interface ContentNoteRepository extends JpaRepository<ContentNote, Long> {
}
