package ru.patterns.controllers;

import ru.patterns.dto.NoteDTO;
import ru.patterns.models.Note;
import ru.patterns.repositories.NoteRepository;
import ru.patterns.services.NoteService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteRepository noteRepository) {
        this.noteService = NoteService.getInstance(noteRepository);
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping
    public ResponseEntity<String> addNote(@Valid @RequestBody NoteDTO note, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid note data");
        }
        noteService.addNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body("Note added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteService.getNoteById(id);
        return note.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNoteById(@PathVariable Long id) {
        if (noteService.getNoteById(id).isPresent()) {
            noteService.deleteNoteById(id);
            return ResponseEntity.ok("Note deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/duplicate/{id}")
    public ResponseEntity<Note> duplicateNoteById(@PathVariable Long id) {
        Optional<Note> noteCopy = noteService.duplicateNoteById(id);
        return noteCopy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
