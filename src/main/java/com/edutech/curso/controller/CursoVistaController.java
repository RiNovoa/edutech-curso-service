package com.edutech.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edutech.curso.model.Curso;
import com.edutech.curso.service.CursoService;

@Controller
@RequestMapping("/cursos")
public class CursoVistaController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String mostrarCurso(Model model){
        model.addAttribute("cursos", cursoService.getCursos());
        return "cursos";  // Nombre de la vista HTML (Thymeleaf o similar)
    }

    @PostMapping("/agregar")
    public String agregarCurso(@ModelAttribute Curso curso){
        cursoService.saveCurso(curso);
        return "redirect:/cursos";
    }
}
