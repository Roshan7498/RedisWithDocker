package com.roshan.sproject.demoForRedis.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roshan.sproject.demoForRedis.entities.Note;
import com.roshan.sproject.demoForRedis.services.NoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    // POST /api/v1/notes
    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note note) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(note));
    }

    // GET /api/v1/notes
    @GetMapping
    public ResponseEntity<List<Note>> findAll() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    // GET /api/v1/notes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Note> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    // PUT /api/v1/notes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note note) {
        return ResponseEntity.ok(noteService.updateNote(id, note));
    }

    // DELETE /api/v1/notes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}
