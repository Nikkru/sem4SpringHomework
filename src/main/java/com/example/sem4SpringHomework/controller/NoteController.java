package com.example.sem4SpringHomework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoteController {

    @RequestMapping("/")
    public String start(Model model){
        model.addAttribute("title", "Привет, Мир!");
        return "index";
    }

}
