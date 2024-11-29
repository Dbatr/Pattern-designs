package ru.patterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.patterns.models.ContentNote;
import ru.patterns.repositories.ContentNoteRepository;

/**
 * Паттерн Strategy
 */
@Component
public class TextNoteProcessingStrategy implements NoteProcessingStrategy {

    private final ContentNoteRepository contentNoteRepository;

    public TextNoteProcessingStrategy(ContentNoteRepository contentNoteRepository) {
        this.contentNoteRepository = contentNoteRepository;
    }

    @Override
    public void process(ContentNote note) {
        var processedContent = note.getContent().trim();

        if (!processedContent.isEmpty()) {
            processedContent = processedContent.substring(0, 1).toUpperCase() + processedContent.substring(1).toLowerCase();
        }
        note.setContent(processedContent);

        contentNoteRepository.save(note);
        System.out.println("Processing text note: " + note.getContent());
    }
}

