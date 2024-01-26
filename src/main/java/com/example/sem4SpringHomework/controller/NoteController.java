package com.example.sem4SpringHomework.controller;

import com.example.sem4SpringHomework.model.Note;
import com.example.sem4SpringHomework.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @RequestMapping("/")
    public String start(Model model){
        model.addAttribute("title", "Привет, Мир!");
        return "index";
    }

    @RequestMapping("/notes")
    public String notebook(Model model){
        List<Note> allNotes = noteRepository.findAll();
        model.addAttribute("allNotes", allNotes);
        return "notes";
    }

    @RequestMapping(value = "/note", method = RequestMethod.GET)
    public String newEntry(Model model) {
        model.addAttribute("pageTitle", "New Note");
        model.addAttribute("givenAction", "/note");
        model.addAttribute("givenTitle", "");
        model.addAttribute("givenNote", "");
        return "note";
    }

    @RequestMapping(value = "/note", method = RequestMethod.POST)
    public String addNote(@RequestParam String title, @RequestParam String note) {
        Note newNote = new Note(title, note);
        noteRepository.save(newNote);
        return "redirect:/notes";
    }

    @RequestMapping(value = "/entry/{id}", method = RequestMethod.GET)
    public String editNote(@PathVariable(value = "id") Long noteId, Model model) {
        Optional<Note> note = noteRepository.findById(noteId);
        model.addAttribute("pageTitle", "Edit Note");
        model.addAttribute("givenAction", "/note/" + noteId);
        model.addAttribute("givenTitle", note.get().getTitle());
        model.addAttribute("givenNote", note.get().getNote());
        return "note";
    }

    @RequestMapping(value = "/note/{id}", method = RequestMethod.POST)
    public String updateNote(@PathVariable(value = "id") Long noteId,
                              @RequestParam String title,
                              @RequestParam String note) {
        Optional<Note> n = noteRepository.findById(noteId);
        if (n.isPresent()) {
            Note nn = n.get();
            nn.setTitle(title);
            nn.setNote(note);
            noteRepository.save(nn);
            return "redirect:/";
        }
        return "redirect:/";
    }

}
