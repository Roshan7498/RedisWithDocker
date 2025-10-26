package com.roshan.sproject.demoForRedis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roshan.sproject.demoForRedis.entities.Note;

public interface NoteRepo extends JpaRepository<Note, Long> {
}
