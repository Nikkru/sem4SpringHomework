package com.example.sem4SpringHomework.repository;

import com.example.sem4SpringHomework.model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAll();
}
