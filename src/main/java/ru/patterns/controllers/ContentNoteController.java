package ru.patterns.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patterns.models.ContentNote;
import ru.patterns.services.ContentNoteService;

@RestController
@RequestMapping("/contentnotes")
public class ContentNoteController {

    @Autowired
    private ContentNoteService contentNoteService;

    @PostMapping
    public ResponseEntity<ContentNote> createNote(@RequestBody ContentNote note) {
        ContentNote savedNote = contentNoteService.save(note);
        return ResponseEntity.ok(savedNote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentNote> getNoteById(@PathVariable Long id) {
        ContentNote note = contentNoteService.getNoteById(id);
        return ResponseEntity.ok(note);
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<String> processNote(@PathVariable Long id) {
        ContentNote note = contentNoteService.getNoteById(id);
        contentNoteService.processNote(note);  // Применяется стратегия обработки
        return ResponseEntity.ok("Note processed successfully.");
    }
}
