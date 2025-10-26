package com.roshan.sproject.demoForRedis.services;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.roshan.sproject.demoForRedis.entities.Note;
import com.roshan.sproject.demoForRedis.repositories.NoteRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepo noteRepo;

    @CacheEvict(value = "notes", allEntries = true)
    public Note createNote(Note note) {
        // id is auto-generated; ignore any id coming from client
        note.setId(null);
        return noteRepo.save(note);
    }
   
    @Cacheable(value = "notes", key = "'allNotes'")
   
        public List<Note> getAllNotes() {
        // If you want newest first, sort here or via Pageable
        return noteRepo.findAll();
    }

     @Cacheable (value = "notes", key ="#id")

    public Note getNoteById(Long id) {
        return noteRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Note not found with id: " + id));
    }

    @CacheEvict(value = "notes", allEntries = true)
    public Note updateNote(Long id, Note payload) {
        Note existing = getNoteById(id);
        existing.setTitle(payload.getTitle());
        existing.setContent(payload.getContent());
        existing.setLive(payload.isLive());
        // addedDate set @CreationTimestamp; keep as-is
        return noteRepo.save(existing);
    }

    @CacheEvict(value = "notes", allEntries = true)
    public void deleteNoteById(Long id) {
        if (!noteRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Note not found with id: " + id);
        }
        noteRepo.deleteById(id);
    }
}
