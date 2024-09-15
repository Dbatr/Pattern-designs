package ru.patterns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.patterns.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
