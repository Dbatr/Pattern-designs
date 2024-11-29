package ru.patterns.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patterns.models.ContentNote;
import ru.patterns.strategy.NoteProcessingStrategy;
import ru.patterns.strategy.TextNoteProcessingStrategy;
import ru.patterns.strategy.ImageNoteProcessingStrategy;
import ru.patterns.strategy.ChecklistNoteProcessingStrategy;
import ru.patterns.repositories.ContentNoteRepository;

@Service
public class ContentNoteService {

    @Autowired
    private ContentNoteRepository contentNoteRepository;

    private NoteProcessingStrategy processingStrategy;

    public void setProcessingStrategy(ContentNote note) {
        switch (note.getType().toLowerCase()) {
            case "text":
                this.processingStrategy = new TextNoteProcessingStrategy(contentNoteRepository);
                break;
            case "image":
                this.processingStrategy = new ImageNoteProcessingStrategy();
                break;
            case "checklist":
                this.processingStrategy = new ChecklistNoteProcessingStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown note type: " + note.getType());
        }
    }

    public void processNote(ContentNote note) {
        setProcessingStrategy(note);
        processingStrategy.process(note);
    }

    public ContentNote save(ContentNote note) {
        processNote(note);
        return contentNoteRepository.save(note);
    }

    public ContentNote getNoteById(Long id) {
        return contentNoteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }
}
